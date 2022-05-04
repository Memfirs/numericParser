package by.ponomarenko.task.entity;

import java.util.Map;

public class PowerCustomData {

    static private Map<Integer, String> powerData;

    public Map<Integer, String> getPowerData() {
        return Map.copyOf(powerData);
    }

    public void setPowerData(Map<Integer, String> map) {
        powerData = map;
    }

    public String getPowerName(int power) {
        return powerData.get(power);
    }
}