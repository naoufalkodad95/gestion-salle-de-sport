import java.util.Scanner;

public class MenuResponsable {
    public static void MenuResponsable(Responsable responsable) {
        Scanner scanner = null;

        Membre membre = new Membre();
        Entraineur entraineur = new Entraineur();
        Equipement equipement = new Equipement();

        scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("-----------------------Menu Responsable--------------------------");
            System.out.println("1. Afficher Infos");
            System.out.println("2. Modifier Infos");
            System.out.println("3. Gerer Membre");
            System.out.println("4. Gerer Entraineur");
            System.out.println("5. Gerer Equipement");
            System.out.println("6. Retour au Menu Principal");
            System.out.println("7. Quitter");
            System.out.print("Choisissez une option: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    responsable.afficherInfos();
                    break;
                case 2:
                    responsable.modifierInfo();
                    break;
                case 3:
                    GererMembre();
                    break;
                case 4:
                    GererEntraineur();
                    break;
                case 5:
                    GererEquipement();
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
        } while (choice != 10);
    }
    public static void GererMembre( ) {
        Scanner scanner = null;

        Membre membre = new Membre();

        scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("-----------------------Gerer Membre--------------------------");
            System.out.println("1. Ajouter Membre");
            System.out.println("2. Supprimer Membre");
            System.out.println("3. afficher Tous Les Membres");
            System.out.println("4. Retour au Menu Principal");
            System.out.print("Choisissez une option: ");
            choice = scanner.nextInt();
            switch (choice) {

                case 1:
                    membre.ajouterMembre();
                    break;
                case 2:
                    membre.supprimerMembre();
                    break;
                case 3:
                    membre.afficherListeMembre();
                    break;
                case 4:
                    System.out.println("Retour au Menu Principal");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        } while (choice != 4);
    }
    public static void GererEntraineur( ) {
        Scanner scanner = null;

        Entraineur entraineur = new Entraineur();
        scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("-----------------------Gerer Entraineur--------------------------");
            System.out.println("1. Ajouter Entraineur");
            System.out.println("2. Supprimer Entraineur");
            System.out.println("3. afficher Tous Les Entraineur");
            System.out.println("4. Retour au Menu Principal");
            System.out.print("Choisissez une option: ");
            choice = scanner.nextInt();
            switch (choice) {

                case 1:
                    entraineur.ajouterEntraineur();
                    break;
                case 2:
                    entraineur.supprimerEntraineur();
                    break;
                case 3:
                    entraineur.afficherListeEntraineur();
                    break;
                case 4:
                    System.out.println("Retour au Menu Principal");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        } while (choice != 4);
    }
    public static void GererEquipement( ) {
        Scanner scanner = null;
        Responsable responsable = new Responsable();

        Equipement equipement = new Equipement();
        scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("-----------------------Gerer Equipement--------------------------");
            System.out.println("1. Ajouter Equipement");
            System.out.println("2. Supprimer Equipement");
            System.out.println("3. afficher Tous Les Equipement");
            System.out.println("4. Retour au Menu Principal");
            System.out.print("Choisissez une option: ");
            choice = scanner.nextInt();
            switch (choice) {

                case 1:
                    equipement.ajouterEquipement(responsable.getId());
                    break;
                case 2:
                    equipement.supprimerEquipement();
                    break;
                case 3:
                    equipement.afficherListeEquipement();
                    break;
                case 4:
                    System.out.println("Retour au Menu Principal");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        } while (choice != 4);
    }
}