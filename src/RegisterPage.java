import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class RegisterPage {

    public static void show(Stage stage) {

        Label title = new Label("Doctor Register Portal");
        title.setFont(new Font("Arial", 20));

        TextField nameField = new TextField();
        nameField.setPromptText("Username");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        ComboBox<String> roleBox = new ComboBox<>();
        roleBox.getItems().addAll("Doctor", "Patient");
        roleBox.setPromptText("Select Role");

        Button registerBtn = new Button("Register");
        Button cancelBtn = new Button("Cancel");

        registerBtn.setMaxWidth(Double.MAX_VALUE);
        cancelBtn.setMaxWidth(Double.MAX_VALUE);

        registerBtn.setStyle("""
            -fx-background-color: linear-gradient(to right, #00c6ff, #0072ff);
            -fx-text-fill: white;
        """);

        cancelBtn.setStyle("""
            -fx-background-color: linear-gradient(to right, #ff416c, #ff4b2b);
            -fx-text-fill: white;
        """);

        Label msg = new Label();

        // 🔥 REGISTER ACTION (CONNECTED TO DB)
        registerBtn.setOnAction(e -> {

            String name = nameField.getText();
            String pass = password.getText();
            String role = roleBox.getValue();

            if (name.isEmpty() || pass.isEmpty() || role == null) {
                msg.setText("Fill all fields!");
                return;
            }

            User user = new User(name, pass, role);
            UserDAO.register(user);

            msg.setText("Registered Successfully!");

            nameField.clear();
            password.clear();
            roleBox.setValue(null);
        });

        cancelBtn.setOnAction(e -> stage.close());

        Hyperlink loginLink = new Hyperlink("Already a user? Login here");
        loginLink.setOnAction(e -> LoginPage.show(stage));

        VBox layout = new VBox(12,
                title,
                nameField,
                password,
                roleBox,
                registerBtn,
                cancelBtn,
                loginLink,
                msg
        );

        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding:25; -fx-background-color:#f0f8ff;");
        layout.setMaxWidth(300);

        stage.setScene(new Scene(layout, 400, 450));
        stage.setTitle("Register");
        stage.show();
    }
}