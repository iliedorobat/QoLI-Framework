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

import static app.java.commons.constants.Constants.CSV_SEPARATOR;
import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.dimensions.gov.GovRightsParams.*;
import static app.java.commons.dimensions.gov.GovRightsPaths.*;

public class GovRightsStats {
    // Intermediate data which will be grouped into a single indicator
    private static final Map<String, Number>
            initPopulationLegtstTrust = Initializer.initConsolidatedMap(POPULATION_LEGTST_TRUST_PARAMS, POPULATION_TRUST_PATH),
            initPopulationOthersTrust = Initializer.initConsolidatedMap(POPULATION_OTHERS_TRUST_PARAMS, POPULATION_TRUST_PATH),
            initPopulationPlctstTrust = Initializer.initConsolidatedMap(POPULATION_PLCTST_TRUST_PARAMS, POPULATION_TRUST_PATH),
            initPopulationPlttstTrust = Initializer.initConsolidatedMap(POPULATION_PLTTST_TRUST_PARAMS, POPULATION_TRUST_PATH);

    private static final Map<String, Map<String, Number>> initVoterTurnout = voterTurnoutCsvToMap();

    // Intermediate data which will be grouped into a single indicator
    private static final Map<String, Number>
            initTurnoutEuParliament = Initializer.initMap(initVoterTurnout.get("euParliament")),
            initTurnoutParliamentary = Initializer.initMap(initVoterTurnout.get("parliamentary")),
            initTurnoutPresidential = Initializer.initMap(initVoterTurnout.get("presidential"));


    private static final Map<String, Number>
            initCitizenshipRatio = Initializer.initConsolidatedMap(CITIZENSHIP_RATIO_PARAMS, CITIZENSHIP_RATIO_PATH),
            initGenderEmpGap = Initializer.initConsolidatedMap(GENDER_EMP_GAP_PARAMS, GENDER_EMP_GAP_PATH),
            initGenderPayGap = Initializer.initConsolidatedMap(GENDER_PAY_GAP_PARAMS, GENDER_PAY_GAP_PATH);

    public static final Map<String, Number>
            // Intermediate data used to calculate populationTrustRatio
            populationLegtstTrustRatio = Preparation.prepareData(initPopulationLegtstTrust),
            populationOthersTrustRatio = Preparation.prepareData(initPopulationOthersTrust),
            populationPlctstTrustRatio = Preparation.prepareData(initPopulationPlctstTrust),
            populationPlttstTrustRatio = Preparation.prepareData(initPopulationPlttstTrust),

            // Intermediate data used to calculate voterTurnout
            turnoutEuParliament = Preparation.prepareData(initTurnoutEuParliament),
            turnoutParliamentary = Preparation.prepareData(initTurnoutParliamentary),
            turnoutPresidential = Preparation.prepareData(initTurnoutPresidential),

            citizenship = Preparation.prepareData(initCitizenshipRatio),
            genderEmpGap = Preparation.prepareData(initGenderEmpGap),
            genderPayGap = Preparation.prepareData(initGenderPayGap),
            populationTrustRatio = preparePopulationTrust(),
            voterTurnout = prepareVoterTurnout();

