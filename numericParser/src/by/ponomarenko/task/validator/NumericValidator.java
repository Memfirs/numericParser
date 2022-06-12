package by.ponomarenko.task.validator;

public class NumericValidator {

    private final static String NUMERIC_REGULAR = "^\\s*-?\\d+$";
    private final static String POWER_REGULAR = "^\\s*\\d+\\s[а-яА-Я]+\\s*$";

    public static boolean isValidate(String line) {
        return line.matches(NUMERIC_REGULAR);
    }

    public static boolean isValidatePower(String line) {
        return line.matches(POWER_REGULAR);
    }
}