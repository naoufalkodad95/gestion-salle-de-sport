import java.util.Scanner;


public class MenuMembre {


    public static void MenuMembre(Membre membre) {
        Scanner scanner = new Scanner(System.in);
        CoursCollectif cours = new CoursCollectif();
        Reservation reservation = new Reservation();

        int choice;
        do {
            System.out.println("-----------------------Menu Membre--------------------------");
            System.out.println("1. Afficher Infos");
            System.out.println("2. Modifier Infos");
            System.out.println("3. Consulter le planning");
            System.out.println("4. Effectuer une réservation");
            System.out.println("5. Annuler une réservation");
            System.out.println("6. Retour au Menu Principal");
            System.out.println("7. Quitter");
            System.out.print("Choisissez une option: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    membre.afficherInfos();
                    break;
                case 2:
                    membre.modifierInfo();
                    break;
                case 3:
                    cours.consulterCoursCollectif();
                    break;
                case 4:
                    reservation.effectuerReservation(membre.getId());
                    break;
                case 5:
                    reservation.annulerReservation();
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


}