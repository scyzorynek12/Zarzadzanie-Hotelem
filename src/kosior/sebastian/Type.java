package kosior.sebastian;

import java.util.Random;

public enum Type {
    SINGLE("Jedno osobowy"), DOUBLE("Dwu osobowy"), SUITE("Apartament"), FAMILY("Rodzinny");

    private final String description;

    private static final Type[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    Type(String description) {
        this.description = description;
    }

    public static Type getRandomType(){
        return VALUES[RANDOM.nextInt(SIZE)];
    }

    public String getDescription(){
        return description;
    }
}
