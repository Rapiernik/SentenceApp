package pl.nordea.recruitment.SentenceApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.nordea.recruitment.SentenceApp.model.Sentences;
import pl.nordea.recruitment.SentenceApp.service.sentence.SentenceService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class SentenceController {

    @Autowired
    private SentenceService sentenceService;

    @PostMapping(value = "/processFile", produces = MediaType.APPLICATION_XML_VALUE)
    public Sentences process(@RequestParam("file") MultipartFile file) {

        Sentences sentences = null;
        try {
            sentences = sentenceService.process(file.getBytes());
        } catch (IOException e) {
//            TODO: wyjątek
        }

        return sentences;
    }
}
