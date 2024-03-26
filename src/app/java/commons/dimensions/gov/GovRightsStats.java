package app.java.commons.dimensions.gov;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.EnvConst;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.stats.Initializer;
import app.java.data.stats.Preparation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.constants.Constants.EU28_MEMBERS_NAME;
import static app.java.commons.dimensions.gov.GovRightsParams.*;
import static app.java.commons.dimensions.gov.GovRightsPaths.*;

public class GovRightsStats {
    // Intermediate data which will be grouped into a single indicator
    private static final Map<String, Number>
            initPopulationLegtstTrust = Initializer.initConsolidatedMap(POPULATION_LEGTST_TRUST_PARAMS, POPULATION_TRUST_PATH),
            initPopulationOthersTrust = Initializer.initConsolidatedMap(POPULATION_OTHERS_TRUST_PARAMS, POPULATION_TRUST_PATH),
            initPopulationPlctstTrust = Initializer.initConsolidatedMap(POPULATION_PLCTST_TRUST_PARAMS, POPULATION_TRUST_PATH),
            initPopulationPlttstTrust = Initializer.initConsolidatedMap(POPULATION_PLTTST_TRUST_PARAMS, POPULATION_TRUST_PATH);
    private static final ArrayList<Map<String, Number>> voterTurnoutList = new ArrayList<>() {{
        add(voterTurnoutCsvToMap(VOTER_TURNOUT_PATH));
    }};

    private static final Map<String, Number>
            initCitizenshipRatio = Initializer.initConsolidatedMap(CITIZENSHIP_RATIO_PARAMS, CITIZENSHIP_RATIO_PATH),
            initGenderEmpGap = Initializer.initConsolidatedMap(GENDER_EMP_GAP_PARAMS, GENDER_EMP_GAP_PATH),
            initGenderPayGap = Initializer.initConsolidatedMap(GENDER_PAY_GAP_PARAMS, GENDER_PAY_GAP_PATH),
            initVoterTurnout = Initializer.initConsolidatedMaps(voterTurnoutList);

    public static final Map<String, Number>
            // Intermediate data used to calculate populationTrustRatio
            populationLegtstTrustRatio = Preparation.prepareData(initPopulationLegtstTrust),
            populationOthersTrustRatio = Preparation.prepareData(initPopulationOthersTrust),
            populationPlctstTrustRatio = Preparation.prepareData(initPopulationPlctstTrust),
            populationPlttstTrustRatio = Preparation.prepareData(initPopulationPlttstTrust),

            citizenship = Preparation.prepareData(initCitizenshipRatio),
            genderEmpGap = Preparation.prepareData(initGenderEmpGap),
            genderPayGap = Preparation.prepareData(initGenderPayGap),
            populationTrustRatio = preparePopulationTrust(),
            voterTurnout = Preparation.prepareData(initVoterTurnout);

