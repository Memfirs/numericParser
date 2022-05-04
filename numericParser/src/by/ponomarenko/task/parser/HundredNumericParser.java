package by.ponomarenko.task.parser;

public class HundredNumericParser extends AbstractNumericParser {

    private final String[] DIVISOR = new String[]{"сто", "двести", "триста",
            "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};

    private TenNumericParser tenNumericParser = new TenNumericParser();

    @Override
    public String getNumericValue(String value) {
        StringBuilder stringBuilder = new StringBuilder();

        boolean hundredSearch = false;
        int number;

        if (value.length() > 4) {
            number = Integer.parseInt(value.substring(value.length() - 4));
        } else {
            number = Integer.parseInt(value);
        }

        number = number % 1000;

        if (number >= 100) {
            stringBuilder.append(DIVISOR[(number / 100) - 1]);
            number = number % 100;
            hundredSearch = true;
        }

        if (number != 0) {
            if (hundredSearch) {
                stringBuilder.append(SEPARATOR);
            }
            stringBuilder.append(tenNumericParser.getNumericValue(number));
        }
        return stringBuilder.toString();
    }
}