package by.test.ponomarenko.task.reader;

import by.ponomarenko.task.exception.TaskCustomException;
import by.ponomarenko.task.reader.NumericReader;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NumericReaderTest {

    NumericReader numericReader = new NumericReader();

    @Test
    public void readFromTxtTestNegative() throws TaskCustomException {

        List<String> actualData = numericReader.readFromTxt("resources\\data\\textData.txt");
        List<String> expectedData = new ArrayList<>();

        expectedData.add("Wrong data.");
        Assert.assertNotEquals(actualData, expectedData);
    }

    @Test(expected = TaskCustomException.class)
    public void readFromTxtTestException() throws TaskCustomException {

        List<String> actualData = numericReader.readFromTxt("resources\\data\\no.txt");
    }
}