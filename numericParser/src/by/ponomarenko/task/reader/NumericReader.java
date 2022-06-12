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

    public List<String> readAllDataFromTxt(String pathFile, String powerPathFile) throws TaskCustomException {

        powerCustomData.setPowerData(readInfo(powerPathFile));

        List<String> dataList = readFromTxt(pathFile);
        List<String> correctLines = new ArrayList<>();

        for (String lineForValidation : dataList) {
            if (!NumericValidator.isValidate(lineForValidation)) {
                logger.log(Level.INFO, "Line \"" + lineForValidation + "\" is incorrect.");
            } else {
                logger.log(Level.INFO, "Line \"" + lineForValidation + "\" is correct.");
                correctLines.add(lineForValidation.trim());
            }
        }

        if (correctLines.isEmpty()) {
            logger.log(Level.ERROR, "File \"" + pathFile + "\" is empty or contains only invalid data.");
            throw new TaskCustomException("File \"" + pathFile + "\" is empty or contains only invalid data.");
        }

        return correctLines;
    }

    private Map<Integer, String> readInfo(String pathFile) throws TaskCustomException {

        Map<Integer, String> map = new HashMap<>();
        List<String> dataList = readFromTxt(pathFile);

        for (String line : dataList) {

            if (!NumericValidator.isValidatePower(line)) {
                logger.log(Level.ERROR, "Line \"" + line + "\" is incorrect.");
                throw new TaskCustomException("File \"" + pathFile + "\" has invalid data at line \"" + line + "\".");
            } else {
                logger.log(Level.INFO, "Line \"" + line+ "\" is correct.");
            }

            String[] tmpLine = line.split(" ");
            map.put(Integer.parseInt(tmpLine[0]), tmpLine[1]);
        }
        return map;
    }

    private List<String> readFromTxt(String pathFile) throws TaskCustomException {

        List<String> dataList;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(pathFile))) {
            dataList = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            logger.log(Level.ERROR, "File \"" + pathFile + "\" not found or damaged.");
            throw new TaskCustomException("File \"" + pathFile + "\" not found or damaged.");
        }

        if (dataList.isEmpty()) {
            logger.log(Level.ERROR, "File \"" + pathFile + "\" is empty.");
            throw new TaskCustomException("File \"" + pathFile + "\" is empty.");
        } else {
            logger.log(Level.INFO, "File \"" + pathFile + "\" was successfully read.");
        }

        return dataList;
    }
}