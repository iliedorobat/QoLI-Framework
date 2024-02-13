package app.java.commons.dimensions.gov;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.DimensionNames;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.IndicatorNames;
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
            initEmploymentFemaleRatio = Initializer.initConsolidatedMap(EMPLOYMENT_FEMALE_RATIO_PARAMS, EMPLOYMENT_RATIO_PATH),
            initEmploymentMaleRatio = Initializer.initConsolidatedMap(EMPLOYMENT_MALE_RATIO_PARAMS, EMPLOYMENT_RATIO_PATH),
            initPopulationLegtstTrust = Initializer.initConsolidatedMap(POPULATION_LEGTST_TRUST_PARAMS, POPULATION_TRUST_PATH),
            initPopulationOthersTrust = Initializer.initConsolidatedMap(POPULATION_OTHERS_TRUST_PARAMS, POPULATION_TRUST_PATH),
            initPopulationPlctstTrust = Initializer.initConsolidatedMap(POPULATION_PLCTST_TRUST_PARAMS, POPULATION_TRUST_PATH),
            initPopulationPlttstTrust = Initializer.initConsolidatedMap(POPULATION_PLTTST_TRUST_PARAMS, POPULATION_TRUST_PATH);
    private static final ArrayList<Map<String, Number>> voterTurnoutList = new ArrayList<>() {{
        add(voterTurnoutCsvToMap(VOTER_TURNOUT_PATH));
    }};

    private static final Map<String, Number>
            initCitizenshipRatio = Initializer.initConsolidatedMap(CITIZENSHIP_RATIO_PARAMS, CITIZENSHIP_RATIO_PATH),
            initGenderPayGap = Initializer.initConsolidatedMap(GENDER_PAY_GAP_PARAMS, GENDER_PAY_GAP_PATH),
            initVoterTurnout = Initializer.initConsolidatedMaps(voterTurnoutList);

    public static final Map<String, Number>
            // Intermediate data used to calculate populationTrustRatio
            populationLegtstTrustRatio = Preparation.prepareData(initPopulationLegtstTrust),
            populationOthersTrustRatio = Preparation.prepareData(initPopulationOthersTrust),
            populationPlctstTrustRatio = Preparation.prepareData(initPopulationPlctstTrust),
            populationPlttstTrustRatio = Preparation.prepareData(initPopulationPlttstTrust),

            citizenship = Preparation.prepareData(initCitizenshipRatio),
            employmentFemaleRatio = Preparation.prepareData(initEmploymentFemaleRatio),
            employmentMaleRatio = Preparation.prepareData(initEmploymentMaleRatio),
            genderEmploymentGap = prepareGenderEmploymentGap(),
            genderPayGap = Preparation.prepareData(initGenderPayGap),
            populationTrustRatio = preparePopulationTrust(),
            voterTurnout = Preparation.prepareData(initVoterTurnout);

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>(){{
        put("citizenshipRatio", citizenship);
        put("genderEmploymentGap", genderEmploymentGap);
        put("genderPayGap", genderPayGap);
        put("populationTrustRatio", populationTrustRatio);
        put("voterTurnout", voterTurnout);
    }};

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double
                        employmentGap = genderEmploymentGap.get(key).doubleValue(),
                        genderGap = genderPayGap.get(key).doubleValue(),
                        // Transform the 1-10 notes into 1-100 notes
                        trust = populationTrustRatio.get(key).doubleValue() * 10;

                double product = 1
                        * MathUtils.percentageSafetyDouble(citizenship, key)
                        * MathUtils.percentageSafetyDouble(- employmentGap)
                        * MathUtils.percentageSafetyDouble(- genderGap)
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

    public static TreeMap<String, Map<String, Number>> getInitList() {
        return new TreeMap<>() {{
            put("Citizenship Ratio", Preparation.filterMap(initCitizenshipRatio));
            put("Employment Female Ratio", Preparation.filterMap(initEmploymentFemaleRatio));
            put("Employment Male Ratio", Preparation.filterMap(initEmploymentMaleRatio));
            put("Gender Pay Gap", Preparation.filterMap(initGenderPayGap));
            put("Population Legal System Trust", Preparation.filterMap(initPopulationLegtstTrust));
            put("Population Other Systems Trust", Preparation.filterMap(initPopulationOthersTrust));
            put("Population Police Trust", Preparation.filterMap(initPopulationPlctstTrust));
            put("Population Political Trust", Preparation.filterMap(initPopulationPlttstTrust));
            put("Voter Turnout", Preparation.filterMap(initVoterTurnout));
        }};
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        HashMap<String, Map<String, Number>> indicators = new HashMap<>() {{
            put(IndicatorNames.CITIZENSHIP, citizenship);
            put(IndicatorNames.EMPLOYMENT_FEMALE_RATIO, employmentFemaleRatio);
            put(IndicatorNames.EMPLOYMENT_MALE_RATIO, employmentMaleRatio);
            put(IndicatorNames.GENDER_EMPLOYMENT_GAP, genderEmploymentGap);
            put(IndicatorNames.GENDER_PAY_GAP, genderPayGap);
            put(IndicatorNames.POPULATION_LEGTST_TRUST_RATIO, populationLegtstTrustRatio);
            put(IndicatorNames.POPULATION_OTHERS_TRUST_RATIO, populationOthersTrustRatio);
            put(IndicatorNames.POPULATION_PLCTST_TRUST_RATIO, populationPlctstTrustRatio);
            put(IndicatorNames.POPULATION_PLTTST_TRUST_RATIO, populationPlttstTrustRatio);
            put(IndicatorNames.POPULATION_TRUST, populationTrustRatio);
            put(IndicatorNames.VOTER_TURNOUT, voterTurnout);
        }};

        Print.printChartData(args, indicators, DimensionNames.GOVERNMENT, EU28_MEMBERS, seriesType, direction);
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
     * Generate the employment gender gap (male employment ratio - female employment ratio)
     *
     * @return An ordered map with prepared data
     */
    private static Map<String, Number> prepareGenderEmploymentGap() {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double femaleRatio = employmentFemaleRatio.get(key).doubleValue();
                double maleRatio = employmentMaleRatio.get(key).doubleValue();

                Number diff = maleRatio - femaleRatio;
                Number genderGap = diff.doubleValue() / maleRatio * 100;

                preparedMap.put(key, genderGap);
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

                // Get the EU average if it is missing from the country's dataset
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
