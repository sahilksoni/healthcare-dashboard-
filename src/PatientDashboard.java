import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.property.*;

public class PatientDashboard {

    public static void show(Stage stage) {

        TableView<Patient> table = new TableView<>();

        TableColumn<Patient, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));

        TableColumn<Patient, String> disCol = new TableColumn<>("Disease");
        disCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDisease()));

        table.getColumns().addAll(nameCol, disCol);

        table.getItems().addAll(PatientDAO.getAllPatients());

        VBox layout = new VBox(10, table);
        layout.setStyle("-fx-padding:20; -fx-background-color:#fce4ec;");

        stage.setScene(new Scene(layout, 500, 400));
        stage.setTitle("Patient Dashboard");
    }
}