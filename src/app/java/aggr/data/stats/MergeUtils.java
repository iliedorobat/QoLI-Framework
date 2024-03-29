package app.java.aggr.data.stats;

import app.java.aggr.commons.MapOrder;
import app.java.aggr.commons.constants.Constants;
import app.java.aggr.commons.constants.ParamsNames;
import app.java.aggr.commons.utils.MapUtils;
import app.java.aggr.data.LocalParser;
import app.java.aggr.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static app.java.aggr.commons.constants.Constants.EU28_MEMBERS;

public class MergeUtils {
    /**
     * Prepare data based on a set of parameters values (change the key name)<br/>
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
     * @param params The global allowed query values (the allowed query values
     *                           excepting the year and the country code)
     * @param filePath The full access path to the desired file
     * @return Sorted map with COUNTRY-CODE_YEAR as key (e.g.: AT_2010; RO_2015 etc.)
     */
    public static Map<String, Number> consolidateMap(MultiValuedMap<String, String> params, String filePath) {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        String fullPath = System.getProperty("user.dir") + File.separator + filePath;
        Map<List<String>, Number> entries = LocalParser.readJSONFile(fullPath);

        ArrayList<String> localQueryKeys = LocalParser.getDimensionKeys(fullPath);
        int countryIndex = localQueryKeys.indexOf(ParamsNames.GEO);
        int yearIndex = localQueryKeys.indexOf(ParamsNames.TIME);

        for (Map.Entry<List<String>, Number> entry : entries.entrySet()) {
            // entryValues = the queried values for parameters
            // Example:
            //      parameters: [unit, sex, isced11, age, geo, time]
            //      queryEntries: [PC, T, ED5-8, Y15-64, UK, 2017]
            ArrayList<String> localQueryValues = new ArrayList<>(entry.getKey());
            Number value = entry.getValue();

            String country = localQueryValues.get(countryIndex);
            int year = Integer.parseInt(localQueryValues.get(yearIndex));

            if (isParamIncluded(params, localQueryKeys, localQueryValues)) {
                consolidatedList.put(country + Constants.KEY_SEPARATOR + year, value);
            }
        }

        return consolidatedList;
    }

    // TODO: documentation: Check if the current iterated entry (queryValues) includes params values
    private static boolean isParamIncluded(MultiValuedMap<String, String> params, ArrayList<String> localQueryKeys, ArrayList<String> queryValues) {
        ArrayList<String> paramsKeys = FetcherUtils.getFilteredParamsKeys(params);

        for (String paramKey : paramsKeys) {
            int localIndex = localQueryKeys.indexOf(paramKey);
            String localValue = queryValues.get(localIndex);
            String paramValue = params.get(paramKey).iterator().next();

            if (!localValue.equals(paramValue))
                return false;
        }

        return true;
    }

    /**
     * Consolidate a list of maps into a single one
     *
     * @param mapsList The list of maps
     * @return Sorted map with COUNTRY-CODE_YEAR as key (e.g.: AT_2010; RO_2015 etc.)
     */
    public static Map<String, Number> consolidateMaps(ArrayList<Map<String, Number>> mapsList) {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());

        // Iterate over EU28_MEMBERS in order to add the entries by country code into the ordered map
        for (String code : EU28_MEMBERS) {
            for (Map<String, Number> map : mapsList) {
                for (Map.Entry<String, Number> entry : map.entrySet()) {
                    String entryCode = MapUtils.getEntryCode(entry);
                    String entryKey = entry.getKey();
                    Number entryValue = entry.getValue();

                    if (code.equals(entryCode) && !preparedMap.containsKey(entryKey))
                        preparedMap.put(entryKey, entryValue);
                }
            }
        }

        return preparedMap;
    }
}
