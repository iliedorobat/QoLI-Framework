package ro.webdata.qoli.aggr.stats.dimensions.gov;

import ro.webdata.qoli.aggr.data.stats.Initializer;
import ro.webdata.qoli.aggr.data.stats.Preparation;
import ro.webdata.qoli.aggr.stats.MapOrder;
import ro.webdata.qoli.aggr.stats.Print;
import ro.webdata.qoli.aggr.stats.constants.Constants;
import ro.webdata.qoli.aggr.stats.utils.MapUtils;
import ro.webdata.qoli.aggr.stats.utils.StatsUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static ro.webdata.qoli.aggr.stats.dimensions.gov.GovRightsAggrParams.*;

public class GovRightsStats {
    // Intermediate data which will be grouped into a single indicator
    private static final Map<String, Number>
            initPopulationLegtstTrust = Initializer.initConsolidatedMap(GovRightsParams.POPULATION_LEGAL_TRUST_PARAMS, GovRightsPaths.POPULATION_TRUST_PATH),
            initPopulationOthersTrust = Initializer.initConsolidatedMap(GovRightsParams.POPULATION_OTHER_TRUST_PARAMS, GovRightsPaths.POPULATION_TRUST_OTHER_PATH),
            initPopulationPlctstTrust = Initializer.initConsolidatedMap(GovRightsParams.POPULATION_POLICE_TRUST_PARAMS, GovRightsPaths.POPULATION_TRUST_PATH),
            initPopulationPlttstTrust = Initializer.initConsolidatedMap(GovRightsParams.POPULATION_POLITIC_TRUST_PARAMS, GovRightsPaths.POPULATION_TRUST_PATH);

    private static final Map<String, Map<String, Number>> initVoterTurnout = voterTurnoutCsvToMap();

    // Intermediate data which will be grouped into a single indicator
    private static final Map<String, Number>
            initTurnoutEuParliament = Initializer.initMap(initVoterTurnout.get("euParliament")),
            initTurnoutParliamentary = Initializer.initMap(initVoterTurnout.get("parliamentary")),
            initTurnoutPresidential = Initializer.initMap(initVoterTurnout.get("presidential"));


    private static final Map<String, Number>
            initCitizenshipRatio = Initializer.initConsolidatedMap(GovRightsParams.CITIZENSHIP_RATIO_PARAMS, GovRightsPaths.CITIZENSHIP_RATIO_PATH),
            initGenderEmpGap = Initializer.initConsolidatedMap(GovRightsParams.GENDER_EMP_GAP_PARAMS, GovRightsPaths.GENDER_EMP_GAP_PATH),
            initGenderPayGap = Initializer.initConsolidatedMap(GovRightsParams.GENDER_PAY_GAP_PARAMS, GovRightsPaths.GENDER_PAY_GAP_PATH);

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
            // Aggregate the population trust ratios into a single index
            populationTrustRatio = StatsUtils.calculateGeometricMean(
                    new ArrayList<>() {{
                        add(populationLegtstTrustRatio);
                        add(populationOthersTrustRatio);
                        add(populationPlctstTrustRatio);
                        add(populationPlttstTrustRatio);
                    }},
                    10.0
            ),
            // Aggregate the voter turnout for different type of elections into a single index
            voterTurnout = StatsUtils.calculateGeometricMean(
                    new ArrayList<>() {{
                        add(turnoutEuParliament);
                        add(turnoutParliamentary);
                        add(turnoutPresidential);
                    }}
            );

