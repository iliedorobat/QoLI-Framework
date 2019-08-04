package app.java.data.measurement;

import app.java.commons.MapOrder;
import app.java.commons.constants.Constants;
import app.java.commons.constants.ParamsConst;
import app.java.data.parse.LocalParser;

import java.util.*;

public class MeasureUtils {
    // For testing
    //TODO: delete
    public static void print(String filePath) {
        Map<List<String>, Number> entries = LocalParser.readJSONFile(filePath);
        System.out.println(entries);

        Set<String> dimensions = LocalParser.getDimensionsOrder(filePath);
        System.out.println(dimensions);
    }

    /**
     * Prepare data based on the set of parameters values (change the key name)<br/>
     * E.g.:
     * <pre>
     *  {
     *      [I_DSK_BAB, IND_TOTAL, PC_IND, AT, 2015]=64,
     *      [I_DSK_BAB, IND_TOTAL, PC_IND, AT, 2016]=65,
     *      [I_DSK_BAB, IND_TOTAL, PC_IND, AT, 2017]=67,
     *      [I_DSK_BAB, IND_TOTAL, PC_IND, BE, 2015]=60,
     *      ...
     *  }
     * </pre>
     * will be processed to:
     * <pre>
     *  {
     *      AT_2015=64,
     *      AT_2016=65,
     *      AT_2017=67,
     *      BE_2015=60,
     *      ...
     *  }
     * </pre>
     *
     * @param globalParamsValues The global allowed query values (the allowed query values
     *                           excepting the year and the country code)
     * @param filePath The full access path to the desired file
     * @return Sorted list with COUNTRY-CODE_YEAR as key (e.g.: AT_2010; RO_2015 etc.)
     */
    public static Map<String, Number> consolidateList(String[] globalParamsValues, String filePath) {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<List<String>, Number> entries = LocalParser.readJSONFile(filePath);

        Set<String> dim = LocalParser.getDimensionsOrder(filePath);
        List<String> dimList = new ArrayList<>(dim);
        int countryIndex = dimList.indexOf(ParamsConst.GEO);
        int yearIndex = dimList.indexOf(ParamsConst.TIME);

        for (Map.Entry<List<String>, Number> entry : entries.entrySet()) {
            // queryValues = the query values for parameters
            // Example:
            //      parameters: [unit, sex, isced11, age, geo, time]
            //      query values: [PC, T, ED5-8, Y15-64, UK, 2017]
            List<String> queryValues = entry.getKey();
            Number value = entry.getValue();

            String country = queryValues.get(countryIndex);
            int year = Integer.valueOf(queryValues.get(yearIndex));

            if (isValidQuery(globalParamsValues, queryValues)) {
                consolidatedList.put(country + Constants.KEY_SEPARATOR + year, value);
            }
        }

        return consolidatedList;
    }

    /**
     * Check if all of the queried values are in the parameters list (the query is valid)
     *
     * @param params The list of query parameters
     * @param queryValues The list of parsed keys
     * @return The result of the validation of the query
     */
    private static boolean isValidQuery(String[] params, List<String> queryValues) {
        for (int i = 0; i < params.length; i++) {
            if (queryValues.indexOf(params[i]) == -1) {
                return false;
            }
        }

        return true;
    }
}
