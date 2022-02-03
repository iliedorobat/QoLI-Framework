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
     * @throws Error The input data is not found in acceptedData
     */
    public static void throwNewError(String[] acceptedData, String inputData, String inputType) {
        if (!Arrays.asList(acceptedData).contains(inputData)) {
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
     * @throws Error The input data is not found in acceptedData
     */
    public static void throwNewError(String[] acceptedData, String[] inputData, String inputType) {
        List<String> acceptedList = Arrays.asList(acceptedData);

        for (String inputDatum : inputData) {
            if (!acceptedList.contains(inputDatum)) {
                throw new Error("one of the input data (" + Arrays.toString(inputData) + ") " +
                        "is not one of the accepted " + inputType +
                        "\nPlease choose one of the following ones: " + Arrays.toString(acceptedData) + ")");
            }
        }
    }

//    public static void throwLowerPercentageValue(double value) {
//        if (value < Constants.PERCENTAGE_MIN_VALUE) {
//            throw new Error("The value (" + value + ") is lower than " + Constants.PERCENTAGE_MIN_VALUE
//                    + " and the current safety threshold can not be applied.");
//        }
//    }
}
