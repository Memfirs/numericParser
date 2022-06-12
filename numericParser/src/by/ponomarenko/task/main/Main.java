package by.ponomarenko.task.main;

import by.ponomarenko.task.exception.TaskCustomException;
import by.ponomarenko.task.parser.AbstractNumericParser;
import by.ponomarenko.task.parser.DefaultNumericParser;
import by.ponomarenko.task.reader.NumericReader;
import by.ponomarenko.task.writer.NumericWriter;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws TaskCustomException {

        NumericReader numericReader = new NumericReader();
        AbstractNumericParser numericParser = new DefaultNumericParser();
        NumericWriter numericWriter = new NumericWriter();

        List<String> newData = new ArrayList<>();
        List<String> textData = numericReader.readAllDataFromTxt("resources\\data\\textData.txt", "resources\\data\\powerData.txt");
        for (String line : textData) {
            newData.add(numericParser.getNumericValue(line));
        }
        numericWriter.writeParserData(newData);

    }
}