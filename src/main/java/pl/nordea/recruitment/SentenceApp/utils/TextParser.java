package pl.nordea.recruitment.SentenceApp.utils;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import pl.nordea.recruitment.SentenceApp.model.Sentence;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TextParser {

    private static final String PUNCTUATION_SYMBOLS_REGEX = "[\\p{Punct}\\s]+";

    public List<String> splitToSentences(String inputText) {
        InputStream is = getClass().getResourceAsStream("/opennlp/en-sent.bin");
        SentenceModel model = null;
        try {
            model = new SentenceModel(is);
        } catch (IOException e) {
//            TODO: throw nice exception
        }
        SentenceDetectorME detector = new SentenceDetectorME(model);
        String sentences[] = detector.sentDetect(inputText);

        List<String> outputList = new ArrayList<>();
        for (String sentence : sentences) {
            List<String> additionalSplitSentenceList = ensureItIsSingleSentence(sentence);
            if (additionalSplitSentenceList.size() == 1) {
                outputList.add(sentence);
            } else {
                for (String str : additionalSplitSentenceList) {
                    outputList.add(str);
                }
            }
        }

        return outputList;
    }

    public List<String> splitToWordsOrdered(String sentence) {
        InputStream inputStream = getClass().getResourceAsStream("/opennlp/en-token.bin");
        TokenizerModel model = null;
        try {
            model = new TokenizerModel(inputStream);
        } catch (IOException e) {
//            TODO: throw nice exception
        }
        TokenizerME tokenizer = new TokenizerME(model);
        String[] split = tokenizer.tokenize(sentence);

        //check correctness of split
        for (int i = 0; i < split.length; i++) {
            if (split[i].startsWith(",") || split[i].startsWith("'")) {
                split[i] = split[i].substring(1);
            }
            if (split[i].endsWith(",") || split[i].endsWith("'")) {
                split[i] = split[i].substring(0, split[i].length() - 2);
            }
        }

        // fix apostrophe issue
        List<String> output = new ArrayList<>();
        List<String> toProcess = Arrays.asList(split);
        for (int i = toProcess.size() - 1; i >= 0; i--) {
            if (i > 0 && toProcess.get(i).contains("'")) {
                output.add(toProcess.get(i - 1) + toProcess.get(i));
                i--;
            } else {
                output.add(toProcess.get(i));
            }
        }

        Collections.sort(output, String.CASE_INSENSITIVE_ORDER);

        return filterWords(output);
    }

    private List<String> filterWords(List<String> split) {
        return split.stream().filter(o -> !o.isEmpty()).filter(o -> !o.matches(PUNCTUATION_SYMBOLS_REGEX)).collect(Collectors.toList());
    }

    private List<String> ensureItIsSingleSentence(String sentence) {
        String[] split = sentence.split("[?!]");
        return Arrays.asList(split);
    }
}