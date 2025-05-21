package Vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class BookingConfirmationVue {

    private Stage stage;

    public BookingConfirmationVue(Stage primaryStage) {
        this.stage = primaryStage;
        initializeUI();
    }

    private void initializeUI() {
        stage.setTitle("Booking Confirmation");

        // Success icon
        ImageView successIcon = new ImageView(new Image(getClass().getResourceAsStream("/success.png")));
        successIcon.setFitWidth(100);
        successIcon.setFitHeight(100);

        // Confirmation message
        Label confirmationLabel = new Label("Booking Confirmed!");
        confirmationLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        confirmationLabel.setTextFill(Color.web("#8C1C13"));

        // Booking details
        VBox detailsBox = new VBox(10);
        detailsBox.setPadding(new Insets(15));
        detailsBox.setStyle("-fx-background-color: #E7D7C1; -fx-border-radius: 5px;");

        detailsBox.getChildren().addAll(
                new Label("Movie: Inception"),
                new Label("Cinema: Cinema Lumière"),
                new Label("Date: 15/06/2025 18:00"),
                new Label("Seats: A3, A4"),
                new Label("Booking Reference: ABC123XYZ")
        );

        // Action button
        Button doneBtn = new Button("Done");
        doneBtn.setStyle("-fx-background-color: #8C1C13; -fx-text-fill: white; -fx-padding: 10px 30px;");

        // Main layout
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(successIcon, confirmationLabel, detailsBox, doneBtn);

        Scene scene = new Scene(mainLayout, 500, 500, Color.web("#F5F5F5"));
        stage.setScene(scene);
    }

    public void show() {
        stage.show();
    }
}