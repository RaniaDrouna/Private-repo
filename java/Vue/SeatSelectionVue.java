package Vue;

import Model.Salle;
import Model.Siege;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.List;

public class SeatSelectionVue {

    private Stage stage;
    private Salle salle;
    private List<Siege> sieges;

    public SeatSelectionVue(Stage primaryStage, Salle salle, List<Siege> sieges) {
        this.stage = primaryStage;
        this.salle = salle;
        this.sieges = sieges;
        initializeUI();
    }

    private void initializeUI() {
        stage.setTitle("Select Your Seats");

        // Header
        Label headerLabel = new Label("Select Seats for " + salle.getNom_Salle());
        headerLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #8C1C13;");

        // Screen representation
        Rectangle screen = new Rectangle(400, 20);
        screen.setFill(Color.SILVER);
        screen.setArcWidth(20);
        screen.setArcHeight(20);
        HBox screenBox = new HBox(screen);
        screenBox.setAlignment(Pos.CENTER);

        // Seat grid
        GridPane seatGrid = new GridPane();
        seatGrid.setHgap(10);
        seatGrid.setVgap(10);
        seatGrid.setAlignment(Pos.CENTER);
        seatGrid.setPadding(new Insets(20));

        // Add seats to grid
        for (Siege siege : sieges) {
            Button seatBtn = new Button(siege.getNom_Siege());
            seatBtn.setPrefSize(40, 40);

            if (siege.getStatut().equals("Reserver")) {
                seatBtn.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
                seatBtn.setDisable(true);
            } else {
                seatBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
            }

            seatGrid.add(seatBtn, siege.getNom_Siege().charAt(1) - '0', siege.getNom_Siege().charAt(0) - 'A');
        }

        // Action buttons
        Button confirmBtn = new Button("Confirm Selection");
        confirmBtn.setStyle("-fx-background-color: #8C1C13; -fx-text-fill: white; -fx-padding: 10px 20px;");

        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: #BF4342; -fx-text-fill: white; -fx-padding: 10px 20px;");

        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(backBtn, confirmBtn);

        // Legend
        HBox legend = new HBox(20);
        legend.setAlignment(Pos.CENTER);

        Rectangle availableSeat = new Rectangle(20, 20, Color.GREEN);
        Rectangle bookedSeat = new Rectangle(20, 20, Color.RED);

        legend.getChildren().addAll(
                new HBox(5, availableSeat, new Label("Available")),
                new HBox(5, bookedSeat, new Label("Booked"))
        );

        // Main layout
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.getChildren().addAll(headerLabel, screenBox, seatGrid, legend, buttonBox);

        Scene scene = new Scene(mainLayout, 800, 600, Color.web("#F5F5F5"));
        stage.setScene(scene);
    }

    public void show() {
        stage.show();
    }
}