package app.java.commons.dimesntions.gov;

import app.java.commons.MapOrder;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.stats.Initializer;
import app.java.data.stats.Preparation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.constants.Constants.EU28_MEMBERS_NAME;
import static app.java.commons.constants.Constants.JSON_EXTENSION;
import static app.java.commons.constants.Constants.CSV_EXTENSION;

public class GovRightsStats {
    // Queried params values
    private static final String[]
            ACTIVE_CITIZENSHIP = {"TOTAL", "AC43A", "Y_GE16", "T", "PC"},
            EMPLOYMENT_FEMALE_RATIO = {"Y20-64", "PC_POP", "F", "EMP_LFS"},
            EMPLOYMENT_MALE_RATIO = {"Y20-64", "PC_POP", "M", "EMP_LFS"},
            GENDER_PAY_GAP = {"PC", "B-S_X_O"},
            POPULATION_LEGTST_TRUST = {"RTG", "TOTAL", "LEGTST", "T", "Y_GE16"},
            POPULATION_PLCTST_TRUST = {"RTG", "TOTAL", "PLCTST", "T", "Y_GE16"},
            POPULATION_PLTTST_TRUST = {"RTG", "TOTAL", "PLTTST", "T", "Y_GE16"};

    private static final String
            activeCitizenshipPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.ACTIVE_CITIZENSHIP + JSON_EXTENSION,
            employmentRatioPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.EMPLOYMENT + JSON_EXTENSION,
            genderPayGapPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.GENDER_PAY_GAP + JSON_EXTENSION,
            populationTrustPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.POPULATION_TRUST + JSON_EXTENSION,
            voterTurnoutPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.VOTER_TURNOUT + CSV_EXTENSION;

    // Intermediate data which should be consolidated into a single indicator
    private static final Map<String, Number>
            initEmploymentFemaleRatio = Initializer.initConsolidatedMap(EMPLOYMENT_FEMALE_RATIO, employmentRatioPath),
            initEmploymentMaleRatio = Initializer.initConsolidatedMap(EMPLOYMENT_MALE_RATIO, employmentRatioPath),
            initPopulationLegtstTrust = Initializer.initConsolidatedMap(POPULATION_LEGTST_TRUST, populationTrustPath),
            initPopulationPlctstTrust = Initializer.initConsolidatedMap(POPULATION_PLCTST_TRUST, populationTrustPath),
            initPopulationPlttstTrust = Initializer.initConsolidatedMap(POPULATION_PLTTST_TRUST, populationTrustPath);
    private static final ArrayList<Map<String, Number>> voterTurnoutList = new ArrayList<>() {{
        add(voterTurnoutCsvToMap(voterTurnoutPath));
    }};

    private static final Map<String, Number>
            initActiveCitizenship = Initializer.initConsolidatedMap(ACTIVE_CITIZENSHIP, activeCitizenshipPath),
            initGenderPayGap = Initializer.initConsolidatedMap(GENDER_PAY_GAP, genderPayGapPath),
            initVoterTurnout = Initializer.initConsolidatedMaps(voterTurnoutList);

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                activeCitizenship = Preparation.prepareData(initActiveCitizenship),
                employmentGenderGap = consolidateEmploymentGenderGap(),
                genderPayGap = Preparation.prepareData(initGenderPayGap),
                populationTrust = consolidatePopulationTrust(),
                voterTurnout = Preparation.prepareData(initVoterTurnout);

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double employmentGap = - employmentGenderGap.get(key).doubleValue();
                double genderGap = - genderPayGap.get(key).doubleValue();
                // Transform the 1-10 notes into 1-100 notes
                double trust = populationTrust.get(key).doubleValue() * 10;

                double product = 1
                        * MathUtils.percentageSafetyDouble(activeCitizenship, key)
                        * MathUtils.percentageSafetyDouble(employmentGap)
                        * MathUtils.percentageSafetyDouble(genderGap)
                        * MathUtils.percentageSafetyDouble(trust)
                        * MathUtils.percentageSafetyDouble(voterTurnout, key);
                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }


//        Print.printVariation(StatsUtils.generateVariation(populationTrust, true));
//        Print.print(populationTrust, true);

        return consolidatedList;
    }

    public static ArrayList<Map<String, Number>> getInitList() {
        return new ArrayList<>() {{
            add(Preparation.filterMap(initActiveCitizenship));
            add(Preparation.filterMap(initEmploymentFemaleRatio));
            add(Preparation.filterMap(initEmploymentMaleRatio));
            add(Preparation.filterMap(initGenderPayGap));
            add(Preparation.filterMap(initPopulationLegtstTrust));
            add(Preparation.filterMap(initPopulationPlctstTrust));
            add(Preparation.filterMap(initPopulationPlttstTrust));
            add(Preparation.filterMap(initVoterTurnout));
        }};
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
                    int year = Integer.parseInt(items[2].trim());
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
    private static Map<String, Number> consolidateEmploymentGenderGap() {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());
        Map<String, Number>
                employmentFemaleRatio = Preparation.prepareData(initEmploymentFemaleRatio),
                employmentMaleRatio = Preparation.prepareData(initEmploymentMaleRatio);

        for (String code : EU28_MEMBERS) {
            for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
                String key = MapUtils.generateKey(code, year);
                double femaleRatio = employmentFemaleRatio.get(key).doubleValue();
                double maleRatio = employmentMaleRatio.get(key).doubleValue();
                Number genderGap = maleRatio - femaleRatio;
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
    private static Map<String, Number> consolidatePopulationTrust() {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());
        Map<String, Number>
            populationLegtstTrustRatio = Preparation.prepareData(initPopulationLegtstTrust),
            populationPlctstTrustRatio = Preparation.prepareData(initPopulationPlctstTrust),
            populationPlttstTrustRatio = Preparation.prepareData(initPopulationPlttstTrust);

        for (String code : EU28_MEMBERS) {
            for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
                String key = MapUtils.generateKey(code, year);

                // Get the EU average if it is missing from the country's dataset
                double valueLegtst = populationLegtstTrustRatio.get(key).doubleValue();
                double valuePlctst = populationPlctstTrustRatio.get(key).doubleValue();
                double valuePlttst = populationPlttstTrustRatio.get(key).doubleValue();

                Number value = (valueLegtst + valuePlctst + valuePlttst) / 3;
                preparedMap.put(key, value);
            }
        }

        return preparedMap;
    }
}
