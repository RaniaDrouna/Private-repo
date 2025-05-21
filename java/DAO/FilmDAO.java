package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
 
import Model.Film;

public class FilmDAO {
    private Connection connection;

    public FilmDAO(Connection connection) {
        this.connection = connection;
    }

    // Ajouter un film
    public boolean ajouterFilm(Film film) {
        String sql = "INSERT INTO film (Titre, Duree, Image, Date_Sortie, Limite_Age, Nom_Realisateur, Note, Description, Genre, NIN) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, film.getTitre());
            stmt.setInt(2, film.getDuree());
            stmt.setString(3, film.getImage());
            stmt.setDate(4, new Date(film.getDateSortie().getTime()));
            stmt.setInt(5, film.getLimiteAge());
            stmt.setString(6, film.getNomRealisateur());
            stmt.setFloat(7, film.getNote());
            stmt.setString(8, film.getDescription());
            stmt.setString(9, film.getGenre());
            stmt.setString(10, film.getNIN());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Récupérer un film par son ID
    public Film getFilmById(int idFilm) {
        String sql = "SELECT * FROM film WHERE id_Film = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idFilm);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Film(
                    rs.getInt("id_Film"),
                    rs.getString("Titre"),
                    rs.getInt("Duree"),
                    rs.getString("Image"),
                    rs.getDate("Date_Sortie"),
                    rs.getInt("Limite_Age"),
                    rs.getString("Nom_Realisateur"),
                    rs.getFloat("Note"),
                    rs.getString("Description"),
                    rs.getString("Genre"),
                    rs.getString("NIN")
                    
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // rechercher des films en fonction de son titre 
    public List<Film> rechercherFilmsParTitre(String titre) {
        List<Film> films = new ArrayList<>();
        String sql = "SELECT * FROM film WHERE Titre LIKE ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + titre + "%"); // Recherche partielle sur le titre
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Film film = new Film(
                    rs.getInt("id_Film"),
                    rs.getString("Titre"),
                    rs.getInt("Duree"),
                    rs.getString("Image"),
                    rs.getDate("Date_Sortie"),
                    rs.getInt("Limite_Age"),
                    rs.getString("Nom_Realisateur"),
                    rs.getFloat("Note"),
                    rs.getString("Description"),
                    rs.getString("Genre"),
                    rs.getString("NIN")
                    
                );
                films.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return films;
    }

    //rechercher des films en fonction de son genre 
    public List<Film> rechercherFilmsParGenre(String genre) {
        List<Film> films = new ArrayList<>();
        String sql = "SELECT * FROM film WHERE Genre LIKE ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + genre + "%"); // Recherche partielle sur le genre
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Film film = new Film(
                    rs.getInt("id_Film"),
                    rs.getString("Titre"),
                    rs.getInt("Duree"),
                    rs.getString("Image"),
                    rs.getDate("Date_Sortie"),
                    rs.getInt("Limite_Age"),
                    rs.getString("Nom_Realisateur"),
                    rs.getFloat("Note"),
                    rs.getString("Description"),
                    rs.getString("Genre"),
                    rs.getString("NIN")
                   
                );
                films.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return films;
    }

    // Récupérer tous les films
    public List<Film> getAllFilms() {
        List<Film> films = new ArrayList<>();
        String sql = "SELECT * FROM film";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                films.add(new Film(
                    rs.getInt("id_Film"),
                    rs.getString("Titre"),
                    rs.getInt("Duree"),
                    rs.getString("Image"),
                    rs.getDate("Date_Sortie"),
                    rs.getInt("Limite_Age"),
                    rs.getString("Nom_Realisateur"),
                    rs.getFloat("Note"),
                    rs.getString("Description"),
                    rs.getString("Genre"),
                    rs.getString("NIN")
                    
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }

    // Mettre à jour un film
    public boolean mettreAJourFilm(Film film) {
        String sql = "UPDATE film SET Titre=?, Duree=?, Image=?, Date_Sortie=?, Limite_Age=?, Nom_Realisateur=?, Note=?, Description=?, id_Utilisateur=?, Genre=? WHERE id_Film=?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, film.getTitre());
            stmt.setInt(2, film.getDuree());
            stmt.setString(3, film.getImage());
            stmt.setDate(4, new Date(film.getDateSortie().getTime()));
            stmt.setInt(5, film.getLimiteAge());
            stmt.setString(6, film.getNomRealisateur());
            stmt.setFloat(7, film.getNote());
            stmt.setString(8, film.getDescription());
            stmt.setString(9, film.getNIN());
            stmt.setString(10, film.getGenre());
            stmt.setInt(11, film.getIdFilm());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //Retourne la moyenne des notes des commentaires d’un film
    public double getMoyenneNoteParFilm(int idFilm) {
        double moyenne = 0;
        String sql = "SELECT AVG(note) FROM Commentaire WHERE idFilm = ?";
        
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, idFilm);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                moyenne = rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return moyenne;
    }


    // Supprimer un film par id
    public boolean supprimerFilm(int idFilm) {
        String sql = "DELETE FROM film WHERE id_Film = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idFilm);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

