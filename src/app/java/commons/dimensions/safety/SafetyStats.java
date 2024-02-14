package app.java.commons.dimensions.safety;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.IndicatorNames;
import app.java.commons.dimensions.auxiliary.AuxiliaryStats;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.stats.Initializer;
import app.java.data.stats.Preparation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.constants.Constants.EU28_MEMBERS_EXTENDED;
import static app.java.commons.dimensions.safety.SafetyParams.*;
import static app.java.commons.dimensions.safety.SafetyPaths.*;

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

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>(){{
        put("crimeRatio", crimeRatio);
        put("nonPaymentRatio", nonPaymentRatio);
        put("pensionPpsRatio", pensionPpsRatio);
        put("socialProtectionPpsRatio", socialProtectionPpsRatio);
        put("totalOffencesRatio", totalOffencesRatio);
        put("unexpectedRatio", unexpectedRatio);
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

    public static TreeMap<String, Map<String, Number>> getInitList() {
        return new TreeMap<>() {{
            put("Crime Ratio", Preparation.filterMap(initCrimeRatio));
            put("Non Payment Ratio", Preparation.filterMap(initNonPaymentRatio));
            put("Pension PPS", Preparation.filterMap(initPensionPps));
            put("Social Protection PPS", Preparation.filterMap(initSocialProtectionPps));
            put("Unexpected Ratio", Preparation.filterMap(initUnexpectedRatio));

            put("Assault Offences", Preparation.filterMap(initAssaultOffences));
            put("Attempted Homicide Offences", Preparation.filterMap(initAttemptedHomicideOffences));
            put("Bribery Offences", Preparation.filterMap(initBriberyOffences));
            put("Burglary Offences", Preparation.filterMap(initBurglaryOffences));
            put("Burglary Private Offences", Preparation.filterMap(initBurglaryPrivateOffences));
            put("Computers Offences", Preparation.filterMap(initComputersOffences));
            put("Criminal Groups Offences", Preparation.filterMap(initCriminalGroupsOffences));
            put("Corruption Offences", Preparation.filterMap(initCorruptionGroupsOffences));
            put("Fraud Offences", Preparation.filterMap(initFraudGroupsOffences));
            put("Homicide Offences", Preparation.filterMap(initHomicideOffences));
            put("Kidnapping Offences", Preparation.filterMap(initKidnappingOffences));
            put("Money Laundering Offences", Preparation.filterMap(initMoneyLaunderingOffences));
            put("Narcotics Offences", Preparation.filterMap(initNarcoticsOffences));
            put("Rape Offences", Preparation.filterMap(initRapeOffences));
            put("Robbery Offences", Preparation.filterMap(initRobberyOffences));
            put("Sexual Assault Offences", Preparation.filterMap(initSexualAssaultOffences));
            put("Sexual Exploitation Offences", Preparation.filterMap(initSexualExploitationOffences));
            put("Sexual Violence Offences", Preparation.filterMap(initSexualViolenceOffences));
            put("Theft Offences", Preparation.filterMap(initTheftOffences));
            put("Theft Vehicle Offences", Preparation.filterMap(initTheftVehicleOffences));
        }};
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        HashMap<String, Map<String, Number>> indicators = new HashMap<>() {{
            put(IndicatorNames.CRIME_RATIO, crimeRatio);
            put(IndicatorNames.NON_PAYMENT_RATIO, nonPaymentRatio);
            put(IndicatorNames.PENSION_PPS, pensionPps);
            put(IndicatorNames.SOCIAL_PROTECTION_PPS, socialProtectionPps);
            put(IndicatorNames.UNEXPECTED_RATIO, unexpectedRatio);
            put(IndicatorNames.ATTEMPTED_HOMICIDE_OFFENCES, attemptedHomicideOffences);
            put(IndicatorNames.ASSAULT_OFFENCES, assaultOffences);
            put(IndicatorNames.BRIBERY_OFFENCES, briberyOffences);
            put(IndicatorNames.BURGLARY_OFFENCES, burglaryOffences);
            put(IndicatorNames.BURGLARY_PRIVATE_OFFENCES, burglaryPrivateOffences);
            put(IndicatorNames.COMPUTERS_OFFENCES, computersOffences);
            put(IndicatorNames.CORRUPTION_OFFENCES, corruptionOffences);
            put(IndicatorNames.CRIMINAL_GROUPS_OFFENCES, criminalGroupsOffences);
            put(IndicatorNames.FRAUD_OFFENCES, fraudOffences);
            put(IndicatorNames.HOMICIDE_OFFENCES, homicideOffences);
            put(IndicatorNames.KIDNAPPING_OFFENCES, kidnappingOffences);
            put(IndicatorNames.MONEY_LAUNDERING_OFFENCES, moneyLaunderingOffences);
            put(IndicatorNames.NARCOTICS_OFFENCES, narcoticsOffences);
            put(IndicatorNames.RAPE_OFFENCES, rapeOffences);
            put(IndicatorNames.ROBBERY_OFFENCES, robberyOffences);
            put(IndicatorNames.SEXUAL_ASSAULT_OFFENCES, sexualAssaultOffences);
            put(IndicatorNames.SEXUAL_EXPLOITATION_OFFENCES, sexualExploitationOffences);
            put(IndicatorNames.SEXUAL_VIOLENCE_OFFENCES, sexualViolenceOffences);
            put(IndicatorNames.THEFT_OFFENCES, theftOffences);
            put(IndicatorNames.THEFT_VEHICLE_OFFENCES, theftVehicleOffences);
            put(IndicatorNames.TOTAL_OFFENCES_RATIO, totalOffencesRatio);
        }};

        Print.printChartData(args, indicators, SAFETY_FILE_NAME, EU28_MEMBERS, seriesType, direction);
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
