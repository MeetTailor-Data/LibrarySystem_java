import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        LoginPage login = new LoginPage();
        stage.setScene(login.getLoginScene(stage));
        stage.setTitle("Library Management System");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}