package by.test.ponomarenko.task.parser;

import by.ponomarenko.task.exception.TaskCustomException;
import by.ponomarenko.task.parser.AbstractNumericParser;
import by.ponomarenko.task.parser.DefaultNumericParser;
import by.ponomarenko.task.reader.NumericReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class NumericParserTest {

    static Logger logger = LogManager.getLogger();
    AbstractNumericParser numericParser = new DefaultNumericParser();
    List<String> textData;

    @Before
    public void init() throws TaskCustomException {

        NumericReader numericReader = new NumericReader();

        textData = numericReader.readAllDataFromTxt("resources\\data\\littleData.txt",
                "resources\\data\\powerData.txt");

    }

    @Test
    public void getNumericValuePositiveTest() {

        int number = 415632;
        String actual = numericParser.getNumericValue(number);
        String expected = "четыреста пятнадцать тысяч шестьсот тридцать два";

        logger.log(Level.INFO, number + " = " + expected);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getNumericValueNegativeTest() {

        int number = 12;
        String actual = numericParser.getNumericValue(number);
        String expected = "четыреста пятнадцать тысяч шестьсот тридцать два";

        logger.log(Level.INFO, number + " != " + expected);
        Assert.assertNotEquals(actual, expected);
    }

    @Test
    public void getManyNumericValuePositiveTest() {

        List<String> expected = new ArrayList<>();
        for (String line : textData) {
            expected.add(numericParser.getNumericValue(line));
        }

        List<String> actual = new ArrayList<>();
        actual.add("пятьсот один");
        actual.add("три");
        actual.add("девяносто");

        logger.log(Level.INFO, actual + " = " + expected);
        Assert.assertArrayEquals(actual.toArray(), expected.toArray());
    }
}