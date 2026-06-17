import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginPage {

    public Scene getLoginScene(Stage stage) {

        // Title
        Label title = new Label(" Library Login");
        title.setStyle("-fx-font-size: 32px; -fx-text-fill: white; -fx-font-weight: bold;");

        // Email
        TextField emailField = new TextField();
        emailField.setPromptText("Enter Email");
        emailField.setMaxWidth(280);
        emailField.setStyle(
                "-fx-background-color: rgba(255,255,255,0.2);"
                        + "-fx-text-fill: white;"
                        + "-fx-prompt-text-fill: #eeeeee;"
                        + "-fx-padding: 10;"
                        + "-fx-background-radius: 15;"
                        + "-fx-border-radius: 15;");

        // Password
        PasswordField passField = new PasswordField();
        passField.setPromptText("Enter Password");
        passField.setMaxWidth(280);
        passField.setStyle(
                "-fx-background-color: rgba(255,255,255,0.2);"
                        + "-fx-text-fill: white;"
                        + "-fx-prompt-text-fill: #eeeeee;"
                        + "-fx-padding: 10;"
                        + "-fx-background-radius: 15;"
                        + "-fx-border-radius: 15;");

        // Login Button
        Button loginBtn = new Button("Login");
        loginBtn.setPrefWidth(280);
        loginBtn.setStyle(
                "-fx-background-color: #00d4ff;"
                        + "-fx-text-fill: black;"
                        + "-fx-padding: 12;"
                        + "-fx-font-size: 15px;"
                        + "-fx-background-radius: 20;");

loginBtn.setOnAction(e -> {
    String email = emailField.getText().trim();
    String pass = passField.getText();

    if (email.isEmpty() || pass.isEmpty()) {
        System.out.println("⚠ Enter email & password.");
        return;
    }

    String username = UserManager.loginUser(email, pass);

    if (username != null) {
        System.out.println("✔ Login Successful!");

        // 🔥 USE EMAIL TO GET ENROLLMENT (ALREADY EXISTS)
        String enrollment = UserManager.getEnrollmentByEmail(email);

        stage.setScene(
            new DashboardPage(username, email, enrollment)
                .getDashboardScene(stage)
        );

    } else {
        System.out.println("❌ Invalid Credentials");
    }
});




        loginBtn.setOnMouseEntered(e -> loginBtn.setStyle(
                "-fx-background-color: #05f7ff;"
                        + "-fx-text-fill: black;"
                        + "-fx-padding: 12;"
                        + "-fx-font-size: 15px;"
                        + "-fx-background-radius: 20;"));

        loginBtn.setOnMouseExited(e -> loginBtn.setStyle(
                "-fx-background-color: #00d4ff;"
                        + "-fx-text-fill: black;"
                        + "-fx-padding: 12;"
                        + "-fx-font-size: 15px;"
                        + "-fx-background-radius: 20;"));

        // Register Button
        Button registerBtn = new Button("Create Account");
        registerBtn.setPrefWidth(280);
        registerBtn.setStyle(
                "-fx-background-color: transparent;"
                        + "-fx-border-color: white;"
                        + "-fx-border-radius: 20;"
                        + "-fx-text-fill: white;"
                        + "-fx-padding: 10;");

        registerBtn.setOnAction(e -> {
            RegPage reg = new RegPage();
            stage.setScene(reg.getRegScene(stage));
        });

        // Layout
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));
        root.getChildren().addAll(title, emailField, passField, loginBtn, registerBtn);

        // Background gradient
        root.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #3c1053, #ad5389);"
                        + "-fx-font-family: 'Arial';");

        return new Scene(root, 450, 500);
    }
}
