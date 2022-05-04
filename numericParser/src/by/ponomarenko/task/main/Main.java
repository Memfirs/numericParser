package by.ponomarenko.task.main;

import by.ponomarenko.task.exception.TaskCustomException;
import by.ponomarenko.task.parser.AbstractNumericParser;
import by.ponomarenko.task.parser.DefaultNumericParser;
import by.ponomarenko.task.reader.NumericReader;

import java.util.List;

public class Main {

    public static void main(String[] args) throws TaskCustomException {

        NumericReader numericReader = new NumericReader();
        AbstractNumericParser numericParser = new DefaultNumericParser();

        List<String> textData = numericReader.readFromTxtWithValidation("resources\\data\\textData.txt");
        for (String line : textData) {
            System.out.println(numericParser.getNumericValue(line));
        }
    }
}