    public static TreeMap<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(CITIZENSHIP_RATIO_FILE_NAME, Preparation.filterMap(initCitizenshipRatio));
        put(GENDER_EMP_GAP_FILE_NAME, Preparation.filterMap(initGenderEmpGap));
        put(GENDER_PAY_GAP_FILE_NAME, Preparation.filterMap(initGenderPayGap));
        put(POPULATION_LEGTST_TRUST_RATIO_FILE_NAME, Preparation.filterMap(initPopulationLegtstTrust));
        put(POPULATION_OTHERS_TRUST_RATIO_FILE_NAME, Preparation.filterMap(initPopulationOthersTrust));
        put(POPULATION_PLCTST_TRUST_RATIO_FILE_NAME, Preparation.filterMap(initPopulationPlctstTrust));
        put(POPULATION_PLTTST_TRUST_RATIO_FILE_NAME, Preparation.filterMap(initPopulationPlttstTrust));
        put(VOTER_TURNOUT_EU_PARLIAMENT_FILE_NAME, Preparation.filterMap(initTurnoutEuParliament));
        put(VOTER_TURNOUT_PARLIAMENTARY_FILE_NAME, Preparation.filterMap(initTurnoutParliamentary));
        put(VOTER_TURNOUT_PRESIDENTIAL_FILE_NAME, Preparation.filterMap(initTurnoutPresidential));
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
        put(VOTER_TURNOUT_EU_PARLIAMENT_FILE_NAME, turnoutEuParliament);
        put(VOTER_TURNOUT_PARLIAMENTARY_FILE_NAME, turnoutParliamentary);
        put(VOTER_TURNOUT_PRESIDENTIAL_FILE_NAME, turnoutPresidential);
        put(VOTER_TURNOUT_FILE_NAME, voterTurnout);
    }};

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double
                        reversedGenderEmpGap = MathUtils.reverseGenderGap(genderEmpGap, key),
                        reversedGenderPayGap = MathUtils.reverseGenderGap(genderPayGap, key),
                        // Transform the 1-10 notes into 10-100 notes
                        trust = populationTrustRatio.get(key).doubleValue() * 10;

                double product = 1
                        * MathUtils.percentageSafetyDouble(citizenship, key)
                        * MathUtils.percentageSafetyDouble(reversedGenderEmpGap)
                        * MathUtils.percentageSafetyDouble(reversedGenderPayGap)
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

    public static void printDataAvailability(int targetYear, boolean indStatus) {
        Print.printDataAvailability(rawIndicators, GOVERNANCE_FILE_NAME, targetYear, indStatus);
    }

    /**
     * Aggregate the population trust ratios into a single index
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

                double product = 1
                        * valueLegtst
                        * valueOthers
                        * valuePlctst
                        * valuePlttst;

                Number value = Math.pow(product, 1.0/4);
                preparedMap.put(key, value);
            }
        }

        return preparedMap;
    }

    /**
     * Aggregate the voter turnout for different type of elections into a single index
     *
     * @return An ordered map with aggregated data
     */
    private static Map<String, Number> prepareVoterTurnout() {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double valueEuParliamentary = turnoutEuParliament.get(key).doubleValue();
                double valueParliament = turnoutParliamentary.get(key).doubleValue();
                double valuePresidential = turnoutPresidential.get(key).doubleValue();

                double product = 1
                        * valueEuParliamentary
                        * valueParliament
                        * valuePresidential;

                Number value = Math.pow(product, 1.0/3);
                preparedMap.put(key, value);
            }
        }

        return preparedMap;
    }

    /**
     * Convert the voter turnout data from csv into Java Maps
     *
     * @return An ordered map with prepared data
     */
    private static Map<String, Map<String, Number>> voterTurnoutCsvToMap() {
        Map<String, Map<String, Number>> preparedMap = new HashMap<>();
        Map<String, Number> euParliamentaryMap = new TreeMap<>(new MapOrder());
        Map<String, Number> parliamentaryMap = new TreeMap<>(new MapOrder());
        Map<String, Number> presidentialMap = new TreeMap<>(new MapOrder());

        String csvHeader = "Country;ISO2;ISO3;Election Type;Year;Voter Turnout;Total vote;Registration;VAP Turnout;Voting age population;Population;Invalid votes;Compulsory voting";
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(VOTER_TURNOUT_PATH));
            String readLine;
            int i = 0;

            while((readLine = br.readLine()) != null) {
                if (i == 0) {
                    i++;
                    // Skip the header
                    continue;
                }

                if (readLine.trim().length() > 0 && !readLine.contains(csvHeader)) {
                    String[] items = readLine.split(CSV_SEPARATOR);
                    if (items.length < 6) {
                        // Voter Turnout value does not exists
                        continue;
                    }

                    String countryCode = items[1].trim();
                    if (countryCode.equalsIgnoreCase("GB")) {
                        countryCode = "UK";
                    }
                    String electionType = items[3].trim();
                    String[] dateItems = items[4].split("-");
                    int year = (int) Double.parseDouble(dateItems[0]);
                    String valueStr = items[5].replace("%", "").trim();

                    if (valueStr.length() > 0) {
                        Number value = Double.parseDouble(valueStr);

                        for (String code : EU28_MEMBERS) {
                            if (countryCode.equals(code)) {
                                String key = MapUtils.generateKey(countryCode, year);

                                switch (electionType.toLowerCase()) {
                                    case "eu parliament":
                                        euParliamentaryMap.put(key, value);
                                        break;
                                    case "parliamentary":
                                        parliamentaryMap.put(key, value);
                                        break;
                                    case "presidential":
                                        presidentialMap.put(key, value);
                                        break;
                                }
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file " + VOTER_TURNOUT_PATH + " has not been found.");
        } catch (IOException e) {
            System.err.println("Error at reading the file " + VOTER_TURNOUT_PATH + " from the disk.");
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.err.println("The file 'Buffered Reader' could not be closed.");
            }
        }

        preparedMap.put("euParliament", euParliamentaryMap);
        preparedMap.put("parliamentary", parliamentaryMap);
        preparedMap.put("presidential", presidentialMap);

        return preparedMap;
    }
}
