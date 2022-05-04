package by.test.ponomarenko.task.parser;

import by.ponomarenko.task.entity.PowerCustomData;
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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class NumericParserTest {

    static Logger logger = LogManager.getLogger();

    AbstractNumericParser numericParser = new DefaultNumericParser();
    PowerCustomData powerCustomData = new PowerCustomData();

    final String fileName = "resources\\data\\powerData.txt";

    @Before
    public void setUp() throws TaskCustomException {

        List<String> dataList;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
            dataList = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new TaskCustomException("File \"" + fileName + "\" not found or damaged.");
        }
        if (dataList.isEmpty()) {
            throw new TaskCustomException("File \"" + fileName + "\" is empty or contains only invalid data.");
        }

        Map<Integer, String> map = new HashMap<>();

        for (String line : dataList) {
            String[] tmpLine = line.split(" ");
            map.put(Integer.parseInt(tmpLine[0]), tmpLine[1]);
        }

        powerCustomData.setPowerData(map);
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

        int number = 4151;
        String actual = numericParser.getNumericValue(number);
        String expected = "четыреста пятнадцать тысяч шестьсот тридцать два";

        logger.log(Level.INFO, number + " != " + expected);
        Assert.assertNotEquals(actual, expected);
    }

    @RunWith(Parameterized.class)
    public static class ParameterizeTest {

        private String actual;
        private String expected;

        public ParameterizeTest(String actual, String expected) {
            this.actual = actual;
            this.expected = expected;
        }

        @Parameterized.Parameters
        public static Object dataForTest() throws TaskCustomException {
            NumericReader numericReader = new NumericReader();
            AbstractNumericParser numericParser = new DefaultNumericParser();
            Object[][] dataForTest = new Object[20][];

            List<String> textData = numericReader.readFromTxtWithValidation("resources\\data\\textData.txt");

            ListIterator<String> listIterator = textData.listIterator();

            while (listIterator.hasNext()) {
                String element = listIterator.next();
                dataForTest = new Object[][]{
                        {"четыреста", numericParser.getNumericValue(element)},
                        {"пять миллионов шестьсот шестнадцать тысяч пятьсот шестнадцать", numericParser.getNumericValue(element)},
                        {"один", numericParser.getNumericValue(element)},
                        {"ноль", numericParser.getNumericValue(element)},
                        {"минус четыре", numericParser.getNumericValue(element)},
                        {"шестьсот шестнадцать тысяч восемьсот сорок девять вигинтиллионов восемьсот девяносто четыре новемдециллиона девятьсот восемьдесят девять октодециллионов сто девяносто один септендециллион девятьсот восемьдесят один сексдециллион девятьсот шестьдесят один квиндециллион девятьсот восемьдесят девять кваттордециллионов восемьсот девяносто восемь тредециллионов девятьсот пятнадцать дуодециллионов сто девяносто один ундециллион девятьсот девятнадцать дециллионов девятьсот девяносто семь нониллионов двести девятнадцать октиллионов семьсот пятьдесят два септиллиона сто девяносто пять секстиллионов семьсот двадцать четыре квинтиллиона девятьсот пятнадцать квадриллионов семьсот двадцать четыре триллиона девятьсот пятнадцать миллиардов двести семьдесят девять миллионов четыреста пятьдесят одна тысяча девятьсот девяносто девять", numericParser.getNumericValue(element)},
                        {"девятьсот двенадцать квадриллионов восемьсот тринадцать триллионов семьсот четырнадцать миллиардов шестьсот два миллиона пятьсот три тысячи шестьсот четыре", numericParser.getNumericValue(element)},
                        {"шесть", numericParser.getNumericValue(element)},
                        {"пять", numericParser.getNumericValue(element)}
                };
            }
            return Arrays.asList(dataForTest);
        }

        @Test
        public void paramTest() throws TaskCustomException {

            logger.log(Level.INFO, expected + " = " + actual);
            Assert.assertEquals(actual, expected);
        }
    }
}