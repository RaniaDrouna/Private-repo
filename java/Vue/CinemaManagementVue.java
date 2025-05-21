package Vue;

import Model.Cinema;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CinemaManagementVue {

    private Stage stage;
    private ObservableList<Cinema> cinemas;

    public CinemaManagementVue(Stage primaryStage, ObservableList<Cinema> cinemas) {
        this.stage = primaryStage;
        this.cinemas = cinemas;
        initializeUI();
    }

    private void initializeUI() {
        stage.setTitle("Cinema Management");

        // Table for cinemas
        TableView<Cinema> cinemaTable = new TableView<>();
        cinemaTable.setItems(cinemas);

        TableColumn<Cinema, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("idCinema"));

        TableColumn<Cinema, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("nomCinema"));

        TableColumn<Cinema, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        TableColumn<Cinema, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("numeroTelephone"));

        TableColumn<Cinema, Integer> roomsCol = new TableColumn<>("Rooms");
        roomsCol.setCellValueFactory(new PropertyValueFactory<>("nombreSalle"));

        cinemaTable.getColumns().addAll(idCol, nameCol, addressCol, phoneCol, roomsCol);

        // Action buttons
        Button addBtn = new Button("Add Cinema");
        addBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        Button editBtn = new Button("Edit");
        editBtn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

        Button deleteBtn = new Button("Delete");
        deleteBtn.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: #BF4342; -fx-text-fill: white;");

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(addBtn, editBtn, deleteBtn, backBtn);

        // Form for adding/editing
        VBox form = new VBox(10);
        form.setPadding(new Insets(15));
        form.setStyle("-fx-background-color: #E7D7C1;");

        TextField nameField = new TextField();
        nameField.setPromptText("Cinema Name");

        TextField addressField = new TextField();
        addressField.setPromptText("Address");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");

        Spinner<Integer> roomsSpinner = new Spinner<>(1, 20, 1);

        Button saveBtn = new Button("Save");
        saveBtn.setStyle("-fx-background-color: #8C1C13; -fx-text-fill: white;");

        form.getChildren().addAll(
                new Label("Name:"), nameField,
                new Label("Address:"), addressField,
                new Label("Phone:"), phoneField,
                new Label("Number of Rooms:"), roomsSpinner,
                saveBtn
        );

        // Main layout
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.getChildren().addAll(cinemaTable, buttonBox, form);

        Scene scene = new Scene(mainLayout, 800, 600, Color.web("#F5F5F5"));
        stage.setScene(scene);
    }

    public void show() {
        stage.show();
    }
}