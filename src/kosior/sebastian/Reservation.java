package kosior.sebastian;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reservation {
    private String guestName;
    private Room room;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

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
        return String.format("Rezerwacja: %s%nPokój: %d (%s)%nTermin: %s - %s",
                guestName,
                room.getNumber(),
                room.getType().getDescription(),
                checkIn.format(DATE_FORMATTER),
                checkOut.format(DATE_FORMATTER));
    }
}