    public static Map<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(CITIZENSHIP_RATIO, Preparation.filterMap(initCitizenshipRatio));
        put(GENDER_EMP_GAP, Preparation.filterMap(initGenderEmpGap));
        put(GENDER_PAY_GAP, Preparation.filterMap(initGenderPayGap));
        put(POPULATION_TRUST_LEGAL, Preparation.filterMap(initPopulationLegtstTrust));
        put(POPULATION_TRUST_OTHER, Preparation.filterMap(initPopulationOthersTrust));
        put(POPULATION_TRUST_POLICE, Preparation.filterMap(initPopulationPlctstTrust));
        put(POPULATION_TRUST_POLITIC, Preparation.filterMap(initPopulationPlttstTrust));
        put(VOTER_TURNOUT_EU_PARLIAMENT, Preparation.filterMap(initTurnoutEuParliament));
        put(VOTER_TURNOUT_PARLIAMENTARY, Preparation.filterMap(initTurnoutParliamentary));
        put(VOTER_TURNOUT_PRESIDENTIAL, Preparation.filterMap(initTurnoutPresidential));
    }};

    public static final Map<String, Map<String, Number>> aggrIndicators = new HashMap<>() {{
        put(CITIZENSHIP_RATIO, citizenship);
        put(GENDER_EMP_GAP, genderEmpGap);
        put(GENDER_PAY_GAP, genderPayGap);
        put(POPULATION_TRUST, populationTrustRatio);
        put(VOTER_TURNOUT, voterTurnout);
    }};

    public static final Map<String, Map<String, Number>> baseIndicators = new HashMap<>() {{
        put(CITIZENSHIP_RATIO, citizenship);
        put(GENDER_EMP_GAP, genderEmpGap);
        put(GENDER_PAY_GAP, genderPayGap);
        put(POPULATION_TRUST_LEGAL, populationLegtstTrustRatio);
        put(POPULATION_TRUST_OTHER, populationOthersTrustRatio);
        put(POPULATION_TRUST_POLICE, populationPlctstTrustRatio);
        put(POPULATION_TRUST_POLITIC, populationPlttstTrustRatio);
        put(POPULATION_TRUST, populationTrustRatio);
        put(VOTER_TURNOUT_EU_PARLIAMENT, turnoutEuParliament);
        put(VOTER_TURNOUT_PARLIAMENTARY, turnoutParliamentary);
        put(VOTER_TURNOUT_PRESIDENTIAL, turnoutPresidential);
    }};

    public static Map<String, Number> generateAggrStats(List<String> aggrList, List<String> countryCodes, int startYear, int endYear) {
        return StatsUtils.generateStats(aggrList, countryCodes, startYear, endYear, GOVERNANCE, AGGR_PARAMS, AGGR_REVERSED_STATES, aggrIndicators);
    }

    public static Map<String, Number> generateBaseStats(List<String> aggrList, List<String> countryCodes, int startYear, int endYear) {
        return StatsUtils.generateStats(aggrList, countryCodes, startYear, endYear, GOVERNANCE, IND_PARAMS, IND_REVERSED_STATES, baseIndicators);
    }

    public static void printAggrIndicators(List<String> args, String seriesType, String direction) {
        Print.printChartData(args, aggrIndicators, GOVERNANCE, Constants.EU28_MEMBERS, seriesType, direction);
    }

    public static void printDataAvailability(int targetYear, boolean indStatus) {
        Print.printDataAvailability(rawIndicators, GOVERNANCE, targetYear, indStatus);
    }

    // Convert the voter turnout data from csv into Java Map
    private static Map<String, Map<String, Number>> voterTurnoutCsvToMap() {
        Map<String, Map<String, Number>> preparedMap = new HashMap<>();
        Map<String, Number> euParliamentaryMap = new TreeMap<>(new MapOrder());
        Map<String, Number> parliamentaryMap = new TreeMap<>(new MapOrder());
        Map<String, Number> presidentialMap = new TreeMap<>(new MapOrder());

        String csvHeader = "Country;ISO2;ISO3;Election Type;Year;Voter Turnout;Total vote;Registration;VAP Turnout;Voting age population;Population;Invalid votes;Compulsory voting";
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(GovRightsPaths.VOTER_TURNOUT_PATH));
            String readLine;
            int i = 0;

            while((readLine = br.readLine()) != null) {
                if (i == 0) {
                    i++;
                    // Skip the header
                    continue;
                }

                if (readLine.trim().length() > 0 && !readLine.contains(csvHeader)) {
                    String[] items = readLine.split(Constants.CSV_SEPARATOR);
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

                        for (String code : Constants.EU28_MEMBERS) {
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
            System.err.println("The file " + GovRightsPaths.VOTER_TURNOUT_PATH + " has not been found.");
        } catch (IOException e) {
            System.err.println("Error at reading the file " + GovRightsPaths.VOTER_TURNOUT_PATH + " from the disk.");
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
