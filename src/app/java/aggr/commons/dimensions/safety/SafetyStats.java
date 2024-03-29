package app.java.aggr.commons.dimensions.safety;

import app.java.aggr.commons.MapOrder;
import app.java.aggr.commons.Print;
import app.java.aggr.commons.constants.EnvConst;
import app.java.aggr.commons.dimensions.auxiliary.AuxiliaryStats;
import app.java.aggr.commons.utils.MapUtils;
import app.java.aggr.commons.utils.MathUtils;
import app.java.aggr.data.stats.Initializer;
import app.java.aggr.data.stats.Preparation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static app.java.aggr.commons.constants.Constants.EU28_MEMBERS;
import static app.java.aggr.commons.constants.Constants.EU28_MEMBERS_EXTENDED;
import static app.java.aggr.commons.dimensions.safety.SafetyParams.*;
import static app.java.aggr.commons.dimensions.safety.SafetyPaths.*;

public class SafetyStats {
    private static final Map<String, Number>
            initCrimeRatio = Initializer.initConsolidatedMap(CRIME_RATIO_PARAMS, CRIME_RATIO_PATH),
            initNonPaymentRatio = Initializer.initConsolidatedMap(NON_PAYMENT_RATIO_PARAMS, NON_PAYMENT_RATIO_PATH),
            initPensionPps = Initializer.initConsolidatedMap(PENSION_PPS_PARAMS, PENSION_PPS_PATH),
            initSocialProtectionPps = Initializer.initConsolidatedMap(SOCIAL_PROTECTION_PPS_PARAMS, SOCIAL_PROTECTION_PPS_PATH),
            initUnexpectedRatio = Initializer.initConsolidatedMap(UNEXPECTED_RATIO_PARAMS, UNEXPECTED_RATIO_PATH),

            // Intermediate data which should be consolidated into a single indicator
            initAssaultOffences = Initializer.initConsolidatedMap(OFFENCES_ASSAULT_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED),
            initAttemptedHomicideOffences = Initializer.initConsolidatedMap(OFFENCES_ATTEMPTED_HOMICIDE_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED),
            initBriberyOffences = Initializer.initConsolidatedMap(OFFENCES_BRIBERY_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED),
            initBurglaryOffences = Initializer.initConsolidatedMap(OFFENCES_BURGLARY_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED),
            initBurglaryPrivateOffences = Initializer.initConsolidatedMap(OFFENCES_BURGLARY_PRIVATE_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED),
            initComputersOffences = Initializer.initConsolidatedMap(OFFENCES_COMPUTERS_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED),
            initCorruptionGroupsOffences = Initializer.initConsolidatedMap(OFFENCES_CORRUPTION_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED),
            initCriminalGroupsOffences = Initializer.initConsolidatedMap(OFFENCES_CRIMINAL_GROUPS_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED),
            initFraudGroupsOffences = Initializer.initConsolidatedMap(OFFENCES_FRAUD_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED),
            initHomicideOffences = Initializer.initConsolidatedMap(OFFENCES_HOMICIDE_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED),
            initKidnappingOffences = Initializer.initConsolidatedMap(OFFENCES_KIDNAPPING_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED),
            initMoneyLaunderingOffences = Initializer.initConsolidatedMap(OFFENCES_MONEY_LAUNDERING_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED),
            initNarcoticsOffences = Initializer.initConsolidatedMap(OFFENCES_NARCOTICS_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED),
            initRapeOffences = Initializer.initConsolidatedMap(OFFENCES_RAPE_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED),
            initRobberyOffences = Initializer.initConsolidatedMap(OFFENCES_ROBBERY_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED),
            initSexualViolenceOffences = Initializer.initConsolidatedMap(OFFENCES_SEXUAL_VIOLENCE_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED),
            initSexualAssaultOffences = Initializer.initConsolidatedMap(OFFENCES_SEXUAL_ASSAULT_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED),
            initSexualExploitationOffences = Initializer.initConsolidatedMap(OFFENCES_SEXUAL_EXPLOITATION_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED),
            initTheftOffences = Initializer.initConsolidatedMap(OFFENCES_THEFT_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED),
            initTheftVehicleOffences = Initializer.initConsolidatedMap(OFFENCES_THEFT_VEHICLE_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED);

    public static final Map<String, Number>
            // Intermediate data used to calculate pensionPpsRatio
            pensionPps = Preparation.prepareData(initPensionPps),

