package ro.webdata.qoli.aggr.commons.dimensions.safety;

import ro.webdata.qoli.aggr.commons.MapOrder;
import ro.webdata.qoli.aggr.commons.Print;
import ro.webdata.qoli.aggr.commons.constants.EnvConst;
import ro.webdata.qoli.aggr.commons.dimensions.auxiliary.AuxiliaryStats;
import ro.webdata.qoli.aggr.commons.utils.MapUtils;
import ro.webdata.qoli.aggr.commons.utils.MathUtils;
import ro.webdata.qoli.aggr.data.stats.Initializer;
import ro.webdata.qoli.aggr.data.stats.Preparation;
import ro.webdata.qoli.aggr.commons.constants.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

    public static TreeMap<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(SafetyPaths.CRIME_RATIO_FILE_NAME, Preparation.filterMap(initCrimeRatio));
        put(SafetyPaths.NON_PAYMENT_RATIO_FILE_NAME, Preparation.filterMap(initNonPaymentRatio));
        put(SafetyPaths.PENSION_PPS_FILE_NAME, Preparation.filterMap(initPensionPps));
        put(SafetyPaths.SOCIAL_PROTECTION_RATIO_FILE_NAME, Preparation.filterMap(initSocialProtectionPps));
        put(SafetyPaths.UNEXPECTED_RATIO_FILE_NAME, Preparation.filterMap(initUnexpectedRatio));

        put(SafetyPaths.OFFENCES_ASSAULT_FILE_NAME, Preparation.filterMap(initAssaultOffences));
        put(SafetyPaths.OFFENCES_ATTEMPTED_HOMICIDE_FILE_NAME, Preparation.filterMap(initAttemptedHomicideOffences));
        put(SafetyPaths.OFFENCES_BRIBERY_FILE_NAME, Preparation.filterMap(initBriberyOffences));
        put(SafetyPaths.OFFENCES_BURGLARY_FILE_NAME, Preparation.filterMap(initBurglaryOffences));
        put(SafetyPaths.OFFENCES_BURGLARY_PRIVATE_FILE_NAME, Preparation.filterMap(initBurglaryPrivateOffences));
        put(SafetyPaths.OFFENCES_COMPUTERS_FILE_NAME, Preparation.filterMap(initComputersOffences));
        put(SafetyPaths.OFFENCES_CRIMINAL_GROUPS_FILE_NAME, Preparation.filterMap(initCriminalGroupsOffences));
        put(SafetyPaths.OFFENCES_CORRUPTION_FILE_NAME, Preparation.filterMap(initCorruptionGroupsOffences));
        put(SafetyPaths.OFFENCES_FRAUD_FILE_NAME, Preparation.filterMap(initFraudGroupsOffences));
        put(SafetyPaths.OFFENCES_HOMICIDE_FILE_NAME, Preparation.filterMap(initHomicideOffences));
        put(SafetyPaths.OFFENCES_KIDNAPPING_FILE_NAME, Preparation.filterMap(initKidnappingOffences));
        put(SafetyPaths.OFFENCES_MONEY_LAUNDERING_FILE_NAME, Preparation.filterMap(initMoneyLaunderingOffences));
        put(SafetyPaths.OFFENCES_NARCOTICS_FILE_NAME, Preparation.filterMap(initNarcoticsOffences));
        put(SafetyPaths.OFFENCES_RAPE_FILE_NAME, Preparation.filterMap(initRapeOffences));
        put(SafetyPaths.OFFENCES_ROBBERY_FILE_NAME, Preparation.filterMap(initRobberyOffences));
        put(SafetyPaths.OFFENCES_SEXUAL_ASSAULT_FILE_NAME, Preparation.filterMap(initSexualAssaultOffences));
        put(SafetyPaths.OFFENCES_SEXUAL_EXPLOITATION_FILE_NAME, Preparation.filterMap(initSexualExploitationOffences));
        put(SafetyPaths.OFFENCES_SEXUAL_VIOLENCE_FILE_NAME, Preparation.filterMap(initSexualViolenceOffences));
        put(SafetyPaths.OFFENCES_THEFT_FILE_NAME, Preparation.filterMap(initTheftOffences));
        put(SafetyPaths.OFFENCES_THEFT_VEHICLE_FILE_NAME, Preparation.filterMap(initTheftVehicleOffences));
    }};

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>() {{
        put(SafetyPaths.CRIME_RATIO_FILE_NAME, crimeRatio);
        put(SafetyPaths.NON_PAYMENT_RATIO_FILE_NAME, nonPaymentRatio);
        put(SafetyPaths.PENSION_PPS_FILE_NAME, pensionPps);
        put(SafetyPaths.SOCIAL_PROTECTION_RATIO_FILE_NAME, socialProtectionPps);
        put(SafetyPaths.UNEXPECTED_RATIO_FILE_NAME, unexpectedRatio);

        put(SafetyPaths.OFFENCES_ASSAULT_FILE_NAME, assaultOffences);
        put(SafetyPaths.OFFENCES_ATTEMPTED_HOMICIDE_FILE_NAME, attemptedHomicideOffences);
        put(SafetyPaths.OFFENCES_BRIBERY_FILE_NAME, briberyOffences);
        put(SafetyPaths.OFFENCES_BURGLARY_FILE_NAME, burglaryOffences);
        put(SafetyPaths.OFFENCES_BURGLARY_PRIVATE_FILE_NAME, burglaryPrivateOffences);
        put(SafetyPaths.OFFENCES_COMPUTERS_FILE_NAME, computersOffences);
        put(SafetyPaths.OFFENCES_CRIMINAL_GROUPS_FILE_NAME, criminalGroupsOffences);
        put(SafetyPaths.OFFENCES_CORRUPTION_FILE_NAME, corruptionOffences);
        put(SafetyPaths.OFFENCES_FRAUD_FILE_NAME, fraudOffences);
        put(SafetyPaths.OFFENCES_HOMICIDE_FILE_NAME, homicideOffences);
        put(SafetyPaths.OFFENCES_KIDNAPPING_FILE_NAME, kidnappingOffences);
        put(SafetyPaths.OFFENCES_MONEY_LAUNDERING_FILE_NAME, moneyLaunderingOffences);
        put(SafetyPaths.OFFENCES_NARCOTICS_FILE_NAME, narcoticsOffences);
        put(SafetyPaths.OFFENCES_RAPE_FILE_NAME, rapeOffences);
        put(SafetyPaths.OFFENCES_ROBBERY_FILE_NAME, robberyOffences);
        put(SafetyPaths.OFFENCES_SEXUAL_ASSAULT_FILE_NAME, sexualAssaultOffences);
        put(SafetyPaths.OFFENCES_SEXUAL_EXPLOITATION_FILE_NAME, sexualExploitationOffences);
        put(SafetyPaths.OFFENCES_SEXUAL_VIOLENCE_FILE_NAME, sexualViolenceOffences);
        put(SafetyPaths.OFFENCES_THEFT_FILE_NAME, theftOffences);
        put(SafetyPaths.OFFENCES_THEFT_VEHICLE_FILE_NAME, theftVehicleOffences);
        put(SafetyPaths.OFFENCES_FILE_NAME, totalOffencesRatio);
    }};

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : Constants.EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double product = 1
                        * MathUtils.percentageSafetyDouble(pensionPpsRatio, key)
                        * MathUtils.percentageSafetyDouble(socialProtectionPpsRatio, key)
                        * MathUtils.percentageSafetyDouble(crimeRatio, key, true)
                        * MathUtils.percentageSafetyDouble(nonPaymentRatio, key, true)
                        * MathUtils.percentageSafetyDouble(totalOffencesRatio, key, true)
                        * MathUtils.percentageSafetyDouble(unexpectedRatio, key, true);

                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(StatsUtils.generateVariation(offencesRatio, true));
//        Print.print(offencesRatio, true);

        return consolidatedList;
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        Print.printChartData(args, preparedIndicators, SafetyPaths.SAFETY_FILE_NAME, Constants.EU28_MEMBERS, seriesType, direction);
    }

    public static void printDataAvailability(int targetYear, boolean indStatus) {
        Print.printDataAvailability(rawIndicators, SafetyPaths.SAFETY_FILE_NAME, targetYear, indStatus);
    }

    /**
     * Aggregate all the offences types into the total offences index
     *
     * @return A new sorted map which contains the consolidated indicator
     */
    private static Map<String, Number> prepareOffencesRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
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
            consolidatedList.put(MapUtils.generateKey("UK", year), ukValue);
        }

        return consolidatedList;
    }
}
