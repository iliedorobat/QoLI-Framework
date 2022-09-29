package app.java.commons.dimensions.safety;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.*;
import app.java.commons.dimensions.common.CommonStats;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.stats.Initializer;
import app.java.data.stats.Preparation;
import org.apache.commons.collections4.MultiValuedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.*;

public class SafetyStats {
    // Queried params values
    private static final MultiValuedMap<String, String>
            CRIME_RATIO = SafetyParams.getCrimeParams(),
            NON_PAYMENT_RATIO = SafetyParams.getNonPaymentParams(),
            PENSION_PPS = SafetyParams.getPensionPpsParams(),
            SOCIAL_PROTECTION_PPS = SafetyParams.getSocialProtectionPpsParams(),
            UNEXPECTED_RATIO = SafetyParams.getUnexpectedParams(),

            //UK = UKC-L + UKM + UKN (England and Wales + Scotland + Northern Ireland)
            OFFENCES_ASSAULT = SafetyParams.getOffencesParams(ParamsValues.ICCS.get("assault")),
            OFFENCES_ATTEMPTED_HOMICIDE = SafetyParams.getOffencesParams(ParamsValues.ICCS.get("attemptedHomicide")),
            OFFENCES_BURGLARY = SafetyParams.getOffencesParams(ParamsValues.ICCS.get("burglary")),
            OFFENCES_BURGLARY_PRIVATE = SafetyParams.getOffencesParams(ParamsValues.ICCS.get("burglaryPrivate")),
            OFFENCES_HOMICIDE = SafetyParams.getOffencesParams(ParamsValues.ICCS.get("homicide")),
            OFFENCES_KIDNAPPING = SafetyParams.getOffencesParams(ParamsValues.ICCS.get("kidnapping")),
            OFFENCES_RAPE = SafetyParams.getOffencesParams(ParamsValues.ICCS.get("rape")),
            OFFENCES_ROBBERY = SafetyParams.getOffencesParams(ParamsValues.ICCS.get("robbery")),
            OFFENCES_SEXUAL_ASSAULT = SafetyParams.getOffencesParams(ParamsValues.ICCS.get("sexualAssault")),
            OFFENCES_SEXUAL_VIOLENCE = SafetyParams.getOffencesParams(ParamsValues.ICCS.get("sexualViolence")),
            OFFENCES_THEFT = SafetyParams.getOffencesParams(ParamsValues.ICCS.get("theft")),
            OFFENCES_THEFT_VEHICLE = SafetyParams.getOffencesParams(ParamsValues.ICCS.get("theftVehicle")),
            OFFENCES_UNLAWFUL = SafetyParams.getOffencesParams(ParamsValues.ICCS.get("narcotics"));

    private static final String
            crimeRatioPath = FilePathConst.SAFETY_PATH + FileNameConst.CRIME_RATIO + JSON_EXTENSION,
            nonPaymentRatioPath = FilePathConst.SAFETY_PATH + FileNameConst.NON_PAYMENT_RATIO + JSON_EXTENSION,
            offencesPath = FilePathConst.SAFETY_PATH + FileNameConst.OFFENCES + JSON_EXTENSION,
            pensionPpsPath = FilePathConst.SAFETY_PATH + FileNameConst.PENSION_PPS + JSON_EXTENSION,
            socialProtectionPpsPath = FilePathConst.SAFETY_PATH + FileNameConst.SOCIAL_PROTECTION_RATIO + JSON_EXTENSION,
            unexpectedRatioPath = FilePathConst.SAFETY_PATH + FileNameConst.UNEXPECTED_RATIO + JSON_EXTENSION;

    private static final Map<String, Number>
            initCrimeRatio = Initializer.initConsolidatedMap(CRIME_RATIO, crimeRatioPath),
            initNonPaymentRatio = Initializer.initConsolidatedMap(NON_PAYMENT_RATIO, nonPaymentRatioPath),
            initPensionPps = Initializer.initConsolidatedMap(PENSION_PPS, pensionPpsPath),
            initSocialProtectionPps = Initializer.initConsolidatedMap(SOCIAL_PROTECTION_PPS, socialProtectionPpsPath),
            initUnexpectedRatio = Initializer.initConsolidatedMap(UNEXPECTED_RATIO, unexpectedRatioPath),

