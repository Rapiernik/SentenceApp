package pl.nordea.recruitment.SentenceApp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "text")
@XmlAccessorType(XmlAccessType.FIELD)
public class Sentences implements Serializable {

    @XmlElement(name = "sentence")
    private List<Sentence> sentences = new ArrayList<>();

    public Sentences() {
    }

    public Sentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }
}
