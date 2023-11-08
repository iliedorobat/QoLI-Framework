package app.java.commons.dimensions.safety;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.DimensionNames;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.IndicatorNames;
import app.java.commons.dimensions.common.CommonStats;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.stats.Initializer;
import app.java.data.stats.Preparation;

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
            crimeRatio = Preparation.prepareData(initCrimeRatio),
            nonPaymentRatio = Preparation.prepareData(initNonPaymentRatio),
            pensionPps = Preparation.prepareData(initPensionPps),
            socialProtectionPps = Preparation.prepareData(initSocialProtectionPps),
            unexpectedRatio = Preparation.prepareData(initUnexpectedRatio),

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
            totalOffencesRatio = prepareOffencesRatio();

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double
                        pensionPpsRatio = Preparation.consolidatePpsRatio(pensionPps, code, year),
                        socialProtectionPpsRatio = Preparation.consolidatePpsRatio(socialProtectionPps, code, year),
                        reversedCrimeRatio = MathUtils.percentageReverseRatio(crimeRatio, key),
                        reversedNonPaymentRatio = MathUtils.percentageReverseRatio(nonPaymentRatio, key),
                        reversedOffencesRatio = MathUtils.percentageReverseRatio(totalOffencesRatio, key),
                        reversedUnexpectedRatio = MathUtils.percentageReverseRatio(unexpectedRatio, key);

                double product = 1
                        * MathUtils.percentageSafetyDouble(pensionPpsRatio)
                        * MathUtils.percentageSafetyDouble(socialProtectionPpsRatio)
                        * MathUtils.percentageSafetyDouble(reversedCrimeRatio)
                        * MathUtils.percentageSafetyDouble(reversedNonPaymentRatio)
                        * MathUtils.percentageSafetyDouble(reversedOffencesRatio)
                        * MathUtils.percentageSafetyDouble(reversedUnexpectedRatio);

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
        if (args.contains("--dimension=" + DimensionNames.SAFETY)) {
            if (args.contains("--indicator=" + IndicatorNames.CRIME_RATIO))
                Print.printChartData(crimeRatio, EU28_MEMBERS, seriesType, IndicatorNames.CRIME_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.NON_PAYMENT_RATIO))
                Print.printChartData(nonPaymentRatio, EU28_MEMBERS, seriesType, IndicatorNames.NON_PAYMENT_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.PENSION_PPS))
                Print.printChartData(pensionPps, EU28_MEMBERS, seriesType, IndicatorNames.PENSION_PPS, direction);

            if (args.contains("--indicator=" + IndicatorNames.SOCIAL_PROTECTION_PPS))
                Print.printChartData(socialProtectionPps, EU28_MEMBERS, seriesType, IndicatorNames.SOCIAL_PROTECTION_PPS, direction);

            if (args.contains("--indicator=" + IndicatorNames.UNEXPECTED_RATIO))
                Print.printChartData(unexpectedRatio, EU28_MEMBERS, seriesType, IndicatorNames.UNEXPECTED_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.ATTEMPTED_HOMICIDE_OFFENCES))
                Print.printChartData(attemptedHomicideOffences, EU28_MEMBERS, seriesType, IndicatorNames.ATTEMPTED_HOMICIDE_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.ASSAULT_OFFENCES))
                Print.printChartData(assaultOffences, EU28_MEMBERS, seriesType, IndicatorNames.ASSAULT_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.BRIBERY_OFFENCES))
                Print.printChartData(briberyOffences, EU28_MEMBERS, seriesType, IndicatorNames.BRIBERY_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.BURGLARY_OFFENCES))
                Print.printChartData(burglaryOffences, EU28_MEMBERS, seriesType, IndicatorNames.BURGLARY_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.BURGLARY_PRIVATE_OFFENCES))
                Print.printChartData(burglaryPrivateOffences, EU28_MEMBERS, seriesType, IndicatorNames.BURGLARY_PRIVATE_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.COMPUTERS_OFFENCES))
                Print.printChartData(computersOffences, EU28_MEMBERS, seriesType, IndicatorNames.COMPUTERS_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.CORRUPTION_OFFENCES))
                Print.printChartData(corruptionOffences, EU28_MEMBERS, seriesType, IndicatorNames.CORRUPTION_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.CRIMINAL_GROUPS_OFFENCES))
                Print.printChartData(criminalGroupsOffences, EU28_MEMBERS, seriesType, IndicatorNames.CRIMINAL_GROUPS_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.FRAUD_OFFENCES))
                Print.printChartData(fraudOffences, EU28_MEMBERS, seriesType, IndicatorNames.FRAUD_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.HOMICIDE_OFFENCES))
                Print.printChartData(homicideOffences, EU28_MEMBERS, seriesType, IndicatorNames.HOMICIDE_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.KIDNAPPING_OFFENCES))
                Print.printChartData(kidnappingOffences, EU28_MEMBERS, seriesType, IndicatorNames.KIDNAPPING_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.MONEY_LAUNDERING_OFFENCES))
                Print.printChartData(moneyLaunderingOffences, EU28_MEMBERS, seriesType, IndicatorNames.MONEY_LAUNDERING_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.NARCOTICS_OFFENCES))
                Print.printChartData(narcoticsOffences, EU28_MEMBERS, seriesType, IndicatorNames.NARCOTICS_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.RAPE_OFFENCES))
                Print.printChartData(rapeOffences, EU28_MEMBERS, seriesType, IndicatorNames.RAPE_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.ROBBERY_OFFENCES))
                Print.printChartData(robberyOffences, EU28_MEMBERS, seriesType, IndicatorNames.ROBBERY_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.SEXUAL_ASSAULT_OFFENCES))
                Print.printChartData(sexualAssaultOffences, EU28_MEMBERS, seriesType, IndicatorNames.SEXUAL_ASSAULT_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.SEXUAL_EXPLOITATION_OFFENCES))
                Print.printChartData(sexualExploitationOffences, EU28_MEMBERS, seriesType, IndicatorNames.SEXUAL_EXPLOITATION_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.SEXUAL_VIOLENCE_OFFENCES))
                Print.printChartData(sexualViolenceOffences, EU28_MEMBERS, seriesType, IndicatorNames.SEXUAL_VIOLENCE_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.THEFT_OFFENCES))
                Print.printChartData(theftOffences, EU28_MEMBERS, seriesType, IndicatorNames.THEFT_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.THEFT_VEHICLE_OFFENCES))
                Print.printChartData(theftVehicleOffences, EU28_MEMBERS, seriesType, IndicatorNames.THEFT_VEHICLE_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.TOTAL_OFFENCES_RATIO))
                Print.printChartData(totalOffencesRatio, EU28_MEMBERS, seriesType, IndicatorNames.TOTAL_OFFENCES_RATIO, direction);
        }
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
                    Number value = MathUtils.generatePerHundredInhabitants(CommonStats.population, key, sum);
                    consolidatedList.put(key, value);
                }
            }

            String key = MapUtils.generateKey("UK", year);
            Number ukValue = MathUtils.generatePerHundredInhabitants(CommonStats.population, key, ukSum);
            consolidatedList.put(MapUtils.generateKey("UK", year), ukValue);
        }

        return consolidatedList;
    }
}
