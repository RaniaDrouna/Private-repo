package Vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AdminDashboardVue {

	private Stage stage;

	public AdminDashboardVue(Stage primaryStage) {
		this.stage = primaryStage;
		initializeUI();
	}

	private void initializeUI() {
		stage.setTitle("Admin Dashboard");

		// Header
		Label headerLabel = new Label("Admin Dashboard");
		headerLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #8C1C13;");

		// Buttons
		Button manageUsersBtn = createStyledButton("Manage Users");
		Button manageCinemasBtn = createStyledButton("Manage Cinemas");
		Button manageMoviesBtn = createStyledButton("Manage Movies");
		Button manageScreeningsBtn = createStyledButton("Manage Screenings");
		Button viewReportsBtn = createStyledButton("View Reports");
		Button logoutBtn = createStyledButton("Logout", "-fx-background-color: #BF4342;");

		// Layout
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(25, 25, 25, 25));

		grid.add(manageUsersBtn, 0, 0);
		grid.add(manageCinemasBtn, 1, 0);
		grid.add(manageMoviesBtn, 0, 1);
		grid.add(manageScreeningsBtn, 1, 1);
		grid.add(viewReportsBtn, 0, 2);
		grid.add(logoutBtn, 1, 2);

		VBox mainLayout = new VBox(20);
		mainLayout.setAlignment(Pos.CENTER);
		mainLayout.setPadding(new Insets(20));
		mainLayout.getChildren().addAll(headerLabel, grid);

		Scene scene = new Scene(mainLayout, 800, 600, Color.web("#F5F5F5"));
		stage.setScene(scene);
	}

	private Button createStyledButton(String text) {
		return createStyledButton(text, "-fx-background-color: #8C1C13;");
	}

	private Button createStyledButton(String text, String style) {
		Button button = new Button(text);
		button.setStyle(style + " -fx-text-fill: white; -fx-padding: 10px 20px; -fx-font-size: 14px; -fx-border-radius: 5px;");
		button.setPrefWidth(200);
		return button;
	}

	public void show() {
		stage.show();
	}
}