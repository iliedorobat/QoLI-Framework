package app.java.data.measurement.dao.impl;

import app.java.commons.MapOrder;
import app.java.commons.MapUtils;
import app.java.commons.MathUtils;
import app.java.commons.constants.Constants;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.dao.GovRightsStatsDAO;
import app.java.data.measurement.preparation.Initializer;
import app.java.data.measurement.preparation.Preparation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class GovRightsStatsImpl implements GovRightsStatsDAO {
    private static final String[] EU28_MEMBERS = Constants.EU28_MEMBERS;

    // The lists of queried values
    private static final String[]
            ACTIVE_CITIZENSHIP = {"TOTAL", "AC43A", "Y_GE16", "T", "PC"},
            EMPLOYMENT_FEMALE_RATIO = {"Y20-64", "PC_POP", "F", "EMP_LFS"},
            EMPLOYMENT_MALE_RATIO = {"Y20-64", "PC_POP", "M", "EMP_LFS"},
            GENDER_PAY_GAP = {"PC", "B-S_X_O"},
            POPULATION_LEGTST_TRUST_RATIO = {"RTG", "TOTAL", "LEGTST", "T", "Y_GE16"},
            POPULATION_PLCTST_TRUST_RATIO = {"RTG", "TOTAL", "PLCTST", "T", "Y_GE16"},
            POPULATION_PLTTST_TRUST_RATIO = {"RTG", "TOTAL", "PLTTST", "T", "Y_GE16"};

    private static final String JSON_EXT = Constants.JSON_EXTENSION;
    private static final String CSV_EXT = Constants.CSV_EXTENSION;
    private static final String
            activeCitizenshipPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.ACTIVE_CITIZENSHIP + JSON_EXT,
            employmentRatioPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.EMPLOYMENT + JSON_EXT,
            genderPayGapPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.GENDER_PAY_GAP + JSON_EXT,
            populationTrustRatioPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.POPULATION_TRUST_RATIO + JSON_EXT,
            voterTurnoutPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.VOTER_TURNOUT + CSV_EXT;

    // Intermediate data which should be consolidated into a single indicator
    private static final Map<String, Number>
            initEmploymentFemaleRatio = Initializer.initConsolidatedList(EMPLOYMENT_FEMALE_RATIO, employmentRatioPath),
            initEmploymentMaleRatio = Initializer.initConsolidatedList(EMPLOYMENT_MALE_RATIO, employmentRatioPath),
            initPopulationLegtstTrustRatio = Initializer.initConsolidatedList(POPULATION_LEGTST_TRUST_RATIO, populationTrustRatioPath),
            initPopulationPlctstTrustRatio = Initializer.initConsolidatedList(POPULATION_PLCTST_TRUST_RATIO, populationTrustRatioPath),
            initPopulationPlttstTrustRatio = Initializer.initConsolidatedList(POPULATION_PLTTST_TRUST_RATIO, populationTrustRatioPath);
    private static final ArrayList<Map<String, Number>> voterTurnoutList = new ArrayList<>() {{
        add(voterTurnoutCsvToMap(voterTurnoutPath));
    }};

    private static final Map<String, Number>
            initActiveCitizenship = Initializer.initConsolidatedList(ACTIVE_CITIZENSHIP, activeCitizenshipPath),
            initGenderPayGap = Initializer.initConsolidatedList(GENDER_PAY_GAP, genderPayGapPath),
            initVoterTurnout = Initializer.initConsolidatedMaps(voterTurnoutList);

    public Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                activeCitizenship = Preparation.prepareData(initActiveCitizenship),
                employmentGenderGap = consolidateEmploymentGenderGap(),
                genderPayGap = Preparation.prepareData(initGenderPayGap),
                populationTrust = consolidatePopulationTrust(),
                voterTurnout = Preparation.prepareData(initVoterTurnout);

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (int i = 0; i < Constants.EU28_MEMBERS.length; i++) {
                String code = Constants.EU28_MEMBERS[i];
                String key = MapUtils.generateKey(code, year);

                // Transform the "male - female" difference into "female - male"
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


//        Print.printVariation(Statistics.generateVariation(populationTrust, true));
//        Print.print(populationTrust, true);

        return consolidatedList;
    }

    /**
     * Convert the voter turnout data from csv into Java Map
     *
     * @param voterTurnoutPath The voter turnout file path
     * @return An ordered map with prepared data
     */
    private static Map<String, Number> voterTurnoutCsvToMap(String voterTurnoutPath) {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());
        String csvHeader = "Country;Election type;Year;Voter Turnout (%)";
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
                    Number value = Double.parseDouble(items[3].trim());

                    for (Map.Entry<String, String> entry : Constants.EU28_MEMBERS_NAME.entrySet()) {
                        String entryKey = entry.getKey();
                        String entryValue = entry.getValue();

                        if (country.equals(entryValue)) {
                            String key = MapUtils.generateKey(entryKey, year);
                            preparedMap.put(key, value);
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
    private Map<String, Number> consolidateEmploymentGenderGap() {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());
        Map<String, Number>
                employmentFemaleRatio = Preparation.prepareData(initEmploymentFemaleRatio),
                employmentMaleRatio = Preparation.prepareData(initEmploymentMaleRatio);

        for (int i = 0; i < EU28_MEMBERS.length; i++) {
            String code = EU28_MEMBERS[i];

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
    private Map<String, Number> consolidatePopulationTrust() {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());
        Map<String, Number>
            populationLegtstTrustRatio = Preparation.prepareData(initPopulationLegtstTrustRatio),
            populationPlctstTrustRatio = Preparation.prepareData(initPopulationPlctstTrustRatio),
            populationPlttstTrustRatio = Preparation.prepareData(initPopulationPlttstTrustRatio);

        for (int i = 0; i < EU28_MEMBERS.length; i++) {
            String code = EU28_MEMBERS[i];

            for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
                String key = MapUtils.generateKey(code, year);
                Number numberLegtst = populationLegtstTrustRatio.get(key),
                        numberPlctst = populationPlctstTrustRatio.get(key),
                        numberPlttst = populationPlttstTrustRatio.get(key);

                // Get the EU average for HR (HR doesn't have data for PLCTST - police)
                double valueLegtst = numberLegtst != null ? numberLegtst.doubleValue() : 4.6;
                double valuePlctst = numberPlctst != null ? numberPlctst.doubleValue() : 5.9;
                double valuePlttst = numberPlttst != null ? numberPlttst.doubleValue() : 3.5;

                Number value = (valueLegtst + valuePlctst + valuePlttst) / 3;
                preparedMap.put(key, value);
            }
        }
        
        return preparedMap;
    }
}
