package app.java.commons.dimesntions.safety;

import app.java.commons.MapOrder;
import app.java.commons.constants.ParamsValues;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.stats.Initializer;
import app.java.data.stats.Preparation;
import org.apache.commons.collections4.MultiValuedMap;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.constants.Constants.EU28_MEMBERS_EXTENDED;
import static app.java.commons.constants.Constants.JSON_EXTENSION;

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
            OFFENCES_BURGLARY = SafetyParams.getOffencesParams(ParamsValues.ICCS.get("burglary")),
            OFFENCES_KIDNAPPING = SafetyParams.getOffencesParams(ParamsValues.ICCS.get("kidnapping")),
            OFFENCES_ROBBERY = SafetyParams.getOffencesParams(ParamsValues.ICCS.get("robbery")),
            OFFENCES_SEXUAL = SafetyParams.getOffencesParams(ParamsValues.ICCS.get("sexualViolence")),
            OFFENCES_THEFT = SafetyParams.getOffencesParams(ParamsValues.ICCS.get("theft")),
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
            initBurglaryOffences = Initializer.initConsolidatedMap(OFFENCES_BURGLARY, offencesPath, EU28_MEMBERS_EXTENDED),
            initKidnappingOffences = Initializer.initConsolidatedMap(OFFENCES_KIDNAPPING, offencesPath, EU28_MEMBERS_EXTENDED),
            initRobberyOffences = Initializer.initConsolidatedMap(OFFENCES_ROBBERY, offencesPath, EU28_MEMBERS_EXTENDED),
            initSexualOffences = Initializer.initConsolidatedMap(OFFENCES_SEXUAL, offencesPath, EU28_MEMBERS_EXTENDED),
            initTheftOffences = Initializer.initConsolidatedMap(OFFENCES_THEFT, offencesPath, EU28_MEMBERS_EXTENDED),
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
                        reversedCrimeRatio = MathUtils.percentageReverseRatio(crimeRatio, key),
                        reversedOffencesRatio = MathUtils.percentageReverseRatio(offencesRatio, key),
                        // reduce the number of the whole part of a decimal number from 4 to 2
                        correctedPensionPps = pensionPps.get(key).doubleValue() / 100, // FIXME: check the dataset
                        // reduce the number of the whole part of a decimal number from 4 to 2
                        correctedSocialProtectionPps = socialProtectionPps.get(key).doubleValue() / 100, // FIXME: check the dataset
                        reversedNonPaymentRatio = MathUtils.percentageReverseRatio(nonPaymentRatio, key),
                        reversedUnexpectedRatio = MathUtils.percentageReverseRatio(unexpectedRatio, key);

                double product = 1
                        * MathUtils.percentageSafetyDouble(correctedPensionPps)
                        * MathUtils.percentageSafetyDouble(correctedSocialProtectionPps)
                        * MathUtils.percentageSafetyDouble(reversedCrimeRatio)
                        * MathUtils.percentageSafetyDouble(reversedNonPaymentRatio)
                        * MathUtils.percentageSafetyDouble(reversedUnexpectedRatio)
                        * MathUtils.percentageSafetyDouble(reversedOffencesRatio);
                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(StatsUtils.generateVariation(offencesRatio, true));
//        Print.print(offencesRatio, true);

        return consolidatedList;
    }

    public static ArrayList<Map<String, Number>> getInitList() {
        //TODO: initBurglaryOffences is not used
        return new ArrayList<>() {{
            add(Preparation.filterMap(initCrimeRatio));
            add(Preparation.filterMap(initPensionPps));
            add(Preparation.filterMap(initSocialProtectionPps));
            add(Preparation.filterMap(initUnexpectedRatio));
            add(Preparation.filterMap(initNonPaymentRatio));
            add(Preparation.filterMap(initAssaultOffences));
            add(Preparation.filterMap(initKidnappingOffences));
            add(Preparation.filterMap(initRobberyOffences));
            add(Preparation.filterMap(initSexualOffences));
            add(Preparation.filterMap(initTheftOffences));
            add(Preparation.filterMap(initUnlawfulOffences));
        }};
    }

    /**
     * Aggregate all the offences types into the total offences index
     *
     * @return A new sorted map which contains the consolidated indicator
     */
    private static Map<String, Number> consolidateOffencesRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                assaultOffences = Preparation.prepareData(initAssaultOffences, EU28_MEMBERS_EXTENDED),
                burglaryOffences = Preparation.prepareData(initBurglaryOffences, EU28_MEMBERS_EXTENDED), // FIXME: no data
                kidnappingOffences = Preparation.prepareData(initKidnappingOffences, EU28_MEMBERS_EXTENDED), // FIXME: check the data
                robberyOffences = Preparation.prepareData(initRobberyOffences, EU28_MEMBERS_EXTENDED),
                sexualOffences = Preparation.prepareData(initSexualOffences, EU28_MEMBERS_EXTENDED),
                theftOffences = Preparation.prepareData(initTheftOffences, EU28_MEMBERS_EXTENDED),
                unlawfulOffences = Preparation.prepareData(initUnlawfulOffences, EU28_MEMBERS_EXTENDED);


        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            double ukSum = 0;

            for (String code : EU28_MEMBERS_EXTENDED) {
                String key = MapUtils.generateKey(code, year);

                double sum = assaultOffences.get(key).doubleValue()
//                        + burglaryOffences.get(key).doubleValue()
//                        + kidnappingOffences.get(key).doubleValue()
                        + robberyOffences.get(key).doubleValue()
                        + sexualOffences.get(key).doubleValue()
                        + theftOffences.get(key).doubleValue()
                        + unlawfulOffences.get(key).doubleValue();

                if (code.equals("UKC-L") || code.equals("UKM") || code.equals("UKN")) {
                    ukSum += sum;
                } else {
                    Number value = MathUtils.generatePerThousandInhabitants(key, sum);
                    consolidatedList.put(key, value);
                }
            }

            String key = MapUtils.generateKey("UK", year);
            Number ukValue = MathUtils.generatePerThousandInhabitants(key, ukSum);
            consolidatedList.put(MapUtils.generateKey("UK", year), ukValue);
        }

        return consolidatedList;
    }
}
