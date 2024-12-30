package kosior.sebastian;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel(int numRooms) {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();

        for (int i = 1; i <= numRooms; i++) {
            rooms.add(new Room(i));
        }
    }

    public void displayRooms() {
        System.out.println("Dostępne pokoje:");
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    public void displayReservations() {
        System.out.println("Rezerwacje:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    public void makeReservation(String guestName, int roomNumber, LocalDate checkIn, LocalDate checkOut) {
        Room room = findRoom(roomNumber);
        if (room != null && room.isAvailable()) {
            Reservation reservation = new Reservation(guestName, room, checkIn, checkOut);
            reservations.add(reservation);
            int cost = (int) PaymentSystem.calculateCost(room, checkIn, checkOut);
            System.out.println("Rezerwacja udana!");
            System.out.println("Będzie ona kosztowac: " + cost + "Pln");
        } else {
            System.out.println("Pokój " + roomNumber + " jest niedostępny.");
        }

    }

    private Room findRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }
}
