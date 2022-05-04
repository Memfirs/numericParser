package by.ponomarenko.task.validator;

public class NumericValidator {

    private final static String NUMERIC_REGULAR = "^\\s*-?\\d+$";

    public static boolean isValidate(String line) {
        return line.matches(NUMERIC_REGULAR);
    }
}