package by.ponomarenko.task.parser;

public class TenNumericParser extends AbstractNumericParser {

    private final String[] DIVISOR = new String[]{"двадцать", "тридцать", "сорок",
            "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};

    private PrimaryNumericParser primaryNumericParser = new PrimaryNumericParser();

    @Override
    public String getNumericValue(String value) {
        StringBuilder stringBuilder = new StringBuilder();

        boolean tenSearch = false;

        int number;
        if (value.length() > 3) {
            number = Integer.parseInt(value.substring(value.length() - 3));
        } else {
            number = Integer.parseInt(value);
        }

        number = number % 100;

        if (number >= 20) {
            stringBuilder.append(DIVISOR[(number / 10) - 2]);
            number = number % 10;
            tenSearch = true;
            // numbers 20-99
        }

        if (number != 0) {
            if (tenSearch) {
                stringBuilder.append(SEPARATOR);
            }
            stringBuilder.append(primaryNumericParser.getNumericValue(number));
        }
        return stringBuilder.toString();
    }
}