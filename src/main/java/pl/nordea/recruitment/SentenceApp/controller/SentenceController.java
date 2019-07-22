package pl.nordea.recruitment.SentenceApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.nordea.recruitment.SentenceApp.model.Sentences;
import pl.nordea.recruitment.SentenceApp.service.sentence.SentenceService;
import pl.nordea.recruitment.SentenceApp.utils.CsvResponseWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class SentenceController {

    private static final String TEXT_CSV_TYPE = "text/csv";

    @Autowired
    private SentenceService sentenceService;

    @PostMapping(value = "/processFile/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public Sentences exportToXmlFile(@RequestParam("file") MultipartFile file) {
        Sentences sentences = getSentencesFromInputFile(file);

        return sentences;
    }

    @PostMapping(value = "/processFile/csv", produces = TEXT_CSV_TYPE)
    public void exportToCsvFile(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        Sentences sentences = getSentencesFromInputFile(file);

        CsvResponseWriter csvWriter = new CsvResponseWriter();
        try {
            csvWriter.writeSentences(response.getWriter(), sentences);
        } catch (IOException e) {
//            TODO: wyjątek
        }
    }

    private Sentences getSentencesFromInputFile(@RequestParam("file") MultipartFile file) {
        Sentences sentences = null;
        try {
            sentences = sentenceService.processFile(file.getBytes());
        } catch (IOException e) {
//            TODO: wyjątek
        }
        return sentences;
    }
}
