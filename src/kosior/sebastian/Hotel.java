package kosior.sebastian;

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

    public void makeReservation(String guestName, int roomNumber) {
        Room room = findRoom(roomNumber);
        if (room != null && room.isAvailable()) {
            Reservation reservation = new Reservation(guestName, room);
            reservations.add(reservation);
            System.out.println("Rezerwacja udana!");
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
