package kosior.sebastian;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel(5);

        while (true) {
            System.out.println("\n===== Menu =====");
            System.out.println("1. Pokaż dostępne pokoje");
            System.out.println("2. Pokaż rezerwacje");
            System.out.println("3. Zarezerwuj pokój");
            System.out.println("4. Wyjście");
            System.out.print("Wybierz opcję: ");

            int choice = scanner.nextInt();
            scanner.nextLine();


            switch (choice) {
                case 1:
                    hotel.displayRooms();
                    break;
                case 2:
                    hotel.displayReservations();
                    break;
                case 3:
                    System.out.print("Podaj imię i nazwisko gościa: ");
                    String guestName = scanner.nextLine();
                    System.out.print("Podaj numer pokoju: ");
                    int roomNumber = scanner.nextInt();
                    hotel.makeReservation(guestName, roomNumber);
                    break;
                case 4:
                    System.out.println("Dziękujemy za skorzystanie z serwisu!");
                    return;
                default:
                    System.out.println("Nieznana opcja, spróbuj ponownie.");
            }
        }
    }
}