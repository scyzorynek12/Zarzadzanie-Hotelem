package kosior.sebastian;

public class Reservation {
    private String guestName;
    private Room room;

    public Reservation(String guestName, Room room) {
        this.guestName = guestName;
        this.room = room;
        room.reserve();
    }

    public String getGuestName() {
        return guestName;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "Rezerwacja: " + guestName + " - Pokój " + room.getNumber();
    }
}