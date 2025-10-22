import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Scanner;

public class Reservation {
    private int idReservation;
    private String etatReservation;
    private Date dateReservation;

    public Reservation(int idReservation, String etatReservation, Date dateReservation) {
        this.idReservation = idReservation;
        this.etatReservation = etatReservation;
        this.dateReservation = dateReservation;
    }

    public Reservation() {

    }

    public void effectuerReservation(int idMembre) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ID du cours : ");
        int idCours = scanner.nextInt();
        scanner.nextLine();



        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                String query = "INSERT INTO Reservations (dateReservation, idCours, idMembre) VALUES (CURRENT_DATE(),?, ?)";
                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                    pstmt.setInt(1, idCours);
                    pstmt.setInt(2, idMembre);

                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("Réservation effectuée avec succès.");
                    } else {
                        System.out.println("Aucune réservation effectuée.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la réservation: " + e.getMessage());
        }
    }

    public void annulerReservation() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ID de la réservation à annuler : ");
        int idReservation = scanner.nextInt();
        scanner.nextLine();

        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                String query = "DELETE FROM Reservations WHERE idReservation = ?";
                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                    pstmt.setInt(1, idReservation);

                    int rowsDeleted = pstmt.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println("Réservation annulée avec succès.");
                    } else {
                        System.out.println("Aucune réservation annulée.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'annulation de la réservation: " + e.getMessage());
        }
    }
}
