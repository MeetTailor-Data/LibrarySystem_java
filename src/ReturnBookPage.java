import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReturnBookPage {

    private final String enrollment;

    public ReturnBookPage(String enrollment) {
        this.enrollment = enrollment;
    }

    public Scene getReturnScene(Stage stage) {

        Label title = new Label("↩ Return Book");
        title.setStyle(
                "-fx-font-size: 24px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #8e44ad;");

        TextField bookField = new TextField();
        bookField.setPromptText("Enter Book ID");

        Button returnBtn = new Button("Return");
        Button backBtn = new Button("Back");

        returnBtn.setOnAction(e -> {
            String bookId = bookField.getText().trim();

            if (bookId.isEmpty()) {
                System.out.println("Enter Book ID");
                return;
            }

            boolean ok = BookManager.returnBook(enrollment, bookId);

            if (ok)
                System.out.println("✔ Book Returned");
            else
                System.out.println("❌ Book Not Issued OR Invalid ID");
        });

        backBtn.setOnAction(e -> stage.setScene(new DashboardPage(
                UserManager.getNameByEnrollment(enrollment),
                UserManager.getEmailByEnrollment(enrollment),
                enrollment).getDashboardScene(stage)));

        returnBtn.setStyle(
                "-fx-background-color: #2980b9;" +
                        "-fx-text-fill: white;");

        VBox root = new VBox(12, title, bookField, returnBtn, backBtn);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #ecf0f1;");
        root.setPadding(new Insets(20));

        return new Scene(root, 500, 400);
    }
}
