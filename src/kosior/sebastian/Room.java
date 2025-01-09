package kosior.sebastian;

public class Room {
    private int number;
    private boolean isAvailable;
    private Type type;
    private int floor;
    private ViewType view;

    public enum ViewType {
        CITY("Widok na miasto"),
        GARDEN("Widok na ogród"),
        POOL("Widok na basen"),
        PARKING("Widok na parking");

        private final String description;

        ViewType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public Room(int number) {
        this.number = number;
        this.isAvailable = true;
        this.type = Type.getRandomType();
        this.floor = (number - 1) / 10 + 1;
        this.view = ViewType.values()[number % ViewType.values().length];
    }

    public int getNumber() {
        return number;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Type getType() {
        return type;
    }

    public int getFloor() {
        return floor;
    }

    public ViewType getView() {
        return view;
    }

    public void reserve() {
        try {
            if (isAvailable) {
                isAvailable = false;
            } else {
                throw new IllegalStateException("Pokój " + number + " jest już zajęty.");
            }
        } catch (Exception e) {
            System.out.println("Błąd podczas rezerwacji pokoju: " + e.getMessage());
        }
    }

    public void makeAvailable() {
        try {
            isAvailable = true;
        } catch (Exception e) {
            System.out.println("Błąd podczas zwalniania pokoju: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return String.format("Pokój %d - %s - Piętro %d - %s - %s",
                number, type.getDescription(), floor, view.getDescription(),
                isAvailable ? "Dostępny" : "Zajęty");
    }
}


