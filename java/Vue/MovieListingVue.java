package Vue;

import Model.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class MovieListingVue {

    private Stage stage;
    private List<Film> films;

    public MovieListingVue(Stage primaryStage, List<Film> films) {
        this.stage = primaryStage;
        this.films = films;
        initializeUI();
    }

    private void initializeUI() {
        stage.setTitle("Now Showing");

        // Header
        Label headerLabel = new Label("Now Showing");
        headerLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #8C1C13;");

        // Back button
        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: #BF4342; -fx-text-fill: white; -fx-padding: 5px 15px;");

        // Create movie cards
        VBox movieCardsContainer = new VBox(20);
        movieCardsContainer.setPadding(new Insets(20));
        movieCardsContainer.setAlignment(Pos.TOP_CENTER);

        for (Film film : films) {
            HBox movieCard = createMovieCard(film);
            movieCardsContainer.getChildren().add(movieCard);
        }

        // Main layout
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.getChildren().addAll(headerLabel, movieCardsContainer, backBtn);
        mainLayout.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(mainLayout, 800, 600, Color.web("#F5F5F5"));
        stage.setScene(scene);
    }

    private HBox createMovieCard(Film film) {
        // Movie poster (placeholder)
        ImageView poster = new ImageView(new Image("file:" + film.getImage()));
        poster.setFitWidth(150);
        poster.setFitHeight(200);
        poster.setPreserveRatio(true);

        // Movie details
        VBox details = new VBox(10);
        details.setPadding(new Insets(10));

        Label title = new Label(film.getTitre());
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label director = new Label("Director: " + film.getNomRealisateur());
        Label duration = new Label("Duration: " + film.getDuree() + " mins");
        Label rating = new Label("Rating: " + film.getNote() + "/10");

        Button bookBtn = new Button("Book Now");
        bookBtn.setStyle("-fx-background-color: #8C1C13; -fx-text-fill: white;");

        details.getChildren().addAll(title, director, duration, rating, bookBtn);

        // Combine into card
        HBox card = new HBox(20);
        card.setStyle("-fx-background-color: white; -fx-padding: 15px; -fx-border-radius: 5px;");
        card.setAlignment(Pos.CENTER_LEFT);
        card.getChildren().addAll(poster, details);

        return card;
    }

    public void show() {
        stage.show();
    }
}