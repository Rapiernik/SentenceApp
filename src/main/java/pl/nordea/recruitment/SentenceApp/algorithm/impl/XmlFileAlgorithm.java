package pl.nordea.recruitment.SentenceApp.algorithm.impl;

import org.springframework.stereotype.Component;
import pl.nordea.recruitment.SentenceApp.algorithm.FileAlgorithm;
import pl.nordea.recruitment.SentenceApp.model.Sentences;

@Component
public class XmlFileAlgorithm implements FileAlgorithm {

    @Override
    public byte[] createFile(Sentences sentences) {
        return new byte[0];
    }
}
