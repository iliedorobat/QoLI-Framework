package ro.webdata.qoli.aggr.stats.dimensions.safety;

import ro.webdata.qoli.EnvState;
import ro.webdata.qoli.aggr.data.stats.Initializer;
import ro.webdata.qoli.aggr.data.stats.Preparation;
import ro.webdata.qoli.aggr.stats.MapOrder;
import ro.webdata.qoli.aggr.stats.Print;
import ro.webdata.qoli.aggr.stats.constants.Constants;
import ro.webdata.qoli.aggr.stats.dimensions.auxiliary.AuxiliaryStats;
import ro.webdata.qoli.aggr.stats.utils.MapUtils;
import ro.webdata.qoli.aggr.stats.utils.MathUtils;
import ro.webdata.qoli.aggr.stats.utils.StatsUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static ro.webdata.qoli.aggr.stats.dimensions.safety.SafetyAggrParams.*;

public class SafetyStats {
    private static final Map<String, Number>
            initCrimeRatio = Initializer.initConsolidatedMap(SafetyParams.CRIME_RATIO_PARAMS, SafetyPaths.CRIME_RATIO_PATH),
            initNonPaymentRatio = Initializer.initConsolidatedMap(SafetyParams.NON_PAYMENT_RATIO_PARAMS, SafetyPaths.NON_PAYMENT_RATIO_PATH),
            initPensionPps = Initializer.initConsolidatedMap(SafetyParams.PENSION_PPS_PARAMS, SafetyPaths.PENSION_PPS_PATH),
            initSocialProtectionPps = Initializer.initConsolidatedMap(SafetyParams.SOCIAL_PROTECTION_PPS_PARAMS, SafetyPaths.SOCIAL_PROTECTION_PPS_PATH),
            initUnexpectedRatio = Initializer.initConsolidatedMap(SafetyParams.UNEXPECTED_RATIO_PARAMS, SafetyPaths.UNEXPECTED_RATIO_PATH),

