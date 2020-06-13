package cdma;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    public static int viewportWidth = 700;                  //window dimensions
    public static int viewportHeight = 600;

    public static Map<Integer, String> stationsMap = new HashMap<>();


    @Override
    public void start(Stage primaryStage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Code-Division Multiple Access");
        primaryStage.setScene(new Scene(root, viewportWidth, viewportHeight));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static boolean isPowerOfTwo(int N) {
        return N > 0 && ((N & (N - 1)) == 0);
    }

    public static int addSequence(String station, String chip) {

        String[] stationArr = station.split(" ");
        String[] chipArr = chip.split(" ");
        int counter = 0;

        for (int i = 0; i < stationArr.length; i++) {
            counter += Integer.parseInt(stationArr[i]) * Integer.parseInt(chipArr[i]);
        }
        return counter;
    }
}
