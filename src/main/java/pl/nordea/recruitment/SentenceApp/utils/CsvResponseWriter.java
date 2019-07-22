package pl.nordea.recruitment.SentenceApp.utils;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import pl.nordea.recruitment.SentenceApp.model.Sentence;
import pl.nordea.recruitment.SentenceApp.model.Sentences;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CsvResponseWriter {

    public void writeSentences(PrintWriter writer, Sentences sentences) {
        ColumnPositionMappingStrategy mapStrategy = new ColumnPositionMappingStrategy();

        try {
            mapStrategy.setType(String.class);

            int maxColumnNumber = sentences.getSentences().stream().max(Comparator.comparingInt(o -> o.getWords().size())).get().getWords().size();
            String[] columns = new String[maxColumnNumber + 1];
            for (int i = 0; i < columns.length; i++) {
                columns[i] = "Word " + i;
            }
            mapStrategy.setColumnMapping(columns);

            sentences.getSentences().stream().forEach(o -> o.getWords().add(0, "Sentence"));

            StatefulBeanToCsv btcsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withMappingStrategy(mapStrategy)
//                    .withSeparator(',')
                    .build();

//            btcsv.write(sentences.getSentences());
            for (Sentence sentence : sentences.getSentences()) {
                String collect = sentence.getWords().stream().collect(Collectors.joining(","));
                btcsv.write(collect);
            }

        } catch (CsvDataTypeMismatchException e) {
//            TODO: throw nice exception
        } catch (CsvRequiredFieldEmptyException e) {
//            TODO: throw nice exception
        }
    }
}
