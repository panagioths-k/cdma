package cdma;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;


public class Controller {

    @FXML
    private TextField nInput;

    @FXML
    private Button generateStationsButton;

    @FXML
    private TextArea stationArea;

    @FXML
    private TextField receiverInput;

    @FXML
    private Button findStationsButton;

    @FXML
    private TextArea receiverArea;

    @FXML
    private Label errorLabel;

    @FXML
    private void generateStations() {

        clearErrorLabel();

        int N;
        try {
            N = Integer.parseInt(nInput.getText());
        } catch (Exception e) {
            errorLabel.setText("Error reading number of bits!");
            return;
        }


        boolean isPowerOfTwo = Main.isPowerOfTwo(N);
        if (!isPowerOfTwo) {
            errorLabel.setText("Not a power of 2!");
            return;
        }


        List<String> stations = StationGenerator.generate(N);
        Main.stationsMap.clear();

        String stationStr = "";
        for (int i = 0; i < stations.size(); i++) {

            Main.stationsMap.put(i, stations.get(i));
            stationStr += "Station " + (i + 1) + ": " + stations.get(i) + "\n";
        }

        for (int i = 0; i < stations.size(); i++) {
            for (int j = i + 1; j < stations.size(); j++) {
                int res = Main.addSequence(stations.get(i), stations.get(j));
                if (res != 0) {
                    errorLabel.setText("Error: Stations " + i + ", " + j + " are not Orthogonal! (result = " + res + ")");
                }
            }
        }

        stationArea.setText(stationStr);
    }

    @FXML
    private void findStations() {

        clearErrorLabel();

        int N;
        try {
            N = Integer.parseInt(nInput.getText());
        } catch (Exception e) {
            errorLabel.setText("Error reading number of bits!");
            return;
        }

        boolean isPowerOfTwo = Main.isPowerOfTwo(N);
        if (!isPowerOfTwo) {
            errorLabel.setText("Not a power of 2!");
            return;
        }

        String chip = receiverInput.getText();          //"1 1 1 1 3 -1 -1 3";
        if (chip.trim().isEmpty() || chip.split(" ").length != N) {
            errorLabel.setText("Invalid receiver input!");
            return;
        }

        receiverArea.setText(Receiver.findStations(chip, N));
    }

    private void clearErrorLabel(){
        errorLabel.setText("");
    }

}
