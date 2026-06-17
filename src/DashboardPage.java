import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * DashboardPage - show user info and navigation.
 * Construct with (name, email, enrollment).
 */
public class DashboardPage {

    private final String name;
    private final String email;
    private final String enrollment;

    public DashboardPage(String name, String email, String enrollment) {
        this.name = name;
        this.email = email;
        this.enrollment = enrollment;
    }

    public Scene getDashboardScene(Stage stage) {

        Label title = new Label("📚 Library Dashboard");
        title.setStyle("-fx-font-size: 28px; -fx-text-fill: white; -fx-font-weight: bold;");

        Label welcome = new Label("Welcome, " + name + "   (Enrollment NO: " + enrollment + ")");
        welcome.setStyle("-fx-font-size: 16px; -fx-text-fill: #eeeeee;");

        Button issueBtn = new Button("Issue Book");
        Button returnBtn = new Button("Return Book");
        Button searchBtn = new Button("Search Book");
        Button myIssuedBtn = new Button("My Issued Books");
        Button logoutBtn = new Button("Logout");

        issueBtn.setPrefWidth(260);
        returnBtn.setPrefWidth(260);
        searchBtn.setPrefWidth(260);
        myIssuedBtn.setPrefWidth(260);
        logoutBtn.setPrefWidth(260);

        issueBtn.setStyle(btnStyle());
        returnBtn.setStyle(btnStyle());
        searchBtn.setStyle(btnStyle());
        myIssuedBtn.setStyle(btnStyle());
        logoutBtn.setStyle(
                "-fx-background-color: #ff3b3b; -fx-text-fill: white; -fx-padding: 10; -fx-background-radius: 12;");

        // Navigation (pass enrollment where needed)
        issueBtn.setOnAction(e -> stage.setScene(new IssueBookPage(enrollment).getIssueScene(stage)));
        returnBtn.setOnAction(e -> stage.setScene(new ReturnBookPage(enrollment).getReturnScene(stage)));
        searchBtn.setOnAction(e -> stage.setScene(new SearchBookPage(enrollment).getSearchScene(stage)));
        myIssuedBtn.setOnAction(e -> {

            Label titleLbl = new Label("📖 My Issued Books");
            titleLbl.setStyle(
                    "-fx-font-size: 26px;"
                            + "-fx-text-fill: white;"
                            + "-fx-font-weight: bold;");

            javafx.scene.control.TextArea ta = new javafx.scene.control.TextArea();
            ta.setEditable(false);
            ta.setPrefSize(500, 260);
            ta.setWrapText(true);
            ta.setStyle(
                    "-fx-control-inner-background: rgba(255,255,255,0.9);"
                            + "-fx-text-fill: black;"
                            + "-fx-font-size: 14px;"
                            + "-fx-background-radius: 15;");

            StringBuilder sb = new StringBuilder();
            for (String rec : BookManager.listIssuedFor(enrollment)) {
                sb.append("• ").append(rec).append("\n");
            }

            if (sb.length() == 0) {
                sb.append("No books issued yet.");
            }

            ta.setText(sb.toString());

            Button back = new Button("⬅ Back to Dashboard");
            back.setPrefWidth(240);
            back.setStyle(
                    "-fx-background-color: #00d4ff;"
                            + "-fx-text-fill: black;"
                            + "-fx-padding: 12;"
                            + "-fx-font-size: 14px;"
                            + "-fx-background-radius: 20;");

            back.setOnAction(ev -> stage.setScene(getDashboardScene(stage)));

            VBox v = new VBox(20, titleLbl, ta, back);
            v.setAlignment(Pos.CENTER);
            v.setPadding(new Insets(30));
            v.setStyle(
                    "-fx-background-color: linear-gradient(to bottom right, #3c1053, #ad5389);");

            stage.setScene(new Scene(v, 700, 500));
        });

        logoutBtn.setOnAction(e -> stage.setScene(new LoginPage().getLoginScene(stage)));

        VBox root = new VBox(15, title, welcome, issueBtn, returnBtn, searchBtn, myIssuedBtn, logoutBtn);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #3c1053, #ad5389);");

        return new Scene(root, 700, 500);
    }

    private String btnStyle() {
        return "-fx-background-color: #00d4ff; -fx-text-fill: black; -fx-padding: 12; -fx-background-radius: 18; -fx-font-size: 14px;";
    }
}
