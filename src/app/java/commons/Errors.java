package app.java.commons;

import java.util.Arrays;
import java.util.List;

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

    /**
     * Throw new Error if the input data is not found in acceptedData
     *
     * @param acceptedData The list of accepted input
     * @param inputData The list of input data
     * @param inputType The input type
     * @throws new Error
     */
    public static void throwNewError(String[] acceptedData, String inputData[], String inputType) {
        List acceptedList = Arrays.asList(acceptedData);

        for (int i = 0; i < inputData.length; i++) {
            if (acceptedList.indexOf(inputData[i]) == -1) {
                throw new Error("one of the input data (" + Arrays.toString(inputData) + ") " +
                        "is not one of the accepted " + inputType +
                        "\nPlease choose one of the following ones: " + Arrays.toString(acceptedData) + ")");
            }
        }
    }
}
