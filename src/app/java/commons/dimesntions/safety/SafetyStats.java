package app.java.commons.dimesntions.safety;

import app.java.commons.MapOrder;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.constants.ParamsValues;
import app.java.commons.dimesntions.common.CommonStats;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.stats.Initializer;
import app.java.data.stats.Preparation;
import org.apache.commons.collections4.MultiValuedMap;

import java.util.ArrayList;
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

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                crimeRatio = Preparation.prepareData(initCrimeRatio),
                nonPaymentRatio = Preparation.prepareData(initNonPaymentRatio),
                pensionPps = Preparation.prepareData(initPensionPps),
                socialProtectionPps = Preparation.prepareData(initSocialProtectionPps),
                unexpectedRatio = Preparation.prepareData(initUnexpectedRatio),
                offencesRatio = consolidateOffencesRatio();

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double
                        pensionPpsRatio = consolidatePpsRatio(pensionPps, code, year),
                        socialProtectionPpsRatio = consolidatePpsRatio(socialProtectionPps, code, year),
                        reversedCrimeRatio = MathUtils.percentageReverseRatio(crimeRatio, key),
                        reversedNonPaymentRatio = MathUtils.percentageReverseRatio(nonPaymentRatio, key),
                        reversedOffencesRatio = MathUtils.percentageReverseRatio(offencesRatio, key),
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

    private static Double consolidatePpsRatio(Map<String, Number> dataset, String code, int year) {
        Double euAverage = Preparation.calculateEuAverage(dataset, year);
        String key = MapUtils.generateKey(code, year);
        Number value = dataset.get(key);

        if (euAverage == null) {
            throw new Error("The \"euAverage\" variable is null.");
        }

        return value.doubleValue() / euAverage * 100;
    }

    /**
     * Aggregate all the offences types into the total offences index
     *
     * @return A new sorted map which contains the consolidated indicator
     */
    private static Map<String, Number> consolidateOffencesRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
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
                unlawfulOffences = Preparation.prepareData(initUnlawfulOffences, EU28_MEMBERS_EXTENDED);


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
