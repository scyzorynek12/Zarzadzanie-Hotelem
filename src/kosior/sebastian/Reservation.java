package kosior.sebastian;

import java.time.LocalDate;

public class Reservation {
    private String guestName;
    private Room room;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Reservation(String guestName, Room room, LocalDate checkIn, LocalDate checkOut) {
        this.guestName = guestName;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        room.reserve();
    }



    public String getGuestName() {
        return guestName;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    @Override
    public String toString() {
        return "Rezerwacja: " + guestName + " - Pokój " + room.getNumber();
    }
}