            // Intermediate data used to calculate socialProtectionPpsRatio
            socialProtectionPps = Preparation.prepareData(initSocialProtectionPps),

            // Intermediate data used to calculate totalOffencesRatio
            attemptedHomicideOffences = Preparation.prepareData(initAttemptedHomicideOffences, EU28_MEMBERS_EXTENDED),
            assaultOffences = Preparation.prepareData(initAssaultOffences, EU28_MEMBERS_EXTENDED),
            briberyOffences = Preparation.prepareData(initBriberyOffences, EU28_MEMBERS_EXTENDED),
            burglaryOffences = Preparation.prepareData(initBurglaryOffences, EU28_MEMBERS_EXTENDED),
            burglaryPrivateOffences = Preparation.prepareData(initBurglaryPrivateOffences, EU28_MEMBERS_EXTENDED),
            computersOffences = Preparation.prepareData(initComputersOffences, EU28_MEMBERS_EXTENDED),
            corruptionOffences = Preparation.prepareData(initCorruptionGroupsOffences, EU28_MEMBERS_EXTENDED),
            criminalGroupsOffences = Preparation.prepareData(initCriminalGroupsOffences, EU28_MEMBERS_EXTENDED),
            fraudOffences = Preparation.prepareData(initFraudGroupsOffences, EU28_MEMBERS_EXTENDED),
            homicideOffences = Preparation.prepareData(initHomicideOffences, EU28_MEMBERS_EXTENDED),
            kidnappingOffences = Preparation.prepareData(initKidnappingOffences, EU28_MEMBERS_EXTENDED),
            moneyLaunderingOffences = Preparation.prepareData(initMoneyLaunderingOffences, EU28_MEMBERS_EXTENDED),
            narcoticsOffences = Preparation.prepareData(initNarcoticsOffences, EU28_MEMBERS_EXTENDED),
            rapeOffences = Preparation.prepareData(initRapeOffences, EU28_MEMBERS_EXTENDED),
            robberyOffences = Preparation.prepareData(initRobberyOffences, EU28_MEMBERS_EXTENDED),
            sexualAssaultOffences = Preparation.prepareData(initSexualAssaultOffences, EU28_MEMBERS_EXTENDED),
            sexualExploitationOffences = Preparation.prepareData(initSexualExploitationOffences, EU28_MEMBERS_EXTENDED),
            sexualViolenceOffences = Preparation.prepareData(initSexualViolenceOffences, EU28_MEMBERS_EXTENDED),
            theftOffences = Preparation.prepareData(initTheftOffences, EU28_MEMBERS_EXTENDED),
            theftVehicleOffences = Preparation.prepareData(initTheftVehicleOffences, EU28_MEMBERS_EXTENDED),

            crimeRatio = Preparation.prepareData(initCrimeRatio),
            nonPaymentRatio = Preparation.prepareData(initNonPaymentRatio),
            pensionPpsRatio = Preparation.preparePpsRatio(pensionPps),
            socialProtectionPpsRatio = Preparation.preparePpsRatio(socialProtectionPps),
            totalOffencesRatio = prepareOffencesRatio(),
            unexpectedRatio = Preparation.prepareData(initUnexpectedRatio);

    public static TreeMap<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(CRIME_RATIO_FILE_NAME, Preparation.filterMap(initCrimeRatio));
        put(NON_PAYMENT_RATIO_FILE_NAME, Preparation.filterMap(initNonPaymentRatio));
        put(PENSION_PPS_FILE_NAME, Preparation.filterMap(initPensionPps));
        put(SOCIAL_PROTECTION_RATIO_FILE_NAME, Preparation.filterMap(initSocialProtectionPps));
        put(UNEXPECTED_RATIO_FILE_NAME, Preparation.filterMap(initUnexpectedRatio));

