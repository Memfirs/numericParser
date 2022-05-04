package by.ponomarenko.task.parser;

abstract public class AbstractNumericParser {

    static public final String SEPARATOR = " ";

    //отвечает за один/одна и два/две
    static boolean flagSyntax = false;

    public String getNumericValue(long value) {
        return getNumericValue(Long.toString(value));
    }

    static public void changeSyntax() {
        flagSyntax = !flagSyntax;
    }

    abstract public String getNumericValue(String value);
}