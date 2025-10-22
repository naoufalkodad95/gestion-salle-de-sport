import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Scanner;

public class CoursCollectif {
    private int idCoursCollectif;
    private String nomCoursCollectif;
    private String descriptionCoursCollectif;
    private Time heureDebutCoursCollectif;
    private Time heureFinCoursCollectif;
    private String joursSemCoursCollectif;

    public CoursCollectif(int idCoursCollectif, String nomCoursCollectif, String descriptionCoursCollectif, Time heureDebutCoursCollectif, Time heureFinCoursCollectif, String joursSemCoursCollectif) {
        this.idCoursCollectif = idCoursCollectif;
        this.nomCoursCollectif = nomCoursCollectif;
        this.descriptionCoursCollectif = descriptionCoursCollectif;
        this.heureDebutCoursCollectif = heureDebutCoursCollectif;
        this.heureFinCoursCollectif = heureFinCoursCollectif;
        this.joursSemCoursCollectif = joursSemCoursCollectif;
    }

    public CoursCollectif() {

    }

    public void ajouterCours() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nom du cours : ");
        String nomCours = scanner.nextLine();

        System.out.print("Description du cours : ");
        String description = scanner.nextLine();

        System.out.print("Heure de début (HH:mm:ss) : ");
        String heureDebutStr = scanner.nextLine();
        Time heureDebut = Time.valueOf(heureDebutStr);

        System.out.print("Heure de fin (HH:mm:ss) : ");
        String heureFinStr = scanner.nextLine();
        Time heureFin = Time.valueOf(heureFinStr);

        System.out.print("Jours de la semaine : ");
        String joursSem = scanner.nextLine();

        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                String query = "INSERT INTO CoursCollectifs (nomCours, descriptionCours, heureDebutCours, heureFinCours, joursSemCours) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                    pstmt.setString(1, nomCours);
                    pstmt.setString(2, description);
                    pstmt.setTime(3, heureDebut);
                    pstmt.setTime(4, heureFin);
                    pstmt.setString(5, joursSem);

                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("Cours collectif ajouté avec succès.");
                    } else {
                        System.out.println("Aucun cours collectif ajouté.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du cours collectif: " + e.getMessage());
        }
    }

    public void modifierCours() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ID du cours à modifier : ");
        int idCours = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nouveau nom du cours : ");
        String newNom = scanner.nextLine();

        System.out.print("Nouvelle description du cours : ");
        String newDescription = scanner.nextLine();

        System.out.print("Nouvelle heure de début (HH:mm:ss) : ");
        String newHeureDebutStr = scanner.nextLine();
        Time newHeureDebut = Time.valueOf(newHeureDebutStr);

        System.out.print("Nouvelle heure de fin (HH:mm:ss) : ");
        String newHeureFinStr = scanner.nextLine();
        Time newHeureFin = Time.valueOf(newHeureFinStr);

        System.out.print("Nouveaux jours de la semaine : ");
        String newJoursSem = scanner.nextLine();

        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                String query = "UPDATE CoursCollectifs SET nomCours = ?, descriptionCours = ?, heureDebutCours = ?, heureFinCours = ?, joursSemCours = ? WHERE idCours= ?";
                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                    pstmt.setString(1, newNom);
                    pstmt.setString(2, newDescription);
                    pstmt.setTime(3, newHeureDebut);
                    pstmt.setTime(4, newHeureFin);
                    pstmt.setString(5, newJoursSem);
                    pstmt.setInt(6, idCours);

                    int rowsUpdated = pstmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("Cours collectif modifié avec succès.");
                    } else {
                        System.out.println("Aucun cours collectif modifié.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification du cours collectif: " + e.getMessage());
        }
    }

    public void supprimerCours() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ID du cours à supprimer : ");
        int idCours = scanner.nextInt();
        scanner.nextLine();

        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                String query = "DELETE FROM CoursCollectifs WHERE idCours = ?";
                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                    pstmt.setInt(1, idCours);

                    int rowsDeleted = pstmt.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println("Cours collectif supprimé avec succès.");
                    } else {
                        System.out.println("Aucun cours collectif supprimé.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du cours collectif: " + e.getMessage());
        }
    }

    public void consulterCoursCollectif() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                String query = "SELECT * FROM CoursCollectifs";
                try (PreparedStatement pstmt = connection.prepareStatement(query);
                     ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        System.out.println("-------------------------------------------------");
                        System.out.println("ID: " + rs.getInt("idCours"));
                        System.out.println("Nom: " + rs.getString("nomCours"));
                        System.out.println("Description: " + rs.getString("descriptionCours"));
                        System.out.println("Heure de Début: " + rs.getTime("heureDebutCours"));
                        System.out.println("Heure de Fin: " + rs.getTime("heureFinCours"));
                        System.out.println("Jours de la Semaine: " + rs.getString("joursSemCours"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la consultation des cours collectifs: " + e.getMessage());
        }
    }
}