            // Intermediate data which should be consolidated into a single indicator
            initAssaultOffences = Initializer.initConsolidatedMap(OFFENCES_ASSAULT, offencesPath, EU28_MEMBERS_EXTENDED),
            initAttemptedHomicideOffences = Initializer.initConsolidatedMap(OFFENCES_ATTEMPTED_HOMICIDE, offencesPath, EU28_MEMBERS_EXTENDED),
            initBurglaryOffences = Initializer.initConsolidatedMap(OFFENCES_BURGLARY, offencesPath, EU28_MEMBERS_EXTENDED),
            initBurglaryPrivateOffences = Initializer.initConsolidatedMap(OFFENCES_BURGLARY_PRIVATE, offencesPath, EU28_MEMBERS_EXTENDED),
            initHomicideOffences = Initializer.initConsolidatedMap(OFFENCES_HOMICIDE, offencesPath, EU28_MEMBERS_EXTENDED),
            initKidnappingOffences = Initializer.initConsolidatedMap(OFFENCES_KIDNAPPING, offencesPath, EU28_MEMBERS_EXTENDED),
            initRapeOffences = Initializer.initConsolidatedMap(OFFENCES_RAPE, offencesPath, EU28_MEMBERS_EXTENDED),
            initRobberyOffences = Initializer.initConsolidatedMap(OFFENCES_ROBBERY, offencesPath, EU28_MEMBERS_EXTENDED),
            initSexualViolenceOffences = Initializer.initConsolidatedMap(OFFENCES_SEXUAL_VIOLENCE, offencesPath, EU28_MEMBERS_EXTENDED),
            initSexualAssaultOffences = Initializer.initConsolidatedMap(OFFENCES_SEXUAL_ASSAULT, offencesPath, EU28_MEMBERS_EXTENDED),
            initTheftOffences = Initializer.initConsolidatedMap(OFFENCES_THEFT, offencesPath, EU28_MEMBERS_EXTENDED),
            initTheftVehicleOffences = Initializer.initConsolidatedMap(OFFENCES_THEFT_VEHICLE, offencesPath, EU28_MEMBERS_EXTENDED),
            initUnlawfulOffences = Initializer.initConsolidatedMap(OFFENCES_UNLAWFUL, offencesPath, EU28_MEMBERS_EXTENDED);

    public static final Map<String, Number>
            crimeRatio = Preparation.prepareData(initCrimeRatio),
            nonPaymentRatio = Preparation.prepareData(initNonPaymentRatio),
            pensionPps = Preparation.prepareData(initPensionPps),
            socialProtectionPps = Preparation.prepareData(initSocialProtectionPps),
            unexpectedRatio = Preparation.prepareData(initUnexpectedRatio),

            attemptedHomicideOffences = Preparation.prepareData(initAttemptedHomicideOffences, EU28_MEMBERS_EXTENDED),
            assaultOffences = Preparation.prepareData(initAssaultOffences, EU28_MEMBERS_EXTENDED),
            burglaryOffences = Preparation.prepareData(initBurglaryOffences, EU28_MEMBERS_EXTENDED),
            burglaryPrivateOffences = Preparation.prepareData(initBurglaryPrivateOffences, EU28_MEMBERS_EXTENDED),
            homicideOffences = Preparation.prepareData(initHomicideOffences, EU28_MEMBERS_EXTENDED),
            kidnappingOffences = Preparation.prepareData(initKidnappingOffences, EU28_MEMBERS_EXTENDED),
            rapeOffences = Preparation.prepareData(initRapeOffences, EU28_MEMBERS_EXTENDED),
            robberyOffences = Preparation.prepareData(initRobberyOffences, EU28_MEMBERS_EXTENDED),
            sexualAssaultOffences = Preparation.prepareData(initSexualAssaultOffences, EU28_MEMBERS_EXTENDED),
            sexualViolenceOffences = Preparation.prepareData(initSexualViolenceOffences, EU28_MEMBERS_EXTENDED),
            theftOffences = Preparation.prepareData(initTheftOffences, EU28_MEMBERS_EXTENDED),
            theftVehicleOffences = Preparation.prepareData(initTheftVehicleOffences, EU28_MEMBERS_EXTENDED),
            unlawfulOffences = Preparation.prepareData(initUnlawfulOffences, EU28_MEMBERS_EXTENDED),
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

