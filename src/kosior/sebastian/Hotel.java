package kosior.sebastian;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Hotel {
    private final List<Room> rooms;
    private final List<Reservation> reservations;
    private static final int MIN_RESERVATION_DAYS = 1;
    private static final int MAX_RESERVATION_DAYS = 30;

    public Hotel(int numRooms) {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();

        for (int i = 1; i <= numRooms; i++) {
            rooms.add(new Room(i));
        }
    }

    public void displayRooms() {
        try {
            System.out.println("\n=== Dostępne pokoje ===");
            rooms.stream()
                    .sorted((r1, r2) -> Integer.compare(r1.getNumber(), r2.getNumber()))
                    .forEach(room -> {
                        try {
                            String status = room.isAvailable() ? "DOSTĘPNY" : "ZAJĘTY";
                            String price = String.format("%.2f PLN/doba", PaymentSystem.getDailyRate(room.getType()));
                            System.out.printf("Pokój %d - %s - %s - %s%n",
                                    room.getNumber(),
                                    room.getType().getDescription(),
                                    status,
                                    price);
                        } catch (Exception e) {
                            System.out.println("Błąd podczas wyświetlania informacji o pokoju: " + e.getMessage());
                        }
                    });
        } catch (Exception e) {
            System.out.println("Wystąpił błąd podczas wyświetlania pokoi: " + e.getMessage());
        }
    }

    public void displayReservations() {
        try {
            if (reservations.isEmpty()) {
                System.out.println("Brak aktywnych rezerwacji.");
                return;
            }

            System.out.println("\n=== Aktualne rezerwacje ===");
            reservations.forEach(reservation -> {
                try {
                    double cost = PaymentSystem.calculateCost(reservation.getRoom(),
                            reservation.getCheckIn(),
                            reservation.getCheckOut());
                    System.out.printf("Rezerwacja: %s%n", reservation.getGuestName());
                    System.out.printf("Pokój: %d (%s)%n",
                            reservation.getRoom().getNumber(),
                            reservation.getRoom().getType().getDescription());
                    System.out.printf("Od: %s Do: %s%n",
                            reservation.getCheckIn(),
                            reservation.getCheckOut());
                    System.out.printf("Całkowity koszt: %.2f PLN%n", cost);
                    System.out.println("------------------------");
                } catch (Exception e) {
                    System.out.println("Błąd podczas wyświetlania rezerwacji: " + e.getMessage());
                }
            });
        } catch (Exception e) {
            System.out.println("Wystąpił błąd podczas wyświetlania rezerwacji: " + e.getMessage());
        }
    }

    public List<Room> getAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
        try {
            return rooms.stream()
                    .filter(room -> isRoomAvailable(room, checkIn, checkOut))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Błąd podczas wyszukiwania dostępnych pokoi: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Room> getAvailableRoomsByType(Type type, LocalDate checkIn, LocalDate checkOut) {
        return getAvailableRooms(checkIn, checkOut).stream()
                .filter(room -> room.getType() == type)
                .collect(Collectors.toList());
    }

    public Optional<Reservation> makeReservation(String guestName, int roomNumber,
                                                 LocalDate checkIn, LocalDate checkOut) {
        try {
            validateReservationDates(checkIn, checkOut);
            Room room = findRoom(roomNumber);

            if (room == null) {
                System.out.println("Błąd: Pokój o numerze " + roomNumber + " nie istnieje.");
                return Optional.empty();
            }

            if (!isRoomAvailable(room, checkIn, checkOut)) {
                System.out.println("Błąd: Pokój " + roomNumber + " jest niedostępny w podanym terminie.");
                return Optional.empty();
            }

            Reservation reservation = new Reservation(guestName, room, checkIn, checkOut);
            reservations.add(reservation);

            double cost = PaymentSystem.calculateCost(room, checkIn, checkOut);
            System.out.println("\nRezerwacja została potwierdzona!");
            System.out.printf("Całkowity koszt: %.2f PLN%n", cost);

            return Optional.of(reservation);

        } catch (IllegalArgumentException e) {
            System.out.println("Błąd: " + e.getMessage());
            return Optional.empty();
        }
    }

    private Room findRoom(int roomNumber) {
        return rooms.stream()
                .filter(room -> room.getNumber() == roomNumber)
                .findFirst()
                .orElse(null);
    }

    private boolean isRoomAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {
        try {
            return reservations.stream()
                    .filter(res -> res.getRoom().equals(room))
                    .noneMatch(res -> datesOverlap(res.getCheckIn(), res.getCheckOut(), checkIn, checkOut));
        } catch (Exception e) {
            System.out.println("Błąd podczas sprawdzania dostępności pokoju: " + e.getMessage());
            return false;
        }
    }


    private boolean datesOverlap(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        return !start1.isAfter(end2) && !start2.isAfter(end1);
    }

    private void validateReservationDates(LocalDate checkIn, LocalDate checkOut) {
        if (checkIn.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data zameldowania nie może być wcześniejsza niż dzisiaj.");
        }

        if (checkOut.isBefore(checkIn)) {
            throw new IllegalArgumentException("Data wymeldowania musi być późniejsza niż data zameldowania.");
        }

        long daysStay = checkOut.toEpochDay() - checkIn.toEpochDay();

        if (daysStay < MIN_RESERVATION_DAYS || daysStay > MAX_RESERVATION_DAYS) {
            throw new IllegalArgumentException(
                    String.format("Długość pobytu musi być między %d a %d dni.",
                            MIN_RESERVATION_DAYS, MAX_RESERVATION_DAYS));
        }
    }

    public void cancelReservation(String guestName, int roomNumber) {
        reservations.stream()
                .filter(res -> res.getGuestName().equals(guestName) &&
                        res.getRoom().getNumber() == roomNumber)
                .findFirst()
                .ifPresentOrElse(
                        reservation -> {
                            reservations.remove(reservation);
                            reservation.getRoom().makeAvailable();
                            System.out.println("Rezerwacja została anulowana pomyślnie.");
                        },
                        () -> System.out.println("Nie znaleziono takiej rezerwacji.")
                );
    }
}