            // Intermediate data which should be consolidated into a single indicator
            initAssaultOffences = Initializer.initConsolidatedMap(SafetyParams.OFFENCES_ASSAULT_PARAMS, SafetyPaths.OFFENCES_PATH, Constants.EU28_MEMBERS_EXTENDED),
            initAttemptedHomicideOffences = Initializer.initConsolidatedMap(SafetyParams.OFFENCES_ATTEMPTED_HOMICIDE_PARAMS, SafetyPaths.OFFENCES_PATH, Constants.EU28_MEMBERS_EXTENDED),
            initBriberyOffences = Initializer.initConsolidatedMap(SafetyParams.OFFENCES_BRIBERY_PARAMS, SafetyPaths.OFFENCES_PATH, Constants.EU28_MEMBERS_EXTENDED),
            initBurglaryOffences = Initializer.initConsolidatedMap(SafetyParams.OFFENCES_BURGLARY_PARAMS, SafetyPaths.OFFENCES_PATH, Constants.EU28_MEMBERS_EXTENDED),
            initBurglaryPrivateOffences = Initializer.initConsolidatedMap(SafetyParams.OFFENCES_BURGLARY_PRIVATE_PARAMS, SafetyPaths.OFFENCES_PATH, Constants.EU28_MEMBERS_EXTENDED),
            initComputersOffences = Initializer.initConsolidatedMap(SafetyParams.OFFENCES_COMPUTERS_PARAMS, SafetyPaths.OFFENCES_PATH, Constants.EU28_MEMBERS_EXTENDED),
            initCorruptionGroupsOffences = Initializer.initConsolidatedMap(SafetyParams.OFFENCES_CORRUPTION_PARAMS, SafetyPaths.OFFENCES_PATH, Constants.EU28_MEMBERS_EXTENDED),
            initCriminalGroupsOffences = Initializer.initConsolidatedMap(SafetyParams.OFFENCES_CRIMINAL_GROUPS_PARAMS, SafetyPaths.OFFENCES_PATH, Constants.EU28_MEMBERS_EXTENDED),
            initFraudGroupsOffences = Initializer.initConsolidatedMap(SafetyParams.OFFENCES_FRAUD_PARAMS, SafetyPaths.OFFENCES_PATH, Constants.EU28_MEMBERS_EXTENDED),
            initHomicideOffences = Initializer.initConsolidatedMap(SafetyParams.OFFENCES_HOMICIDE_PARAMS, SafetyPaths.OFFENCES_PATH, Constants.EU28_MEMBERS_EXTENDED),
            initKidnappingOffences = Initializer.initConsolidatedMap(SafetyParams.OFFENCES_KIDNAPPING_PARAMS, SafetyPaths.OFFENCES_PATH, Constants.EU28_MEMBERS_EXTENDED),
            initMoneyLaunderingOffences = Initializer.initConsolidatedMap(SafetyParams.OFFENCES_MONEY_LAUNDERING_PARAMS, SafetyPaths.OFFENCES_PATH, Constants.EU28_MEMBERS_EXTENDED),
            initNarcoticsOffences = Initializer.initConsolidatedMap(SafetyParams.OFFENCES_NARCOTICS_PARAMS, SafetyPaths.OFFENCES_PATH, Constants.EU28_MEMBERS_EXTENDED),
            initRapeOffences = Initializer.initConsolidatedMap(SafetyParams.OFFENCES_RAPE_PARAMS, SafetyPaths.OFFENCES_PATH, Constants.EU28_MEMBERS_EXTENDED),
            initRobberyOffences = Initializer.initConsolidatedMap(SafetyParams.OFFENCES_ROBBERY_PARAMS, SafetyPaths.OFFENCES_PATH, Constants.EU28_MEMBERS_EXTENDED),
            initSexualViolenceOffences = Initializer.initConsolidatedMap(SafetyParams.OFFENCES_SEXUAL_VIOLENCE_PARAMS, SafetyPaths.OFFENCES_PATH, Constants.EU28_MEMBERS_EXTENDED),
            initSexualAssaultOffences = Initializer.initConsolidatedMap(SafetyParams.OFFENCES_SEXUAL_ASSAULT_PARAMS, SafetyPaths.OFFENCES_PATH, Constants.EU28_MEMBERS_EXTENDED),
            initSexualExploitationOffences = Initializer.initConsolidatedMap(SafetyParams.OFFENCES_SEXUAL_EXPLOITATION_PARAMS, SafetyPaths.OFFENCES_PATH, Constants.EU28_MEMBERS_EXTENDED),
            initTheftOffences = Initializer.initConsolidatedMap(SafetyParams.OFFENCES_THEFT_PARAMS, SafetyPaths.OFFENCES_PATH, Constants.EU28_MEMBERS_EXTENDED),
            initTheftVehicleOffences = Initializer.initConsolidatedMap(SafetyParams.OFFENCES_THEFT_VEHICLE_PARAMS, SafetyPaths.OFFENCES_PATH, Constants.EU28_MEMBERS_EXTENDED);

    public static final Map<String, Number>
            // Intermediate data used to calculate pensionPpsRatio
            pensionPps = Preparation.prepareData(initPensionPps),

            // Intermediate data used to calculate socialProtectionPpsRatio
            socialProtectionPps = Preparation.prepareData(initSocialProtectionPps),

