package pl.nordea.recruitment.SentenceApp.service.sentence.impl;

import org.springframework.stereotype.Service;
import pl.nordea.recruitment.SentenceApp.model.Sentence;
import pl.nordea.recruitment.SentenceApp.model.Sentences;
import pl.nordea.recruitment.SentenceApp.service.sentence.SentenceService;
import pl.nordea.recruitment.SentenceApp.utils.TextParser;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class SentenceServiceImpl implements SentenceService {

    @Override
    public Sentences process(byte[] data) {
        String textToProcess = new String(data, StandardCharsets.UTF_8);
        TextParser parser = new TextParser();

        List<Sentence> sentenceList = new ArrayList<>();

        // parsowanie całego tekstu do listy sentencji
        List<String> sentenceStringList = parser.splitToSentences(textToProcess);

        // parsowanie poszczególnych sentencji do listy słów
        for (String sentence : sentenceStringList) {
            List<String> wordStringList = parser.splitToWordsOrdered(sentence);
            sentenceList.add(new Sentence(wordStringList));
        }

        return new Sentences(sentenceList);
    }
}
