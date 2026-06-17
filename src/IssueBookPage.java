import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IssueBookPage {

    private final String enrollment;

    public IssueBookPage(String enrollment) {
        this.enrollment = enrollment;
    }

    public Scene getIssueScene(Stage stage) {

        Label title = new Label("📚 Issue Book");
        title.setStyle(
                "-fx-font-size: 24px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #86b5e4ff;");

        TextField bookField = new TextField();
        bookField.setPromptText("Enter Book ID");

        Label message = new Label();
        message.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

        Label info = new Label("Available Books: " + BookManager.listAllBooks());
        info.setStyle("-fx-text-fill: gray;");

        Button issueBtn = new Button("Issue");
        Button backBtn = new Button("Back");

        issueBtn.setOnAction(e -> {
            String bookId = bookField.getText().trim();

            if (bookId.isEmpty()) {
                message.setText("❗ Please enter Book ID");
                return;
            }

            boolean ok = BookManager.issueBook(enrollment, bookId);

            if (ok) {
                message.setText("✔ Book Issued Successfully");
                bookField.clear();
            } else {
                message.setText("❌ Invalid Book ID or Already Issued");
            }
        });
        issueBtn.setStyle(
                "-fx-background-color: #27ae60;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-padding: 8 20 8 20;");

        backBtn.setStyle(
                "-fx-background-color: #c0392b;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-padding: 8 20 8 20;");

        backBtn.setOnAction(e -> stage.setScene(new DashboardPage(
                UserManager.getNameByEnrollment(enrollment),
                UserManager.getEmailByEnrollment(enrollment),
                enrollment).getDashboardScene(stage)));

        VBox root = new VBox(12, title, bookField, issueBtn, message, backBtn);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #3c1053, #ad5389);");

        return new Scene(root, 500, 400);
    }
}