            // Intermediate data used to calculate totalOffencesRatio
            attemptedHomicideOffences = Preparation.prepareData(initAttemptedHomicideOffences, Constants.EU28_MEMBERS_EXTENDED),
            assaultOffences = Preparation.prepareData(initAssaultOffences, Constants.EU28_MEMBERS_EXTENDED),
            briberyOffences = Preparation.prepareData(initBriberyOffences, Constants.EU28_MEMBERS_EXTENDED),
            burglaryOffences = Preparation.prepareData(initBurglaryOffences, Constants.EU28_MEMBERS_EXTENDED),
            burglaryPrivateOffences = Preparation.prepareData(initBurglaryPrivateOffences, Constants.EU28_MEMBERS_EXTENDED),
            computersOffences = Preparation.prepareData(initComputersOffences, Constants.EU28_MEMBERS_EXTENDED),
            corruptionOffences = Preparation.prepareData(initCorruptionGroupsOffences, Constants.EU28_MEMBERS_EXTENDED),
            criminalGroupsOffences = Preparation.prepareData(initCriminalGroupsOffences, Constants.EU28_MEMBERS_EXTENDED),
            fraudOffences = Preparation.prepareData(initFraudGroupsOffences, Constants.EU28_MEMBERS_EXTENDED),
            homicideOffences = Preparation.prepareData(initHomicideOffences, Constants.EU28_MEMBERS_EXTENDED),
            kidnappingOffences = Preparation.prepareData(initKidnappingOffences, Constants.EU28_MEMBERS_EXTENDED),
            moneyLaunderingOffences = Preparation.prepareData(initMoneyLaunderingOffences, Constants.EU28_MEMBERS_EXTENDED),
            narcoticsOffences = Preparation.prepareData(initNarcoticsOffences, Constants.EU28_MEMBERS_EXTENDED),
            rapeOffences = Preparation.prepareData(initRapeOffences, Constants.EU28_MEMBERS_EXTENDED),
            robberyOffences = Preparation.prepareData(initRobberyOffences, Constants.EU28_MEMBERS_EXTENDED),
            sexualAssaultOffences = Preparation.prepareData(initSexualAssaultOffences, Constants.EU28_MEMBERS_EXTENDED),
            sexualExploitationOffences = Preparation.prepareData(initSexualExploitationOffences, Constants.EU28_MEMBERS_EXTENDED),
            sexualViolenceOffences = Preparation.prepareData(initSexualViolenceOffences, Constants.EU28_MEMBERS_EXTENDED),
            theftOffences = Preparation.prepareData(initTheftOffences, Constants.EU28_MEMBERS_EXTENDED),
            theftVehicleOffences = Preparation.prepareData(initTheftVehicleOffences, Constants.EU28_MEMBERS_EXTENDED),

            crimeRatio = Preparation.prepareData(initCrimeRatio),
            nonPaymentRatio = Preparation.prepareData(initNonPaymentRatio),
            pensionPpsRatio = Preparation.preparePpsRatio(pensionPps),
            socialProtectionPpsRatio = Preparation.preparePpsRatio(socialProtectionPps),
            totalOffencesRatio = prepareOffencesRatio(),
            unexpectedRatio = Preparation.prepareData(initUnexpectedRatio);

    public static Map<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(CRIME_RATIO, Preparation.filterMap(initCrimeRatio));
        put(NON_PAYMENT_RATIO, Preparation.filterMap(initNonPaymentRatio));
        put(PENSION_PPS_RATIO, Preparation.filterMap(initPensionPps));
        put(SOCIAL_PROTECTION_PPS_RATIO, Preparation.filterMap(initSocialProtectionPps));
        put(UNEXPECTED_RATIO, Preparation.filterMap(initUnexpectedRatio));

