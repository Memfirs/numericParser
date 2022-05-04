package by.ponomarenko.task.parser;

import by.ponomarenko.task.entity.PowerCustomData;

public class ThousandNumericParser extends AbstractNumericParser {

    private final int POWER = 3;

    private HundredNumericParser hundredNumericParser = new HundredNumericParser();
    private PowerCustomData powerCustomData = new PowerCustomData();

    @Override
    public String getNumericValue(String value) {
        StringBuilder stringBuilder = new StringBuilder();

        int number;
        int numberTemp = 0;
        int numberTemp2 = 0;

        if ("".equals(value)) {
            number = 0;
        } else {
            if (value.length() > 6) {
                number = Integer.parseInt(value.substring(value.length() - 6));
                numberTemp = Integer.parseInt(value.substring(value.length() - 5).substring(0, 2));
                numberTemp2 = Integer.parseInt(value.substring(value.length() - 4).substring(0, 1));
            } else {
                number = Integer.parseInt(value);
            }
        }

        if (number >= 1000) {
            if (value.length() > 5) {
                //определяем последние 2 знакa для нахождения окночания слова тысячи
                numberTemp = Integer.parseInt(value.substring(value.length() - 5).substring(0, 2));
                numberTemp2 = Integer.parseInt(value.substring(value.length() - 4).substring(0, 1));
            } else {
                if (value.length() > 4) {
                    numberTemp = Integer.parseInt(value.substring(value.length() - 5).substring(0, 2));
                    numberTemp2 = Integer.parseInt(value.substring(value.length() - 4).substring(0, 1));

                } else {
                    numberTemp2 = Integer.parseInt(value.substring(value.length() - 4).substring(0, 1));
                }
            }

            //замена один/одна и два/две
            if ((numberTemp2 == 1 || numberTemp2 == 2) && (numberTemp != 11) && (numberTemp != 12)) {
                changeSyntax();
                stringBuilder.append(hundredNumericParser.getNumericValue(number / 1000));
                //возвращаем флаг замены
                changeSyntax();
            } else {
                stringBuilder.append(hundredNumericParser.getNumericValue(number / 1000));
            }

            stringBuilder.append(SEPARATOR);
            stringBuilder.append(powerCustomData.getPowerName(POWER));
            if (((numberTemp2 == 2) || numberTemp2 == 3 || numberTemp2 == 4) && (numberTemp != 12) && (numberTemp != 13) && (numberTemp != 14)) {
                stringBuilder.append("и");
            }
            if ((numberTemp2 == 1) && (numberTemp != 11)) {
                stringBuilder.append("а");
            }
        }

        String hundredsName = hundredNumericParser.getNumericValue(number);

        if (!"".equals(hundredsName) && (number >= 1000)) {
            stringBuilder.append(SEPARATOR);
        }
        stringBuilder.append(hundredsName);

        return stringBuilder.toString();
    }
}