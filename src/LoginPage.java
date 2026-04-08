import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class LoginPage {

    public static void show(Stage stage) {

        Label title = new Label("Doctor Login Portal");
        title.setFont(new Font("Arial", 20));

        ComboBox<String> roleBox = new ComboBox<>();
        roleBox.getItems().addAll("Doctor", "Patient");
        roleBox.setPromptText("Select User");

        TextField username = new TextField();
        username.setPromptText("Username");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        Button loginBtn = new Button("Login");
        Button cancelBtn = new Button("Cancel");

        loginBtn.setMaxWidth(Double.MAX_VALUE);
        cancelBtn.setMaxWidth(Double.MAX_VALUE);

        loginBtn.setStyle("""
            -fx-background-color: linear-gradient(to right, #00c6ff, #0072ff);
            -fx-text-fill: white;
        """);

        cancelBtn.setStyle("""
            -fx-background-color: linear-gradient(to right, #ff416c, #ff4b2b);
            -fx-text-fill: white;
        """);

        Label msg = new Label();

        // 🔥 LOGIN ACTION (CONNECTED TO DB)
        loginBtn.setOnAction(e -> {

            String role = roleBox.getValue();
            String user = username.getText();
            String pass = password.getText();

            if (role == null || user.isEmpty() || pass.isEmpty()) {
                msg.setText("Fill all fields!");
                return;
            }

            boolean success = UserDAO.login(user, pass, role);

            if (success) {
                msg.setText("Login Success!");

                if (role.equals("Doctor")) {
                    DoctorDashboard.show(stage);
                } else {
                    PatientDashboard.show(stage);
                }

            } else {
                msg.setText("Invalid credentials!");
            }
        });

        cancelBtn.setOnAction(e -> stage.close());

        Hyperlink registerLink = new Hyperlink("Don't have account? Register here");
        registerLink.setOnAction(e -> RegisterPage.show(stage));

        VBox layout = new VBox(12,
                title,
                roleBox,
                username,
                password,
                loginBtn,
                cancelBtn,
                registerLink,
                msg
        );

        layout.setAlignment(Pos.CENTER);
        layout.setStyle("""
            -fx-padding: 25;
            -fx-background-color: linear-gradient(to bottom, #e0f7fa, #ffffff);
        """);

        layout.setMaxWidth(300);

        stage.setScene(new Scene(layout, 400, 450));
        stage.setTitle("Login");
        stage.show();
    }
}