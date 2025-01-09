package kosior.sebastian;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

public class PaymentSystem {
    protected static final Map<Season, Double> SEASON_MULTIPLIERS = new HashMap<>();
    private static final Map<Type, Double> BASE_RATES = new HashMap<>();
    private static double DISCOUNT_LONG_STAY = 0.1;

    static {
        SEASON_MULTIPLIERS.put(Season.LOW, 1.0);
        SEASON_MULTIPLIERS.put(Season.MID, 1.3);
        SEASON_MULTIPLIERS.put(Season.HIGH, 1.6);
        SEASON_MULTIPLIERS.put(Season.PEAK, 2.0);

        BASE_RATES.put(Type.SINGLE, 100.0);
        BASE_RATES.put(Type.DOUBLE, 150.0);
        BASE_RATES.put(Type.SUITE, 250.0);
        BASE_RATES.put(Type.FAMILY, 200.0);
    }

    public static double calculateCost(Room room, LocalDate checkIn, LocalDate checkOut) {
        try {
            if (checkIn.isAfter(checkOut)) {
                throw new IllegalArgumentException("Data wymeldowania musi być późniejsza niż zameldowania.");
            }

            long days = calculateDays(checkIn, checkOut);
            if (days <= 0) {
                throw new IllegalArgumentException("Pobyt musi trwać co najmniej jeden dzień.");
            }

            double dailyRate = getDailyRate(room.getType());
            double seasonMultiplier = getSeasonMultiplier(checkIn);
            double baseTotal = dailyRate * days * seasonMultiplier;

            if (days > 7) {
                return baseTotal * (1 - DISCOUNT_LONG_STAY);
            }

            return baseTotal;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Błąd podczas obliczania kosztu: " + e.getMessage());
        }
    }

    public static double getDailyRate(Type type) {
        return BASE_RATES.get(type);
    }

    private static long calculateDays(LocalDate checkIn, LocalDate checkOut) {
        try {
            return (checkOut.toEpochDay() - checkIn.toEpochDay());
        } catch (Exception e) {
            throw new RuntimeException("Błąd podczas obliczania liczby dni: " + e.getMessage());
        }
    }

    private static Season getSeason(LocalDate date) {
        Month month = date.getMonth();
        return switch (month) {
            case DECEMBER, JANUARY, FEBRUARY -> Season.LOW;
            case MARCH, APRIL, OCTOBER, NOVEMBER -> Season.MID;
            case MAY, JUNE, SEPTEMBER -> Season.HIGH;
            case JULY, AUGUST -> Season.PEAK;
        };
    }

    protected static double getSeasonMultiplier(LocalDate date) {
        return SEASON_MULTIPLIERS.get(getSeason(date));
    }

    public enum Season {
        LOW("Niski"),
        MID("Średni"),
        HIGH("Wysoki"),
        PEAK("Szczytowy");

        private final String description;

        Season(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
