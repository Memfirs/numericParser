package by.test.ponomarenko.task.writer;

import by.ponomarenko.task.exception.TaskCustomException;
import by.ponomarenko.task.reader.NumericReader;
import by.ponomarenko.task.writer.NumericWriter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NumericWriterTest {

    static Logger logger = LogManager.getLogger();
    NumericWriter numericWriter = new NumericWriter();
    NumericReader numericReader = new NumericReader();

    @Test
    public void writeParserDataTestPositive() throws TaskCustomException {

        String expectedPathFile = "resources\\data\\testData.txt";

        List<String> firstList = new ArrayList<>();
        firstList.add("1");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(expectedPathFile))) {
            for (String line : firstList) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            logger.log(Level.ERROR, "File \"" + expectedPathFile + "\" cannot be created.");
            throw new TaskCustomException("File \"" + expectedPathFile + "\" cannot be created.");
        }

        List<String> expectedList = numericReader.readAllDataFromTxt(expectedPathFile, "resources\\data\\powerData.txt");

        String actualPathFile = "resources\\data\\finalData.txt";

        List<String> secondList = new ArrayList<>();
        secondList.add("1");

        numericWriter.writeParserData(secondList);
        List<String> actualList = numericReader.readAllDataFromTxt(actualPathFile, "resources\\data\\powerData.txt");


        logger.log(Level.INFO, actualPathFile + " = " + expectedPathFile);
        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void writeParserDataTestNegative() throws TaskCustomException {

        String expectedPathFile = "resources\\data\\testData.txt";

        List<String> firstList = new ArrayList<>();
        firstList.add("1");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(expectedPathFile))) {
            for (String line : firstList) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            logger.log(Level.ERROR, "File \"" + expectedPathFile + "\" cannot be created.");
            throw new TaskCustomException("File \"" + expectedPathFile + "\" cannot be created.");
        }

        List<String> expectedList = numericReader.readAllDataFromTxt(expectedPathFile, "resources\\data\\powerData.txt");

        String actualPathFile = "resources\\data\\finalData.txt";

        List<String> secondList = new ArrayList<>();
        secondList.add("221");

        numericWriter.writeParserData(secondList);
        List<String> actualList = numericReader.readAllDataFromTxt(actualPathFile, "resources\\data\\powerData.txt");


        logger.log(Level.INFO, actualPathFile + " != " + expectedPathFile);
        Assert.assertNotEquals(expectedList, actualList);
    }
}