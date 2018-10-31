package app.java.commons;

public class MathUtils {
    /**
     * Returns the value of a specific square
     * @param input     the value of square extraction
     * @param order     the square order
     * @return <b>double</b>
     */
    public double getSquareValue(double input, double order) {
        if (order == 0)
            return Math.pow(input, 0);
        return Math.pow(input, 1 / order);
    }
}
