import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegPage {

        public Scene getRegScene(Stage stage) {

                // Title
                Label title = new Label("📘 Create Account");
                title.setStyle("-fx-font-size: 32px; -fx-text-fill: white; -fx-font-weight: bold;");

                // Name
                TextField nameField = new TextField();
                nameField.setPromptText("Full Name");
                nameField.setMaxWidth(280);
                nameField.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.2);" +
                                                "-fx-text-fill: white;" +
                                                "-fx-prompt-text-fill: #eeeeee;" +
                                                "-fx-padding: 10;" +
                                                "-fx-background-radius: 15;");

                // Email
                TextField emailField = new TextField();
                emailField.setPromptText("Email");
                emailField.setMaxWidth(280);
                emailField.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.2);" +
                                                "-fx-text-fill: white;" +
                                                "-fx-prompt-text-fill: #eeeeee;" +
                                                "-fx-padding: 10;" +
                                                "-fx-background-radius: 15;");

                // Enrollment Number
                TextField enrollField = new TextField();
                enrollField.setPromptText("Enrollment Number");
                enrollField.setMaxWidth(280);
                enrollField.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.2);" +
                                                "-fx-text-fill: white;" +
                                                "-fx-prompt-text-fill: #eeeeee;" +
                                                "-fx-padding: 10;" +
                                                "-fx-background-radius: 15;");

                // Password
                PasswordField passField = new PasswordField();
                passField.setPromptText("Create Password");
                passField.setMaxWidth(280);
                passField.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.2);" +
                                                "-fx-text-fill: white;" +
                                                "-fx-prompt-text-fill: #eeeeee;" +
                                                "-fx-padding: 10;" +
                                                "-fx-background-radius: 15;");

                // Confirm Password
                PasswordField confirmField = new PasswordField();
                confirmField.setPromptText("Confirm Password");
                confirmField.setMaxWidth(280);
                confirmField.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.2);" +
                                                "-fx-text-fill: white;" +
                                                "-fx-prompt-text-fill: #eeeeee;" +
                                                "-fx-padding: 10;" +
                                                "-fx-background-radius: 15;");

                // Register Button
                Button registerBtn = new Button("Register");
                registerBtn.setPrefWidth(280);
                registerBtn.setStyle(
                                "-fx-background-color: #00d4ff;" +
                                                "-fx-text-fill: black;" +
                                                "-fx-padding: 12;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-background-radius: 20;");

                registerBtn.setOnAction(e -> {
                        String name = nameField.getText().trim();
                        String email = emailField.getText().trim();
                        String enroll = enrollField.getText().trim();
                        String pass = passField.getText();
                        String confirm = confirmField.getText();

                        if (name.isEmpty() || email.isEmpty() || enroll.isEmpty() || pass.isEmpty()) {
                                System.out.println("⚠ Please fill all details.");
                                return;
                        }

                        if (!pass.equals(confirm)) {
                                System.out.println("⚠ Passwords do not match.");
                                return;
                        }

                        boolean ok = UserManager.registerUser(name, email, pass, enroll);

                        if (ok) {
                                System.out.println("✔ Account Created Successfully!");
                                LoginPage login = new LoginPage();
                                stage.setScene(login.getLoginScene(stage));
                        } else {
                                System.out.println("❌ Error creating account.");
                        }
                });

                // Back to Login Button
                Button backBtn = new Button("Back to Login");
                backBtn.setPrefWidth(280);
                backBtn.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-border-color: white;" +
                                                "-fx-border-radius: 20;" +
                                                "-fx-text-fill: white;" +
                                                "-fx-padding: 10;");

                backBtn.setOnAction(e -> {
                        LoginPage login = new LoginPage();
                        stage.setScene(login.getLoginScene(stage));
                });

                // Layout
                VBox root = new VBox(15);
                root.setAlignment(Pos.CENTER);
                root.setPadding(new Insets(30));
                root.setStyle(
                                "-fx-background-color: linear-gradient(to bottom right, #3c1053, #ad5389);");

                root.getChildren().addAll(
                                title,
                                nameField,
                                emailField,
                                enrollField,
                                passField,
                                confirmField,
                                registerBtn,
                                backBtn);

                return new Scene(root, 450, 600);
        }
}
