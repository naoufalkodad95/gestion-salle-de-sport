import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class Membre extends Utilisateur {



    public Membre(int id, String cin, String nom, String prenom, String genre, Date dateNaissance, String email, String telephone, String adresse, String motDePasse) {
        super(id, cin, nom, prenom, genre, (java.sql.Date) dateNaissance, email, telephone, adresse, motDePasse);

    }

    public Membre() {

    }


    @Override
    public void afficherInfos() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                String query = "SELECT * FROM Membres WHERE cinMembre = ?";
                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                    pstmt.setString(1, this.cin);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            System.out.println("-------------------------------------------------");
                            System.out.println("ID: " + rs.getInt("idMembre"));
                            System.out.println("CIN: " + rs.getString("CINMembre"));
                            System.out.println("Nom: " + rs.getString("nomMembre"));
                            System.out.println("Prénom: " + rs.getString("prenomMembre"));
                            System.out.println("Genre: " + rs.getString("genreMembre"));
                            System.out.println("Date de Naissance: " + rs.getDate("dateNaissanceMembre"));
                            System.out.println("Email: " + rs.getString("emailMembre"));
                            System.out.println("Téléphone: " + rs.getString("telephoneMembre"));
                            System.out.println("Adresse: " + rs.getString("adresseMembre"));
                            System.out.println("Mot de Passe: " + rs.getString("motDePasseMembre"));
                            System.out.println("Est Admin: " + rs.getBoolean("isAdmin"));
                        } else {
                            System.out.println("Aucune information trouvée.");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des informations: " + e.getMessage());
        }
    }


    @Override
    public void modifierInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nouveau CIN : ");
        String newCin = scanner.nextLine();

        System.out.print("Nouveau nom : ");
        String newNom = scanner.nextLine();

        System.out.print("Nouveau prénom : ");
        String newPrenom = scanner.nextLine();

        System.out.print("Nouveau genre : ");
        String newGenre = scanner.nextLine();

        System.out.print("Nouvelle date de naissance (yyyy-MM-dd) : ");
        String newDateNaissanceStr = scanner.nextLine();
        java.sql.Date newDateNaissance = java.sql.Date.valueOf(newDateNaissanceStr);

        System.out.print("Nouvel email : ");
        String newEmail = scanner.nextLine();

        System.out.print("Nouveau téléphone : ");
        String newTelephone = scanner.nextLine();

        System.out.print("Nouvelle adresse : ");
        String newAdresse = scanner.nextLine();

        System.out.print("Nouveau mot de passe : ");
        String newMotDePasse = scanner.nextLine();

        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                String query = "UPDATE Membres SET cinMembre = ?, nomMembre = ?, prenomMembre = ?, genreMembre = ?, dateNaissanceMembre = ?, emailMembre = ?, telephoneMembre = ?, adresseMembre = ?, motDePasseMembre = ? WHERE cinMembre = ?";
                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                    pstmt.setString(1, newCin);
                    pstmt.setString(2, newNom);
                    pstmt.setString(3, newPrenom);
                    pstmt.setString(4, newGenre);
                    pstmt.setDate(5, newDateNaissance);
                    pstmt.setString(6, newEmail);
                    pstmt.setString(7, newTelephone);
                    pstmt.setString(8, newAdresse);
                    pstmt.setString(9, newMotDePasse);
                    pstmt.setString(10, this.cin);

                    int rowsUpdated = pstmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("Mise à jour réussie.");
                        this.cin = newCin;
                        this.nom = newNom;
                        this.prenom = newPrenom;
                        this.genre = newGenre;
                        this.dateNaissance = newDateNaissance;
                        this.email = newEmail;
                        this.telephone = newTelephone;
                        this.adresse = newAdresse;
                        this.motDePasse = newMotDePasse;
                    } else {
                        System.out.println("Aucune mise à jour effectuée.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour des informations: " + e.getMessage());
        }
    }

    public void ajouterMembre() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("CIN : ");
        String cin = scanner.nextLine();

        System.out.print("Nom : ");
        String nom = scanner.nextLine();

        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();

        System.out.print("Genre : ");
        String genre = scanner.nextLine();

        System.out.print("Date de naissance (yyyy-MM-dd) : ");
        String dateNaissanceStr = scanner.nextLine();
        java.sql.Date dateNaissance = java.sql.Date.valueOf(dateNaissanceStr);

        System.out.print("Email : ");
        String email = scanner.nextLine();

        System.out.print("Téléphone : ");
        String telephone = scanner.nextLine();

        System.out.print("Adresse : ");
        String adresse = scanner.nextLine();

        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();

        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                String query = "INSERT INTO Membres (cinMembre, nomMembre, prenomMembre, genreMembre, dateNaissanceMembre, emailMembre, telephoneMembre, adresseMembre, motDePasseMembre) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                    pstmt.setString(1, cin);
                    pstmt.setString(2, nom);
                    pstmt.setString(3, prenom);
                    pstmt.setString(4, genre);
                    pstmt.setDate(5, dateNaissance);
                    pstmt.setString(6, email);
                    pstmt.setString(7, telephone);
                    pstmt.setString(8, adresse);
                    pstmt.setString(9, motDePasse);

                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("Membre ajouté avec succès.");
                    } else {
                        System.out.println("Aucun membre ajouté.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du membre: " + e.getMessage());
        }
    }

    public void supprimerMembre() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("CIN de Membre à supprimer : ");
        String cin = scanner.nextLine();

        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                String query = "DELETE FROM Membres WHERE cinMembre = ?";
                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                    pstmt.setString(1, cin);

                    int rowsDeleted = pstmt.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println("Membre supprimé avec succès.");
                    } else {
                        System.out.println("Aucun entraineur supprimé.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de Membre: " + e.getMessage());
        }
    }

    public void afficherListeMembre() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                String query = "SELECT * FROM Membres";
                try (PreparedStatement pstmt = connection.prepareStatement(query);
                     ResultSet rs = pstmt.executeQuery()) {
                    int i=0;
                    while (rs.next()) {
                        i++;
                        System.out.println("----------------------Membre "+i+"---------------------------");
                        System.out.println("ID: " + rs.getInt("idMembre"));
                        System.out.println("CIN: " + rs.getString("cinMembre"));
                        System.out.println("Nom: " + rs.getString("nomMembre"));
                        System.out.println("Prénom: " + rs.getString("prenomMembre"));
                        System.out.println("Genre: " + rs.getString("genreMembre"));
                        System.out.println("Date de Naissance: " + rs.getDate("dateNaissanceMembre"));
                        System.out.println("Email: " + rs.getString("emailMembre"));
                        System.out.println("Téléphone: " + rs.getString("telephoneMembre"));
                        System.out.println("Adresse: " + rs.getString("adresseMembre"));
                        System.out.println("Mot de Passe: " + rs.getString("motDePasseMembre"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage de la liste des membres: " + e.getMessage());
        }
    }
}
