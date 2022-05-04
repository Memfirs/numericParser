package by.ponomarenko.task.parser;

import by.ponomarenko.task.entity.PowerCustomData;

public class CompositeNumericParser extends AbstractNumericParser {

    private AbstractNumericParser lowNumericParser;
    private ThousandNumericParser thousandNumericParser = new ThousandNumericParser();
    private PowerCustomData powerCustomData = new PowerCustomData();

    private int power;

    public CompositeNumericParser(int power) {
        if (power <= 6) {
            lowNumericParser = thousandNumericParser;
        } else {
            lowNumericParser = new CompositeNumericParser(power - 3);
        }
        this.power = power;
    }

    public String getPowerNameByCount() {
        return powerCustomData.getPowerName(getPowerCount());
    }

    public AbstractNumericParser getHighNumericParser() {
        return thousandNumericParser;
    }

    public AbstractNumericParser getLowNumericParser() {
        return lowNumericParser;
    }

    public int getPowerCount() {
        return power;
    }

    @Override
    public String getNumericValue(String value) {
        StringBuilder stringBuilder = new StringBuilder();

        int numberTemp = 0;
        int numberTemp2 = 0;

        String high;
        String low;
        if (value.length() < getPowerCount()) {
            high = "";
            low = value;
        } else {
            int index = value.length() - getPowerCount();
            high = value.substring(0, index);
            low = value.substring(index);
        }

        String highName = getHighNumericParser().getNumericValue(high);
        String lowName = getLowNumericParser().getNumericValue(low);

        if (!"".equals(highName)) {

            if (high.length() >= 2) {
                numberTemp = Integer.parseInt(high.substring(high.length() - 2).substring(0, 2));
            }
            numberTemp2 = Integer.parseInt(high.substring(high.length() - 1).substring(0, 1));

            stringBuilder.append(highName);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(getPowerNameByCount());
            if (((numberTemp2 == 2) || numberTemp2 == 3 || numberTemp2 == 4) && (numberTemp != 12) && (numberTemp != 13) && (numberTemp != 14)) {
                stringBuilder.append("а");
            } else {
                if (numberTemp2 == 1 && (numberTemp != 11)) {
                    stringBuilder.append("");
                } else {
                    stringBuilder.append("ов");
                }
            }
            if (!"".equals(lowName)) {
                stringBuilder.append(SEPARATOR);
            }
        }

        if (!"".equals(lowName)) {
            stringBuilder.append(lowName);
        }

        return stringBuilder.toString();
    }
}