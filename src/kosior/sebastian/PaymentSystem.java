package kosior.sebastian;

import java.time.LocalDate;
import java.time.Period;

public class PaymentSystem {

    public static double calculateCost(Room room, LocalDate checkIn, LocalDate checkOut) {
        Period period = Period.between(checkIn, checkOut);
        long days = period.getDays();
        if (days <= 0) {
            throw new IllegalArgumentException("Check-out date must be after check-in date.");
        }

        double dailyRate = getDailyRate(room.getType());
        return dailyRate * days;
    }

    private static double getDailyRate(Type type) {
        return switch (type) {
            case SINGLE -> 100.0;
            case DOUBLE -> 150.0;
            case SUITE -> 250.0;
            case FAMILY -> 200.0;
        };
    }
}

