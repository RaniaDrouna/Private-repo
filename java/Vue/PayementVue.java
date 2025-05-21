package Vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PayementVue {

    private Stage stage;

    public PayementVue(Stage primaryStage) {
        this.stage = primaryStage;
        initializeUI();
    }

    private void initializeUI() {
        stage.setTitle("Payment");

        // Header
        Label headerLabel = new Label("Payment Details");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        headerLabel.setTextFill(Color.web("#8C1C13"));

        // Booking summary
        VBox summaryBox = new VBox(10);
        summaryBox.setPadding(new Insets(15));
        summaryBox.setStyle("-fx-background-color: #E7D7C1; -fx-border-radius: 5px;");

        summaryBox.getChildren().addAll(
                new Label("Movie: Inception"),
                new Label("Cinema: Cinema Lumière"),
                new Label("Date: 15/06/2025 18:00"),
                new Label("Seats: A3, A4"),
                new Label("Total: $24.00")
        );

        // Payment form
        GridPane paymentForm = new GridPane();
        paymentForm.setHgap(10);
        paymentForm.setVgap(10);
        paymentForm.setPadding(new Insets(15));
        paymentForm.setAlignment(Pos.CENTER);

        ToggleGroup paymentMethod = new ToggleGroup();
        RadioButton creditCard = new RadioButton("Credit Card");
        creditCard.setToggleGroup(paymentMethod);
        RadioButton paypal = new RadioButton("PayPal");
        paypal.setToggleGroup(paymentMethod);
        RadioButton edahabia = new RadioButton("Edahabia");
        edahabia.setToggleGroup(paymentMethod);
        creditCard.setSelected(true);

        TextField cardNumber = new TextField();
        cardNumber.setPromptText("Card Number");

        TextField cardName = new TextField();
        cardName.setPromptText("Cardholder Name");

        TextField expiryDate = new TextField();
        expiryDate.setPromptText("MM/YY");

        TextField cvv = new TextField();
        cvv.setPromptText("CVV");

        paymentForm.add(new Label("Payment Method:"), 0, 0);
        paymentForm.add(creditCard, 1, 0);
        paymentForm.add(paypal, 2, 0);
        paymentForm.add(edahabia, 3, 0);

        paymentForm.add(new Label("Card Number:"), 0, 1);
        paymentForm.add(cardNumber, 1, 1, 3, 1);

        paymentForm.add(new Label("Name on Card:"), 0, 2);
        paymentForm.add(cardName, 1, 2, 3, 1);

        paymentForm.add(new Label("Expiry Date:"), 0, 3);
        paymentForm.add(expiryDate, 1, 3);

        paymentForm.add(new Label("CVV:"), 2, 3);
        paymentForm.add(cvv, 3, 3);

        // Action buttons
        Button payBtn = new Button("Pay Now");
        payBtn.setStyle("-fx-background-color: #8C1C13; -fx-text-fill: white; -fx-padding: 10px 30px;");

        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: #BF4342; -fx-text-fill: white; -fx-padding: 10px 30px;");

        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(backBtn, payBtn);

        // Main layout
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.getChildren().addAll(headerLabel, summaryBox, paymentForm, buttonBox);

        Scene scene = new Scene(mainLayout, 600, 500, Color.web("#F5F5F5"));
        stage.setScene(scene);
    }

    public void show() {
        stage.show();
    }
}