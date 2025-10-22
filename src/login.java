import java.util.Scanner;
import java.util.InputMismatchException;

public class login {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean authentifie = false;

        while (!authentifie) {
            System.out.print(" ********************** login ********************** \n");



            System.out.println("Sélectionnez le type d'utilisateur:");
            System.out.println("1. Responsable");
            System.out.println("2. Membre");
            System.out.println("3. Entraîneur");
            System.out.print("Choisissez une option: ");


            try {
                int choixUtilisateur = scanner.nextInt();
                scanner.nextLine();

                String typeUtilisateur;
                switch (choixUtilisateur) {
                    case 1:
                        typeUtilisateur = "responsable";
                        break;
                    case 2:
                        typeUtilisateur = "membre";
                        break;
                    case 3:
                        typeUtilisateur = "entraineur";
                        break;
                    default:
                        System.out.println("Choix invalide. Veuillez réessayer.");
                        continue; // Restart loop
                }
                System.out.print("CIN: ");
                String cin = scanner.nextLine();

                System.out.print("Mot de passe: ");
                String motDePasse = scanner.nextLine();
                Utilisateur utilisateur = Authentification.authentifier(cin, motDePasse, typeUtilisateur);

                if (utilisateur != null) {
                    authentifie = true;
                    System.out.println("Authentification réussie.");
                    switch (typeUtilisateur) {
                        case "responsable":
                            MenuResponsable.MenuResponsable((Responsable) utilisateur);
                            break;
                        case "membre":
                            MenuMembre.MenuMembre((Membre) utilisateur);
                            break;
                        case "entraineur":
                            MenuEntraineur.MenuEntraineur((Entraineur) utilisateur);
                            break;
                        default:
                            System.out.println("Type d'utilisateur non pris en charge pour le menu.");
                            authentifie = false;
                            break;
                    }
                } else {
                    System.out.println("Échec de l'authentification. Veuillez réessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre pour le type d'utilisateur.");
                scanner.nextLine();
            }
        }

        scanner.close();
    }
}
