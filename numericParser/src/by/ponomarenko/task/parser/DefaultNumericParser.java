package by.ponomarenko.task.parser;

public class DefaultNumericParser extends AbstractNumericParser {

    private final String ZERO = "ноль";
    private final String MINUS = "минус";

    private AbstractNumericParser abstractNumericParser = new CompositeNumericParser(63);

    @Override
    public String getNumericValue(String value) {

        boolean negative = false;
        if (value.startsWith("-")) {
            negative = true;
            value = value.substring(1);
        }

        String name = abstractNumericParser.getNumericValue(value);

        if ("".equals(name)) {
            name = ZERO;
        } else {
            if (negative) {
                name = MINUS.concat(SEPARATOR).concat(name);
            }
        }

        return name;
    }
}