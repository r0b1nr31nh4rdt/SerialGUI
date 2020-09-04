package serial;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Enumeration;
import java.util.HashMap;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");

        Enumeration ports = null;
        // map the port names to CommPortIdentifiers
        HashMap portMap = new HashMap();

        // this is the object that contains the opened port
        CommPortIdentifier selectedPortIdentifier = null;
        SerialPort serialPort = null;

        ComboBox<String> comboBox = new ComboBox();
        comboBox.setPromptText("Gefundene Ports:");

        ports = CommPortIdentifier.getPortIdentifiers();

        while (ports.hasMoreElements()) {
            CommPortIdentifier curPort = (CommPortIdentifier) ports.nextElement();

            if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                comboBox.getItems().add(curPort.getName());
                portMap.put(curPort.getName(), curPort);
                System.out.println("Port gefunden: " + curPort.getName());
            }
        }

        comboBox.setOnAction((e) -> {
            System.out.println((String)comboBox.getValue());
        });

        Button button1 = new Button("Push to close");
        button1.setOnAction((e) -> {

            System.out.println("Suche nach Ports");
        });

        VBox layout = new VBox(10);
        layout.setPadding((new Insets(10, 10, 10, 10)));
        layout.getChildren().add(comboBox);
        //  Scene scene = new Scene(layout, 300, 250);


        primaryStage.setScene(new Scene(layout, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
