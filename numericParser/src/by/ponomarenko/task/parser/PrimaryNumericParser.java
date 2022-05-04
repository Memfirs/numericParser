package by.ponomarenko.task.parser;

public class PrimaryNumericParser extends AbstractNumericParser {

    private final String[] DIVISOR = new String[]{"один", "два", "три", "четыре",
            "пять", "шесть", "семь", "восемь", "девять", "десять", "одиннадцать", "двенадцать", "тринадцать",
            "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};

    private final String[] DIVISOR_VARIABLE = new String[]{"одна", "две"};

    @Override
    public String getNumericValue(String value) {

        StringBuilder stringBuilder = new StringBuilder();

        int index = 0;
        int number;
        if (value.length() > 3) {
            number = Integer.parseInt(value.substring(value.length() - 3));
        } else {
            number = Integer.parseInt(value);
        }

        if (number < 10) {
            index = (number % 10) - 1;
        } else if (number < 20) {
            index = (number % 20) - 1;
        }

        if (index < DIVISOR.length) {
            if (!flagSyntax) {
                stringBuilder.append(DIVISOR[index]);
            } else {
                stringBuilder.append(DIVISOR_VARIABLE[index]);
            }
        }
        return stringBuilder.toString();
    }
}