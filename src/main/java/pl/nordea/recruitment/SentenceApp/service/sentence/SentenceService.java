package pl.nordea.recruitment.SentenceApp.service.sentence;

import pl.nordea.recruitment.SentenceApp.model.Sentences;

public interface SentenceService {

    Sentences process(byte[] data);
}
