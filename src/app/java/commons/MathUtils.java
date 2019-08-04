package app.java.commons;

public class MathUtils {
    /**
     * Returns the value of a specific square
     * @param input     the value of square extraction
     * @param order     the square order
     * @return <b>double</b>
     */
    public static double getSquareValue(double input, double order) {
        if (order == 0)
            return Math.pow(input, 0);
        return Math.pow(input, 1 / order);
    }

    /**
     * Round a value with "places" decimals
     * @param value The input value
     * @param places The number of decimals with which the value will be formatted
     * @return The value with "places" decimals
     */
    public static double round(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        double preparedValue = value * factor;
        double roundedValue = Math.round(preparedValue);

        return roundedValue / factor;
    }
}
