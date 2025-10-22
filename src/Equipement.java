import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Equipement {
    private int idEquipement;
    private String typeEquipement;
    private String etatEquipement;
    private boolean disponibiliteEquipement;

    public Equipement(int idEquipement, String typeEquipement, String etatEquipement, boolean disponibiliteEquipement) {
        this.idEquipement = idEquipement;
        this.typeEquipement = typeEquipement;
        this.etatEquipement = etatEquipement;
        this.disponibiliteEquipement = disponibiliteEquipement;
    }

    public Equipement() {

    }

    public void ajouterEquipement(int idResponsable) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Type d'équipement : ");
        String typeEquipement = scanner.nextLine();

        System.out.print("État de l'équipement : ");
        String etatEquipement = scanner.nextLine();

        System.out.print("Disponibilité de l'équipement (true/false) : ");
        boolean disponibiliteEquipement = scanner.nextBoolean();

        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                String query = "INSERT INTO Equipements (typeEquipement, etatEquipement, disponibiliteEquipement,idResponsable) VALUES (?,?, ?, ?)";
                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                    pstmt.setString(1, typeEquipement);
                    pstmt.setString(2, etatEquipement);
                    pstmt.setBoolean(3, disponibiliteEquipement);
                    pstmt.setInt(4, idResponsable);

                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("Équipement ajouté avec succès.");
                    } else {
                        System.out.println("Aucun équipement ajouté.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'équipement: " + e.getMessage());
        }
    }

    public void supprimerEquipement() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ID de l'équipement à supprimer : ");
        int idEquipement = scanner.nextInt();
        scanner.nextLine(); // consume newline

        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                String query = "DELETE FROM Equipements WHERE idEquipement = ?";
                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                    pstmt.setInt(1, idEquipement);

                    int rowsDeleted = pstmt.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println("Équipement supprimé avec succès.");
                    } else {
                        System.out.println("Aucun équipement supprimé.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'équipement: " + e.getMessage());
        }
    }

    public void verifierDisponibilite() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ID de l'équipement à vérifier : ");
        int idEquipement = scanner.nextInt();
        scanner.nextLine(); // consume newline

        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                String query = "SELECT disponibiliteEquipement FROM Equipements WHERE idEquipement = ?";
                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                    pstmt.setInt(1, idEquipement);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            boolean disponibilite = rs.getBoolean("disponibiliteEquipement");
                            System.out.println("Disponibilité de l'équipement: " + disponibilite);
                        } else {
                            System.out.println("Aucun équipement trouvé avec cet ID.");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification de la disponibilité: " + e.getMessage());
        }
    }
    public void afficherListeEquipement() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                String query = "SELECT * FROM Equipements";
                try (PreparedStatement pstmt = connection.prepareStatement(query);
                     ResultSet rs = pstmt.executeQuery()) {
                    int i=0;
                    while (rs.next()) {
                        i++;
                        System.out.println("----------------------Equipement "+i+"---------------------------");
                        System.out.println("ID: " + rs.getInt("idEquipement"));
                        System.out.println("type: " + rs.getString("typeEquipement"));
                        System.out.println("etat: " + rs.getString("etatEquipement"));
                        System.out.println("disponibilite: " + rs.getString("disponibiliteEquipement"));

                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage de la liste des Equipement: " + e.getMessage());
        }
    }
}