        put(OFFENCES_ASSAULT, Preparation.filterMap(initAssaultOffences));
        put(OFFENCES_ATTEMPTED_HOMICIDE, Preparation.filterMap(initAttemptedHomicideOffences));
        put(OFFENCES_BRIBERY, Preparation.filterMap(initBriberyOffences));
        put(OFFENCES_BURGLARY, Preparation.filterMap(initBurglaryOffences));
        put(OFFENCES_BURGLARY_PRIVATE, Preparation.filterMap(initBurglaryPrivateOffences));
        put(OFFENCES_COMPUTERS, Preparation.filterMap(initComputersOffences));
        put(OFFENCES_CRIMINAL_GROUPS, Preparation.filterMap(initCriminalGroupsOffences));
        put(OFFENCES_CORRUPTION, Preparation.filterMap(initCorruptionGroupsOffences));
        put(OFFENCES_FRAUD, Preparation.filterMap(initFraudGroupsOffences));
        put(OFFENCES_HOMICIDE, Preparation.filterMap(initHomicideOffences));
        put(OFFENCES_KIDNAPPING, Preparation.filterMap(initKidnappingOffences));
        put(OFFENCES_MONEY_LAUNDERING, Preparation.filterMap(initMoneyLaunderingOffences));
        put(OFFENCES_NARCOTICS, Preparation.filterMap(initNarcoticsOffences));
        put(OFFENCES_RAPE, Preparation.filterMap(initRapeOffences));
        put(OFFENCES_ROBBERY, Preparation.filterMap(initRobberyOffences));
        put(OFFENCES_SEXUAL_ASSAULT, Preparation.filterMap(initSexualAssaultOffences));
        put(OFFENCES_SEXUAL_EXPLOITATION, Preparation.filterMap(initSexualExploitationOffences));
        put(OFFENCES_SEXUAL_VIOLENCE, Preparation.filterMap(initSexualViolenceOffences));
        put(OFFENCES_THEFT, Preparation.filterMap(initTheftOffences));
        put(OFFENCES_THEFT_VEHICLE, Preparation.filterMap(initTheftVehicleOffences));
    }};

    public static final Map<String, Map<String, Number>> aggrIndicators = new HashMap<>() {{
        put(CRIME_RATIO, crimeRatio);
        put(NON_PAYMENT_RATIO, nonPaymentRatio);
        put(OFFENCES_RATIO, totalOffencesRatio);
        put(PENSION_PPS_RATIO, pensionPpsRatio);
        put(SOCIAL_PROTECTION_PPS_RATIO, socialProtectionPpsRatio);
        put(UNEXPECTED_RATIO, unexpectedRatio);
    }};

    public static final Map<String, Map<String, Number>> baseIndicators = new HashMap<>() {{
        put(CRIME_RATIO, crimeRatio);
        put(NON_PAYMENT_RATIO, nonPaymentRatio);
        put(OFFENCES_ASSAULT, prepareOffences(assaultOffences));
        put(OFFENCES_ATTEMPTED_HOMICIDE, prepareOffences(attemptedHomicideOffences));
        put(OFFENCES_BRIBERY, prepareOffences(briberyOffences));
        put(OFFENCES_BURGLARY, prepareOffences(burglaryOffences));
        put(OFFENCES_BURGLARY_PRIVATE, prepareOffences(burglaryPrivateOffences));
        put(OFFENCES_COMPUTERS, prepareOffences(computersOffences));
        put(OFFENCES_CRIMINAL_GROUPS, prepareOffences(criminalGroupsOffences));
        put(OFFENCES_CORRUPTION, prepareOffences(corruptionOffences));
        put(OFFENCES_FRAUD, prepareOffences(fraudOffences));
        put(OFFENCES_HOMICIDE, prepareOffences(homicideOffences));
        put(OFFENCES_KIDNAPPING, prepareOffences(kidnappingOffences));
        put(OFFENCES_MONEY_LAUNDERING, prepareOffences(moneyLaunderingOffences));
        put(OFFENCES_NARCOTICS, prepareOffences(narcoticsOffences));
        put(OFFENCES_RAPE, prepareOffences(rapeOffences));
        put(OFFENCES_ROBBERY, prepareOffences(robberyOffences));
        put(OFFENCES_SEXUAL_ASSAULT, prepareOffences(sexualAssaultOffences));
        put(OFFENCES_SEXUAL_EXPLOITATION, prepareOffences(sexualExploitationOffences));
        put(OFFENCES_SEXUAL_VIOLENCE, prepareOffences(sexualViolenceOffences));
        put(OFFENCES_THEFT, prepareOffences(theftOffences));
        put(OFFENCES_THEFT_VEHICLE, prepareOffences(theftVehicleOffences));
        put(PENSION_PPS_RATIO, pensionPpsRatio);
        put(SOCIAL_PROTECTION_PPS_RATIO, socialProtectionPpsRatio);
        put(UNEXPECTED_RATIO, unexpectedRatio);
    }};

    public static Map<String, Number> generateAggrStats(List<String> aggrList, List<String> countryCodes, int startYear, int endYear) {
        return StatsUtils.generateStats(aggrList, countryCodes, startYear, endYear, SAFETY, AGGR_PARAMS, AGGR_REVERSED_STATES, aggrIndicators);
    }

    public static Map<String, Number> generateBaseStats(List<String> aggrList, List<String> countryCodes, int startYear, int endYear) {
        return StatsUtils.generateStats(aggrList, countryCodes, startYear, endYear, SAFETY, IND_PARAMS, IND_REVERSED_STATES, baseIndicators);
    }

    public static void printAggrIndicators(List<String> args, String seriesType, String direction) {
        Print.printChartData(args, aggrIndicators, SAFETY, Constants.EU28_MEMBERS, seriesType, direction);
    }

    public static void printDataAvailability(int targetYear, boolean indStatus) {
        Print.printDataAvailability(rawIndicators, SAFETY, targetYear, indStatus);
    }

    // Add the offences values of "UKC-L", "UKM" and "UKN" into a single value ("UK")
    private static Map<String, Number> prepareOffences(Map<String, Number> offence) {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvState.MIN_YEAR; year <= EnvState.MAX_YEAR; year++) {
            double ukValue = 0;

            for (String code : Constants.EU28_MEMBERS_EXTENDED) {
                String key = MapUtils.generateKey(code, year);

                double value = offence.get(key).doubleValue();

                if (code.equals("UKC-L") || code.equals("UKM") || code.equals("UKN")) {
                    ukValue += value;
                } else {
                    consolidatedList.put(key, value);
                }
            }

            String key = MapUtils.generateKey("UK", year);
            consolidatedList.put(key, ukValue);
        }

        return consolidatedList;
    }

    // Aggregate all the offences types into the total offences index
    private static Map<String, Number> prepareOffencesRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvState.MIN_YEAR; year <= EnvState.MAX_YEAR; year++) {
            double ukSum = 0;

            for (String code : Constants.EU28_MEMBERS_EXTENDED) {
                String key = MapUtils.generateKey(code, year);

                double sum = 0
                        + attemptedHomicideOffences.get(key).doubleValue()
                        + assaultOffences.get(key).doubleValue()
                        + briberyOffences.get(key).doubleValue()
                        + burglaryOffences.get(key).doubleValue()
                        + burglaryPrivateOffences.get(key).doubleValue()
                        + computersOffences.get(key).doubleValue()
                        + corruptionOffences.get(key).doubleValue()
                        + criminalGroupsOffences.get(key).doubleValue()
                        + fraudOffences.get(key).doubleValue()
                        + homicideOffences.get(key).doubleValue()
                        + kidnappingOffences.get(key).doubleValue()
                        + moneyLaunderingOffences.get(key).doubleValue()
                        + narcoticsOffences.get(key).doubleValue()
                        + rapeOffences.get(key).doubleValue()
                        + robberyOffences.get(key).doubleValue()
                        + sexualAssaultOffences.get(key).doubleValue()
                        + sexualExploitationOffences.get(key).doubleValue()
                        + sexualViolenceOffences.get(key).doubleValue()
                        + theftOffences.get(key).doubleValue()
                        + theftVehicleOffences.get(key).doubleValue();

                if (code.equals("UKC-L") || code.equals("UKM") || code.equals("UKN")) {
                    ukSum += sum;
                } else {
                    Number value = MathUtils.generatePerHundredInhabitants(AuxiliaryStats.population, key, sum);
                    consolidatedList.put(key, value);
                }
            }

            String key = MapUtils.generateKey("UK", year);
            Number ukValue = MathUtils.generatePerHundredInhabitants(AuxiliaryStats.population, key, ukSum);
            consolidatedList.put(key, ukValue);
        }

        return consolidatedList;
    }
}
