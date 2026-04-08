import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OPDSlip {

    public static void show(Patient p) {

        Stage stage = new Stage();

        Label title = new Label("OPD SLIP");

        Label name = new Label("Name: " + p.getName());
        Label age = new Label("Age: " + p.getAge());
        Label disease = new Label("Disease: " + p.getDisease());
        Label medicine = new Label("Medicine: " + p.getMedicine());

        Label doctor = new Label("Doctor: Dr. Sharma");
        Label cabin = new Label("Cabin No: 101");

        VBox layout = new VBox(10,
                title,
                name,
                age,
                disease,
                medicine,
                doctor,
                cabin
        );

        layout.setStyle("-fx-padding:20; -fx-background-color:white;");

        stage.setScene(new Scene(layout, 300, 300));
        stage.setTitle("OPD Slip");
        stage.show();
    }
}