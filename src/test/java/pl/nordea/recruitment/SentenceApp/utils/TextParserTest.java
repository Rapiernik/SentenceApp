package pl.nordea.recruitment.SentenceApp.utils;

import org.junit.Test;

import java.util.List;

public class TextParserTest {

//    TODO: dokończyć testy

    @Test
    public void toSentences1() {
        String text = "Adaś jest Mr. Dwórznik i dlatego będzie gość. Taki z niego conan barbarian. A moze piwko? Oczywiście, że tak. Wypijmy razem z Mr. Dwórznikem.";

        TextParser parser = new TextParser();
        List<String> strings = parser.splitToSentences(text);

        for (String str : strings) {
            System.out.println(str);
        }
    }

    @Test
    public void toWords1() {
        String text = "But most importantly, we have a huge team of outstanding specialists ready to serve you, no matter what your financial challenge might be.";

        TextParser parser = new TextParser();
        List<String> strings = parser.splitToWordsOrdered(text);

        for (String str : strings) {
            System.out.println(str);
        }
    }
}
