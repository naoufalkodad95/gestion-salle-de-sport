import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuEntraineur {
    public static void MenuEntraineur(Entraineur entraineur) {
        CoursCollectif cours = new CoursCollectif();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("-----------------------Menu Entraineur--------------------------");
            System.out.println("1. Afficher Infos");
            System.out.println("2. Modifier Infos");
            System.out.println("3. Ajouter un cours");
            System.out.println("4. Modifier un cours");
            System.out.println("5. Supprimer un cours");
            System.out.println("6. Retour au Menu Principal");
            System.out.println("7. Quitter");

            System.out.print("Choisissez une option: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    entraineur.afficherInfos();
                    break;
                case 2:
                    entraineur.modifierInfo();
                    break;
                case 3:
                    cours.ajouterCours();
                    break;
                case 4:
                    cours.modifierCours();
                    break;
                case 5:
                    cours.supprimerCours();
                    break;
                case 6:
                    System.out.println("Retour au Menu Principal");
                    break;
                case 7:
                    System.out.println("Au revoir!");
                    System.exit(0);;
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        } while (choice != 6);
    }
    public void afficherListeEntraineur() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                String query = "SELECT * FROM Entraineurs";
                try (PreparedStatement pstmt = connection.prepareStatement(query);
                     ResultSet rs = pstmt.executeQuery()) {
                    int i=0;
                    while (rs.next()) {
                        i++;
                        System.out.println("----------------------Membre "+i+"---------------------------");
                        System.out.println("ID: " + rs.getInt("idEntraineur"));
                        System.out.println("CIN: " + rs.getString("cinEntraineur"));
                        System.out.println("Nom: " + rs.getString("nomEntraineur"));
                        System.out.println("Prénom: " + rs.getString("prenomEntraineur"));
                        System.out.println("Genre: " + rs.getString("genreEntraineur"));
                        System.out.println("Date de Naissance: " + rs.getDate("dateNaissanceEntraineur"));
                        System.out.println("Email: " + rs.getString("emailEntraineur"));
                        System.out.println("Téléphone: " + rs.getString("telephoneEntraineur"));
                        System.out.println("Adresse: " + rs.getString("adresseEntraineur"));
                        System.out.println("Mot de Passe: " + rs.getString("motDePasseEntraineur"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage de la liste des Entraineur: " + e.getMessage());
        }
    }
}
