import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchBookPage {

    private final String enrollment;

    public SearchBookPage(String enrollment) {
        this.enrollment = enrollment;
    }

    public Scene getSearchScene(Stage stage) {

        Label title = new Label("🔍 Search Book");
        TextField searchField = new TextField();
        searchField.setPromptText("Enter Book Name or ID");

        TextArea result = new TextArea();
        result.setEditable(false);
        result.setPrefHeight(200);

        Button searchBtn = new Button("Search");
        Button backBtn = new Button("Back");

        searchBtn.setOnAction(e -> {
            String q = searchField.getText().trim();
            result.setText(BookManager.searchBook(q));
        });

        backBtn.setOnAction(e -> stage.setScene(new DashboardPage(
                UserManager.getNameByEnrollment(enrollment),
                UserManager.getEmailByEnrollment(enrollment),
                enrollment).getDashboardScene(stage)));

        VBox root = new VBox(12, title, searchField, searchBtn, result, backBtn);
        root.setAlignment(Pos.CENTER);

        root.setPadding(new Insets(20));

        return new Scene(root, 600, 450);
    }
}
