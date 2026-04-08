import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.beans.property.*;
import javafx.scene.chart.*;

public class DoctorDashboard {

    public static void show(Stage stage) {

        // BACK BUTTON
        Button backBtn = new Button("← Back");
        backBtn.setOnAction(e -> LoginPage.show(stage));

        // INPUT FIELDS
        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField ageField = new TextField();
        ageField.setPromptText("Age");

        TextField diseaseField = new TextField();
        diseaseField.setPromptText("Disease");

        TextField medicineField = new TextField();   // ✅ NEW
        medicineField.setPromptText("Medicine");

        // SEARCH
        TextField searchField = new TextField();
        searchField.setPromptText("Search...");

        Button addBtn = new Button("Add");
        Button deleteBtn = new Button("Delete");

        TableView<Patient> table = new TableView<>();

        // TABLE COLUMNS
        TableColumn<Patient,Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getId()).asObject());

        TableColumn<Patient,String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));

        TableColumn<Patient,Integer> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getAge()).asObject());

        TableColumn<Patient,String> disCol = new TableColumn<>("Disease");
        disCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDisease()));

        TableColumn<Patient,String> medCol = new TableColumn<>("Medicine"); // ✅ NEW
        medCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getMedicine()));

        table.getColumns().addAll(idCol, nameCol, ageCol, disCol, medCol);

        table.setItems(FXCollections.observableArrayList(PatientDAO.getAllPatients()));

        // SEARCH
        searchField.textProperty().addListener((obs, old, val) -> {
            ObservableList<Patient> filtered = FXCollections.observableArrayList();
            for (Patient p : PatientDAO.getAllPatients()) {
                if (p.getName().toLowerCase().contains(val.toLowerCase())) {
                    filtered.add(p);
                }
            }
            table.setItems(filtered);
        });

        // AUTOFILL
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, selected) -> {
            if (selected != null) {
                nameField.setText(selected.getName());
                ageField.setText(String.valueOf(selected.getAge()));
                diseaseField.setText(selected.getDisease());
                medicineField.setText(selected.getMedicine()); // ✅ NEW
            }
        });

        // ADD PATIENT
        addBtn.setOnAction(e -> {
            try {
                Patient p = new Patient(
                        nameField.getText(),
                        Integer.parseInt(ageField.getText()),
                        diseaseField.getText(),
                        medicineField.getText()
                );

                PatientDAO.insertPatient(p);

                // REFRESH TABLE
                table.setItems(FXCollections.observableArrayList(PatientDAO.getAllPatients()));

                // 🔥 SHOW OPD SLIP
                OPDSlip.show(p);

                nameField.clear();
                ageField.clear();
                diseaseField.clear();
                medicineField.clear();

            } catch (Exception ex) {
                System.out.println(ex);
            }
        });

        // DELETE
        deleteBtn.setOnAction(e -> {
            Patient p = table.getSelectionModel().getSelectedItem();
            if (p != null) {
                PatientDAO.deletePatient(p.getId());
                table.setItems(FXCollections.observableArrayList(PatientDAO.getAllPatients()));
            }
        });

        // CHART
        CategoryAxis x = new CategoryAxis();
        NumberAxis y = new NumberAxis();

        BarChart<String, Number> chart = new BarChart<>(x, y);
        chart.setTitle("Patient Age Chart");

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        for (Patient p : PatientDAO.getAllPatients()) {
            series.getData().add(new XYChart.Data<>(p.getName(), p.getAge()));
        }

        chart.getData().add(series);

        // LAYOUT
        VBox layout = new VBox(10,
                backBtn,
                searchField,
                nameField, ageField, diseaseField, medicineField,
                new HBox(10, addBtn, deleteBtn),
                table,
                chart
        );

        layout.setStyle("""
            -fx-padding:15;
            -fx-background-color: linear-gradient(to bottom, #2193b0, #6dd5ed);
        """);

        stage.setScene(new Scene(layout, 750, 600));
        stage.setTitle("Doctor Dashboard");
        stage.show();
    }
}