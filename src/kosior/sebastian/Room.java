package kosior.sebastian;

public class Room {
    private int number;
    private boolean isAvailable;

    public Room(int number) {
        this.number = number;
        this.isAvailable = true;
    }

    public int getNumber() {
        return number;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void reserve() {
        if (isAvailable) {
            isAvailable = false;
        } else {
            System.out.println("Pokój " + number + " jest już zajęty.");
        }
    }

    public void release() {
        isAvailable = true;
    }

    @Override
    public String toString() {
        return "Pokój " + number + " - " + (isAvailable ? "Dostępny" : "Zajęty");
    }
}


