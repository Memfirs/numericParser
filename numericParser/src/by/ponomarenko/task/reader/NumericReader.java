package by.ponomarenko.task.reader;

import by.ponomarenko.task.entity.PowerCustomData;
import by.ponomarenko.task.exception.TaskCustomException;
import by.ponomarenko.task.validator.NumericValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NumericReader {

    private static Logger logger = LogManager.getLogger();

    private PowerCustomData powerCustomData = new PowerCustomData();

    private final String PATH_POWER_DATA = "resources\\data\\powerData.txt";

    public List<String> readFromTxt(String fileName) throws TaskCustomException {

        List<String> dataList;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
            dataList = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            logger.log(Level.ERROR, "File \"" + fileName + "\" not found or damaged.");
            throw new TaskCustomException("File \"" + fileName + "\" not found or damaged.");
        }

        if (dataList.isEmpty()) {
            logger.log(Level.ERROR, "File \"" + fileName + "\" is empty or contains only invalid data.");
            throw new TaskCustomException("File \"" + fileName + "\" is empty or contains only invalid data.");
        }

        logger.log(Level.INFO, "File \"" + fileName + "\" was read successfully.");
        return dataList;
    }

    public List<String> readFromTxtWithValidation(String fileName) throws TaskCustomException {

        List<String> correctLines = new ArrayList<>();

        for (String lineForParsing : readFromTxt(fileName)) {
            if (!NumericValidator.isValidate(lineForParsing)) {
                logger.log(Level.INFO, "Line \"" + lineForParsing + "\" is incorrect.");
            } else {
                logger.log(Level.INFO, "Line \"" + lineForParsing + "\" is correct.");
                correctLines.add(lineForParsing.trim());
            }
        }

        if (correctLines.isEmpty()) {
            logger.log(Level.ERROR, "No correct lines.");
            throw new TaskCustomException("No correct lines.");
        }

        powerCustomData.setPowerData(readInfo(PATH_POWER_DATA));
        return correctLines;
    }

    private Map<Integer, String> readInfo(String pathFile) throws TaskCustomException {

        Map<Integer, String> map = new HashMap<>();
        List<String> dataList = readFromTxt(pathFile);

        for (String line : dataList) {
            String[] tmpLine = line.split(" ");
            map.put(Integer.parseInt(tmpLine[0]), tmpLine[1]);
        }
        return map;
    }
}