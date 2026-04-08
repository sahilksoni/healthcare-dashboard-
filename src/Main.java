import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        LoginPage.show(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}