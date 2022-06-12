package by.test.ponomarenko.task.reader;

import by.ponomarenko.task.exception.TaskCustomException;
import by.ponomarenko.task.reader.NumericReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NumericReaderTest {

    static Logger logger = LogManager.getLogger();
    NumericReader numericReader = new NumericReader();

    @Test
    public void readAllDataFromTxtPositive() throws TaskCustomException {

        List<String> actualData = numericReader.readAllDataFromTxt("resources\\data\\littleData.txt", "resources\\data\\powerData.txt");
        List<String> expectedData = new ArrayList<>();

        expectedData.add("501");
        expectedData.add("3");
        expectedData.add("90");

        logger.log(Level.INFO, actualData + " = " + expectedData);
        Assert.assertEquals(actualData, expectedData);
    }

    @Test
    public void readAllDataFromTxtTestNegative() throws TaskCustomException {

        List<String> actualData = numericReader.readAllDataFromTxt("resources\\data\\textData.txt", "resources\\data\\powerData.txt");
        List<String> expectedData = new ArrayList<>();

        expectedData.add("Wrong data.");

        logger.log(Level.INFO, actualData + " != " + expectedData);
        Assert.assertNotEquals(actualData, expectedData);
    }

    @Test(expected = TaskCustomException.class)
    public void readAllDataFromTxtTestException() throws TaskCustomException {

        numericReader.readAllDataFromTxt("resources\\data\\no.txt", "resources\\data\\powerData.txt");
    }
}