import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Authentification {

    public static Utilisateur authentifier(String cin, String motDePasse, String typeUtilisateur) {
        String tableName = getTableName(typeUtilisateur);
        if (tableName == null) {
            System.out.println("Type d'utilisateur inconnu.");
            return null;
        }

        String query = "SELECT * FROM " + tableName + "s WHERE cin" + tableName + " = ? AND motDePasse" + tableName + " = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, cin);
            pstmt.setString(2, motDePasse);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id" + tableName);
                    String nom = rs.getString("nom" + tableName);
                    String prenom = rs.getString("prenom" + tableName);
                    String genre = rs.getString("genre" + tableName);
                    Date dateNaissance = rs.getDate("dateNaissance" + tableName);
                    String email = rs.getString("email" + tableName);
                    String telephone = rs.getString("telephone" + tableName);
                    String adresse = rs.getString("adresse" + tableName);
                    String motDePasseDb = rs.getString("motDePasse" + tableName);


                    switch (typeUtilisateur.toLowerCase()) {
                        case "responsable":
                            return new Responsable(id, cin, nom, prenom, genre, dateNaissance, email, telephone, adresse, motDePasseDb);
                        case "membre":
                            return new Membre(id, cin, nom, prenom, genre, dateNaissance, email, telephone, adresse, motDePasseDb);
                        case "entraineur":
                            return new Entraineur(id, cin, nom, prenom, genre, dateNaissance, email, telephone, adresse, motDePasseDb);
                        default:
                            return null;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'authentification : " + e.getMessage());
        }
        return null;
    }

    private static String getTableName(String userType) {
        switch (userType.toLowerCase()) {
            case "responsable":
                return "Responsable";
            case "membre":
                return "Membre";
            case "entraineur":
                return "Entraineur";
            default:
                return null;
        }
    }
}
