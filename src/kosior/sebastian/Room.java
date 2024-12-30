package kosior.sebastian;

public class Room {
    private int number;
    private boolean isAvailable;
    private Type type;

    public Type getType() {
        return type;
    }

    public Room(int number) {
        this.number = number;
        this.isAvailable = true;
        this.type = Type.getRandomType();
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

    @Override
    public String toString() {
        return "Pokój " + number + " - " + type.getDescription() + " jest "  + (isAvailable ? "Dostępny" : "Zajęty");
    }
}


