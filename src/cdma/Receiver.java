package cdma;

import java.util.Map;

public class Receiver {

    public static String findStations(String chip, int N) {

        if (chip.split(" ").length != N) {
            System.out.println("Invalid chip size!");
            return "";
        }

        String result = "";
        for (Map.Entry<Integer, String> entry : Main.stationsMap.entrySet()) {

            int res = Main.addSequence(entry.getValue(), chip);

            if (Math.abs(res) != 0) {
                result += "Station " + entry.getKey() + " (" + entry.getValue() + ") : " + res + "\n";
//                System.out.println("Station " + entry.getKey() + " (" + entry.getValue() + ") : " + res);
            }
        }
        return result;
    }
}