        put(OFFENCES_ASSAULT_FILE_NAME, Preparation.filterMap(initAssaultOffences));
        put(OFFENCES_ATTEMPTED_HOMICIDE_FILE_NAME, Preparation.filterMap(initAttemptedHomicideOffences));
        put(OFFENCES_BRIBERY_FILE_NAME, Preparation.filterMap(initBriberyOffences));
        put(OFFENCES_BURGLARY_FILE_NAME, Preparation.filterMap(initBurglaryOffences));
        put(OFFENCES_BURGLARY_PRIVATE_FILE_NAME, Preparation.filterMap(initBurglaryPrivateOffences));
        put(OFFENCES_COMPUTERS_FILE_NAME, Preparation.filterMap(initComputersOffences));
        put(OFFENCES_CRIMINAL_GROUPS_FILE_NAME, Preparation.filterMap(initCriminalGroupsOffences));
        put(OFFENCES_CORRUPTION_FILE_NAME, Preparation.filterMap(initCorruptionGroupsOffences));
        put(OFFENCES_FRAUD_FILE_NAME, Preparation.filterMap(initFraudGroupsOffences));
        put(OFFENCES_HOMICIDE_FILE_NAME, Preparation.filterMap(initHomicideOffences));
        put(OFFENCES_KIDNAPPING_FILE_NAME, Preparation.filterMap(initKidnappingOffences));
        put(OFFENCES_MONEY_LAUNDERING_FILE_NAME, Preparation.filterMap(initMoneyLaunderingOffences));
        put(OFFENCES_NARCOTICS_FILE_NAME, Preparation.filterMap(initNarcoticsOffences));
        put(OFFENCES_RAPE_FILE_NAME, Preparation.filterMap(initRapeOffences));
        put(OFFENCES_ROBBERY_FILE_NAME, Preparation.filterMap(initRobberyOffences));
        put(OFFENCES_SEXUAL_ASSAULT_FILE_NAME, Preparation.filterMap(initSexualAssaultOffences));
        put(OFFENCES_SEXUAL_EXPLOITATION_FILE_NAME, Preparation.filterMap(initSexualExploitationOffences));
        put(OFFENCES_SEXUAL_VIOLENCE_FILE_NAME, Preparation.filterMap(initSexualViolenceOffences));
        put(OFFENCES_THEFT_FILE_NAME, Preparation.filterMap(initTheftOffences));
        put(OFFENCES_THEFT_VEHICLE_FILE_NAME, Preparation.filterMap(initTheftVehicleOffences));
    }};

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>() {{
        put(CRIME_RATIO_FILE_NAME, crimeRatio);
        put(NON_PAYMENT_RATIO_FILE_NAME, nonPaymentRatio);
        put(PENSION_PPS_FILE_NAME, pensionPps);
        put(SOCIAL_PROTECTION_RATIO_FILE_NAME, socialProtectionPps);
        put(UNEXPECTED_RATIO_FILE_NAME, unexpectedRatio);

        put(OFFENCES_ASSAULT_FILE_NAME, assaultOffences);
        put(OFFENCES_ATTEMPTED_HOMICIDE_FILE_NAME, attemptedHomicideOffences);
        put(OFFENCES_BRIBERY_FILE_NAME, briberyOffences);
        put(OFFENCES_BURGLARY_FILE_NAME, burglaryOffences);
        put(OFFENCES_BURGLARY_PRIVATE_FILE_NAME, burglaryPrivateOffences);
        put(OFFENCES_COMPUTERS_FILE_NAME, computersOffences);
        put(OFFENCES_CRIMINAL_GROUPS_FILE_NAME, criminalGroupsOffences);
        put(OFFENCES_CORRUPTION_FILE_NAME, corruptionOffences);
        put(OFFENCES_FRAUD_FILE_NAME, fraudOffences);
        put(OFFENCES_HOMICIDE_FILE_NAME, homicideOffences);
        put(OFFENCES_KIDNAPPING_FILE_NAME, kidnappingOffences);
        put(OFFENCES_MONEY_LAUNDERING_FILE_NAME, moneyLaunderingOffences);
        put(OFFENCES_NARCOTICS_FILE_NAME, narcoticsOffences);
        put(OFFENCES_RAPE_FILE_NAME, rapeOffences);
        put(OFFENCES_ROBBERY_FILE_NAME, robberyOffences);
        put(OFFENCES_SEXUAL_ASSAULT_FILE_NAME, sexualAssaultOffences);
        put(OFFENCES_SEXUAL_EXPLOITATION_FILE_NAME, sexualExploitationOffences);
        put(OFFENCES_SEXUAL_VIOLENCE_FILE_NAME, sexualViolenceOffences);
        put(OFFENCES_THEFT_FILE_NAME, theftOffences);
        put(OFFENCES_THEFT_VEHICLE_FILE_NAME, theftVehicleOffences);
        put(OFFENCES_FILE_NAME, totalOffencesRatio);
    }};

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
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
        Print.printChartData(args, preparedIndicators, SAFETY_FILE_NAME, EU28_MEMBERS, seriesType, direction);
    }

    public static void printDataAvailability(int targetYear, boolean indStatus) {
        Print.printDataAvailability(rawIndicators, SAFETY_FILE_NAME, targetYear, indStatus);
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

            for (String code : EU28_MEMBERS_EXTENDED) {
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