    public static ArrayList<Map<String, Number>> getInitList() {
        return new ArrayList<>() {{
            add(Preparation.filterMap(initCrimeRatio));
            add(Preparation.filterMap(initPensionPps));
            add(Preparation.filterMap(initSocialProtectionPps));
            add(Preparation.filterMap(initUnexpectedRatio));
            add(Preparation.filterMap(initNonPaymentRatio));

            add(Preparation.filterMap(initAssaultOffences));
            add(Preparation.filterMap(initAttemptedHomicideOffences));
            add(Preparation.filterMap(initBurglaryOffences));
            add(Preparation.filterMap(initBurglaryPrivateOffences));
            add(Preparation.filterMap(initHomicideOffences));
            add(Preparation.filterMap(initKidnappingOffences));
            add(Preparation.filterMap(initRapeOffences));
            add(Preparation.filterMap(initRobberyOffences));
            add(Preparation.filterMap(initSexualAssaultOffences));
            add(Preparation.filterMap(initSexualViolenceOffences));
            add(Preparation.filterMap(initTheftOffences));
            add(Preparation.filterMap(initTheftVehicleOffences));
            add(Preparation.filterMap(initUnlawfulOffences));
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

            if (args.contains("--indicator=" + IndicatorNames.BURGLARY_OFFENCES))
                Print.printChartData(burglaryOffences, EU28_MEMBERS, seriesType, IndicatorNames.BURGLARY_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.BURGLARY_PRIVATE_OFFENCES))
                Print.printChartData(burglaryPrivateOffences, EU28_MEMBERS, seriesType, IndicatorNames.BURGLARY_PRIVATE_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.HOMICIDE_OFFENCES))
                Print.printChartData(homicideOffences, EU28_MEMBERS, seriesType, IndicatorNames.HOMICIDE_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.KIDNAPPING_OFFENCES))
                Print.printChartData(kidnappingOffences, EU28_MEMBERS, seriesType, IndicatorNames.KIDNAPPING_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.RAPE_OFFENCES))
                Print.printChartData(rapeOffences, EU28_MEMBERS, seriesType, IndicatorNames.RAPE_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.ROBBERY_OFFENCES))
                Print.printChartData(robberyOffences, EU28_MEMBERS, seriesType, IndicatorNames.ROBBERY_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.SEXUAL_ASSAULT_OFFENCES))
                Print.printChartData(sexualAssaultOffences, EU28_MEMBERS, seriesType, IndicatorNames.SEXUAL_ASSAULT_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.SEXUAL_VIOLENCE_OFFENCES))
                Print.printChartData(sexualViolenceOffences, EU28_MEMBERS, seriesType, IndicatorNames.SEXUAL_VIOLENCE_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.THEFT_OFFENCES))
                Print.printChartData(theftOffences, EU28_MEMBERS, seriesType, IndicatorNames.THEFT_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.THEFT_VEHICLE_OFFENCES))
                Print.printChartData(theftVehicleOffences, EU28_MEMBERS, seriesType, IndicatorNames.THEFT_VEHICLE_OFFENCES, direction);

            if (args.contains("--indicator=" + IndicatorNames.UNLAWFUL_OFFENCES))
                Print.printChartData(unlawfulOffences, EU28_MEMBERS, seriesType, IndicatorNames.UNLAWFUL_OFFENCES, direction);

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
                        + burglaryOffences.get(key).doubleValue()
                        + burglaryPrivateOffences.get(key).doubleValue()
                        + homicideOffences.get(key).doubleValue()
                        + kidnappingOffences.get(key).doubleValue()
                        + rapeOffences.get(key).doubleValue()
                        + robberyOffences.get(key).doubleValue()
                        + sexualAssaultOffences.get(key).doubleValue()
                        + sexualViolenceOffences.get(key).doubleValue()
                        + theftOffences.get(key).doubleValue()
                        + theftVehicleOffences.get(key).doubleValue()
                        + unlawfulOffences.get(key).doubleValue();

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
