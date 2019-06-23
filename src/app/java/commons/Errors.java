package app.java.commons;

import java.util.Arrays;

public class Errors {
    /**
     * Throw new Error if the input data is not found in acceptedData
     *
     * @param acceptedData The list of accepted input
     * @param inputData The input data
     * @param inputType The input type
     * @throws new Error
     */
    public static void throwNewError(String[] acceptedData, String inputData, String inputType) {
        if (Arrays.asList(acceptedData).indexOf(inputData) == -1) {
            throw new Error(inputData + " is not one of the accepted " + inputType +
                    "\nPlease choose one of the following ones: " + Arrays.toString(acceptedData) + ")");
        }
    }
}
