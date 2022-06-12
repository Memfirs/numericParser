package by.ponomarenko.task.writer;

import by.ponomarenko.task.exception.TaskCustomException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class NumericWriter {

    private static Logger logger = LogManager.getLogger();

    public void writeParserData(List<String> data) throws TaskCustomException {

        String outputPathFile = "resources\\data\\finalData.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPathFile))) {
            for (String line : data) {
                logger.log(Level.INFO, line);
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            logger.log(Level.ERROR, "File \"" + outputPathFile + "\" cannot be created.");
            throw new TaskCustomException("File \"" + outputPathFile + "\" cannot be created.");
        }
    }
}