    public static TreeMap<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(CITIZENSHIP_RATIO_FILE_NAME, Preparation.filterMap(initCitizenshipRatio));
        put(GENDER_EMP_GAP_FILE_NAME, Preparation.filterMap(initGenderEmpGap));
        put(GENDER_PAY_GAP_FILE_NAME, Preparation.filterMap(initGenderPayGap));
        put(POPULATION_LEGTST_TRUST_RATIO_FILE_NAME, Preparation.filterMap(initPopulationLegtstTrust));
        put(POPULATION_OTHERS_TRUST_RATIO_FILE_NAME, Preparation.filterMap(initPopulationOthersTrust));
        put(POPULATION_PLCTST_TRUST_RATIO_FILE_NAME, Preparation.filterMap(initPopulationPlctstTrust));
        put(POPULATION_PLTTST_TRUST_RATIO_FILE_NAME, Preparation.filterMap(initPopulationPlttstTrust));
        put(VOTER_TURNOUT_FILE_NAME, Preparation.filterMap(initVoterTurnout));
    }};

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>() {{
        put(CITIZENSHIP_RATIO_FILE_NAME, citizenship);
        put(GENDER_EMP_GAP_FILE_NAME, genderEmpGap);
        put(GENDER_PAY_GAP_FILE_NAME, genderPayGap);
        put(POPULATION_LEGTST_TRUST_RATIO_FILE_NAME, populationLegtstTrustRatio);
        put(POPULATION_OTHERS_TRUST_RATIO_FILE_NAME, populationOthersTrustRatio);
        put(POPULATION_PLCTST_TRUST_RATIO_FILE_NAME, populationPlctstTrustRatio);
        put(POPULATION_PLTTST_TRUST_RATIO_FILE_NAME, populationPlttstTrustRatio);
        put(POPULATION_TRUST_FILE_NAME, populationTrustRatio);
        put(VOTER_TURNOUT_FILE_NAME, voterTurnout);
    }};

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double
                        empGap = MathUtils.reverseGenderGap(genderEmpGap, key),
                        payGap = MathUtils.reverseGenderGap(genderPayGap, key),
                        // Transform the 1-10 notes into 1-100 notes
                        trust = populationTrustRatio.get(key).doubleValue() * 10;

                double product = 1
                        * MathUtils.percentageSafetyDouble(citizenship, key)
                        * MathUtils.percentageSafetyDouble(empGap)
                        * MathUtils.percentageSafetyDouble(payGap)
                        * MathUtils.percentageSafetyDouble(trust)
                        * MathUtils.percentageSafetyDouble(voterTurnout, key);

                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }


//        Print.printVariation(StatsUtils.generateVariation(populationTrustRatio, true));
//        Print.print(populationTrustRatio, true);

        return consolidatedList;
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        Print.printChartData(args, preparedIndicators, GOVERNANCE_FILE_NAME, EU28_MEMBERS, seriesType, direction);
    }

    /**
     * Convert the voter turnout data from csv into Java Map
     *
     * @param voterTurnoutPath The voter turnout file path
     * @return An ordered map with prepared data
     */
    private static Map<String, Number> voterTurnoutCsvToMap(String voterTurnoutPath) {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());
        String csvHeader = "Country;Election type;Year;Voter Turnout";
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(voterTurnoutPath));
            String readLine;

            while((readLine = br.readLine()) != null) {
                if (readLine.trim().length() > 0 && !readLine.contains(csvHeader)) {
                    String[] items = readLine.split(";");

                    String country = items[0].trim();
                    String voterType = items[1].trim();
                    int year = (int) Double.parseDouble(items[2].trim());
                    String valueStr = items[3].replace("%", "").trim();

                    if (valueStr.length() > 0) {
                        Number value = Double.parseDouble(valueStr);

                        for (Map.Entry<String, String> entry : EU28_MEMBERS_NAME.entrySet()) {
                            String entryKey = entry.getKey();
                            String entryValue = entry.getValue();

                            if (country.equals(entryValue)) {
                                String key = MapUtils.generateKey(entryKey, year);
                                preparedMap.put(key, value);
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file " + voterTurnoutPath + " has not been found.");
        } catch (IOException e) {
            System.err.println("Error at reading the file " + voterTurnoutPath + " from the disk.");
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.err.println("The file 'Buffered Reader' could not be closed.");
            }
        }

        return preparedMap;
    }

    /**
     * Aggregate the population trust ratios into a single index (the average)
     *
     * @return An ordered map with aggregated data
     */
    private static Map<String, Number> preparePopulationTrust() {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double valueLegtst = populationLegtstTrustRatio.get(key).doubleValue();
                double valueOthers = populationOthersTrustRatio.get(key).doubleValue();
                double valuePlctst = populationPlctstTrustRatio.get(key).doubleValue();
                double valuePlttst = populationPlttstTrustRatio.get(key).doubleValue();

                Number value = (valueLegtst + valueOthers + valuePlctst + valuePlttst) / 4;
                preparedMap.put(key, value);
            }
        }

        return preparedMap;
    }
}
