package kosior.sebastian;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Hotel hotel = new Hotel(20);

        while (true) {
            System.out.println("\n===== System Rezerwacji Hotelu =====");
            System.out.println("1. Pokaż wszystkie pokoje");
            System.out.println("2. Wyszukaj dostępne pokoje");
            System.out.println("3. Pokaż rezerwacje");
            System.out.println("4. Zarezerwuj pokój");
            System.out.println("5. Anuluj rezerwację");
            System.out.println("6. Informacje o cenach");
            System.out.println("7. Wyjście");
            System.out.print("Wybierz opcję: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> hotel.displayRooms();
                    case 2 -> searchAvailableRooms(hotel);
                    case 3 -> hotel.displayReservations();
                    case 4 -> makeReservation(hotel);
                    case 5 -> cancelReservation(hotel);
                    case 6 -> displayPriceInfo();
                    case 7 -> {
                        System.out.println("Dziękujemy za skorzystanie z systemu!");
                        return;
                    }
                    default -> System.out.println("Nieznana opcja, spróbuj ponownie.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Proszę wprowadzić poprawny numer opcji.");
            }
        }
    }

    private static void searchAvailableRooms(Hotel hotel) {
        try {
            System.out.println("Wprowadź datę zameldowania (YYYY-MM-DD): ");
            LocalDate checkIn = LocalDate.parse(scanner.nextLine());
            System.out.println("Wprowadź datę wymeldowania (YYYY-MM-DD): ");
            LocalDate checkOut = LocalDate.parse(scanner.nextLine());

            System.out.println("Wybierz typ pokoju (opcjonalnie):");
            System.out.println("1. Wszystkie typy");
            for (int i = 0; i < Type.values().length; i++) {
                System.out.println((i + 2) + ". " + Type.values()[i].getDescription());
            }

            int typeChoice = Integer.parseInt(scanner.nextLine());
            List<Room> availableRooms;

            if (typeChoice == 1) {
                availableRooms = hotel.getAvailableRooms(checkIn, checkOut);
            } else if (typeChoice >= 2 && typeChoice <= Type.values().length + 1) {
                Type selectedType = Type.values()[typeChoice - 2];
                availableRooms = hotel.getAvailableRoomsByType(selectedType, checkIn, checkOut);
            } else {
                throw new IllegalArgumentException("Nieprawidłowy wybór typu pokoju.");
            }

            if (availableRooms.isEmpty()) {
                System.out.println("Brak dostępnych pokoi w wybranym terminie.");
            } else {
                System.out.println("\nDostępne pokoje:");
                availableRooms.forEach(System.out::println);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Nieprawidłowy format daty. Użyj formatu YYYY-MM-DD.");
        } catch (NumberFormatException e) {
            System.out.println("Wprowadź poprawną liczbę.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Wystąpił nieoczekiwany błąd: " + e.getMessage());
        }
    }

    private static void makeReservation(Hotel hotel) {
        try {
            System.out.print("Podaj imię i nazwisko gościa: ");
            String guestName = scanner.nextLine();
            if (guestName.trim().isEmpty()) {
                throw new IllegalArgumentException("Imię i nazwisko nie może być puste");
            }

            System.out.print("Podaj numer pokoju: ");
            int roomNumber = Integer.parseInt(scanner.nextLine());

            System.out.println("Wprowadź datę zameldowania (YYYY-MM-DD): ");
            LocalDate checkIn = LocalDate.parse(scanner.nextLine());

            System.out.println("Wprowadź datę wymeldowania (YYYY-MM-DD): ");
            LocalDate checkOut = LocalDate.parse(scanner.nextLine());

            hotel.makeReservation(guestName, roomNumber, checkIn, checkOut);
        } catch (DateTimeParseException e) {
            System.out.println("Nieprawidłowy format daty. Użyj formatu YYYY-MM-DD.");
        } catch (NumberFormatException e) {
            System.out.println("Wprowadź poprawny numer pokoju.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Wystąpił nieoczekiwany błąd: " + e.getMessage());
        }
    }

    private static void cancelReservation(Hotel hotel) {
        try {
            System.out.print("Podaj imię i nazwisko gościa: ");
            String guestName = scanner.nextLine();
            System.out.print("Podaj numer pokoju: ");
            int roomNumber = Integer.parseInt(scanner.nextLine());

            hotel.cancelReservation(guestName, roomNumber);
        } catch (NumberFormatException e) {
            System.out.println("Wprowadź poprawny numer pokoju.");
        }
    }

    private static void displayPriceInfo() {
        System.out.println("\n=== Cennik ===");
        System.out.println("Ceny podstawowe za dobę:");
        for (Type type : Type.values()) {
            System.out.printf("%s: %.2f PLN%n", type.getDescription(), PaymentSystem.getDailyRate(type));
        }

        System.out.println("\nMnożniki sezonowe:");
        for (PaymentSystem.Season season : PaymentSystem.Season.values()) {
            System.out.printf("%s sezon: x%.1f%n", season.getDescription(),
                    PaymentSystem.SEASON_MULTIPLIERS.get(season));
        }
        System.out.println("\nZniżki:");
        System.out.println("- 10% dla pobytów dłuższych niż 7 dni");
    }
}