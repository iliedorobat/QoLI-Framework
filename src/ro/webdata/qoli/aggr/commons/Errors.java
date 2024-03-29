package app.java.aggr.commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
     * @param acceptedData Map containing the list of accepted input
     * @param inputData The list of input data
     * @param inputType The input type
     * @throws Error The input data is not found in acceptedData
     */
    public static void throwNewError(HashMap<String, String> acceptedData, String[] inputData, String inputType) {
        List<String> acceptedList = new ArrayList<>(acceptedData.values());

        for (String inputDatum : inputData) {
            if (!acceptedList.contains(inputDatum)) {
                throw new Error("one of the input data (" + Arrays.toString(inputData) + ") " +
                        "is not one of the accepted " + inputType +
                        "\nPlease choose one of the following ones: " + acceptedList + ")");
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
