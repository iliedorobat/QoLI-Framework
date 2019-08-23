package app.java.data.measurement.dao.impl;

import app.java.commons.MapOrder;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.commons.constants.Constants;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.dao.SafetyStatsDAO;
import app.java.data.measurement.preparation.Initializer;
import app.java.data.measurement.preparation.Preparation;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class SafetyStatsImpl implements SafetyStatsDAO {
    private static final String[] EU28_MEMBERS = Constants.EU28_MEMBERS;
    private static final String[] EU28_MEMBERS_EXTENDED = Constants.EU28_MEMBERS_EXTENDED;

    // The lists of queried values
    private static final String[]
            CRIME_RATIO = {"TOTAL", "TOTAL", "PC"},
            NON_PAYMENT_RATIO = {"TOTAL", "TOTAL", "PC"},
            PENSION_PPS = {"TOTAL", "TOTAL", "PPS_HAB"},
            SOCIAL_PROTECTION_PPS = {"SPBENEFNOREROUTE", "PPS_HAB"},
            UNEXPECTED_RATIO = {"TOTAL", "TOTAL", "PC"},

            //UK = UKC-L + UKM + UKN (England and Wales + Scotland + Northern Ireland)
            OFFENCES_ASSAULT = {"ICCS02011", "NR"},
            OFFENCES_SEXUAL = {"ICCS0301", "NR"},
            OFFENCES_ROBBERY = {"ICCS0401", "NR"},
            OFFENCES_BURGLARY = {"ICCS0501", "NR"},
            OFFENCES_THEFT = {"ICCS0502", "NR"},
            OFFENCES_UNLAWFUL = {"ICCS0601", "NR"};

    private static final String JSON_EXT = Constants.JSON_EXTENSION;
    private static final String
            crimeRatioPath = FilePathConst.SAFETY_PATH + FileNameConst.CRIME_RATIO + JSON_EXT,
            nonPaymentRatioPath = FilePathConst.SAFETY_PATH + FileNameConst.NON_PAYMENT_RATIO + JSON_EXT,
            offencesPath = FilePathConst.SAFETY_PATH + FileNameConst.OFFENCES + JSON_EXT,
            pensionPpsPath = FilePathConst.SAFETY_PATH + FileNameConst.PENSION_PPS + JSON_EXT,
            socialProtectionPpsPath = FilePathConst.SAFETY_PATH + FileNameConst.SOCIAL_PROTECTION_RATIO + JSON_EXT,
            unexpectedRatioPath = FilePathConst.SAFETY_PATH + FileNameConst.UNEXPECTED_RATIO + JSON_EXT;

    private static final Map<String, Number>
            initCrimeRatio = Initializer.initConsolidatedMap(CRIME_RATIO, crimeRatioPath),
            initNonPaymentRatio = Initializer.initConsolidatedMap(NON_PAYMENT_RATIO, nonPaymentRatioPath),
            initPensionPps = Initializer.initConsolidatedMap(PENSION_PPS, pensionPpsPath),
            initSocialProtectionPps = Initializer.initConsolidatedMap(SOCIAL_PROTECTION_PPS, socialProtectionPpsPath),
            initUnexpectedRatio = Initializer.initConsolidatedMap(UNEXPECTED_RATIO, unexpectedRatioPath),

            // Intermediate data which should be consolidated into a single indicator
            initAssaultOffences = Initializer.initConsolidatedMap(OFFENCES_ASSAULT, offencesPath, EU28_MEMBERS_EXTENDED),
            initBurglaryOffences = Initializer.initConsolidatedMap(OFFENCES_BURGLARY, offencesPath, EU28_MEMBERS_EXTENDED),
            initRobberyOffences = Initializer.initConsolidatedMap(OFFENCES_ROBBERY, offencesPath, EU28_MEMBERS_EXTENDED),
            initSexualOffences = Initializer.initConsolidatedMap(OFFENCES_SEXUAL, offencesPath, EU28_MEMBERS_EXTENDED),
            initTheftOffences = Initializer.initConsolidatedMap(OFFENCES_THEFT, offencesPath, EU28_MEMBERS_EXTENDED),
            initUnlawfulOffences = Initializer.initConsolidatedMap(OFFENCES_UNLAWFUL, offencesPath, EU28_MEMBERS_EXTENDED);

    public Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                crimeRatio = Preparation.prepareData(initCrimeRatio),
                nonPaymentRatio = Preparation.prepareData(initNonPaymentRatio),
                pensionPps = Preparation.prepareData(initPensionPps),
                socialProtectionPps = Preparation.prepareData(initSocialProtectionPps),
                unexpectedRatio = Preparation.prepareData(initUnexpectedRatio),
                offencesRatio = consolidateOffencesRatio();

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (int i = 0; i < EU28_MEMBERS.length; i++) {
                String code = EU28_MEMBERS[i];
                String key = MapUtils.generateKey(code, year);

                double reversedCrimeRatio = MathUtils.percentageReverseRatio(crimeRatio, key),
                        reversedOffencesRatio = MathUtils.percentageReverseRatio(offencesRatio, key),
                        // reduce the number of the whole part of a decimal number from 3 to 2
                        correctedPensionPps = pensionPps.get(key).doubleValue() / 100,
                        // reduce the number of the whole part of a decimal number from 4 to 2
                        correctedSocialProtectionPps = socialProtectionPps.get(key).doubleValue() / 100,
                        reversedNonPaymentRatio = MathUtils.percentageReverseRatio(nonPaymentRatio, key),
                        reversedUnexpectedRatio = MathUtils.percentageReverseRatio(unexpectedRatio, key);

                double product = 1
                        * MathUtils.percentageSafetyDouble(reversedCrimeRatio)
                        * MathUtils.percentageSafetyDouble(correctedPensionPps)
                        * MathUtils.percentageSafetyDouble(correctedSocialProtectionPps)
                        * MathUtils.percentageSafetyDouble(reversedNonPaymentRatio)
                        * MathUtils.percentageSafetyDouble(reversedUnexpectedRatio)
                        * MathUtils.percentageSafetyDouble(reversedOffencesRatio);
                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(Statistics.generateVariation(offencesRatio, true));
//        Print.print(offencesRatio, true);

        return consolidatedList;
    }

    public ArrayList<Map<String, Number>> getInitList() {
        //TODO: initBurglaryOffences is not used
        return new ArrayList<>() {{
            add(Preparation.filterMap(initCrimeRatio));
            add(Preparation.filterMap(initPensionPps));
            add(Preparation.filterMap(initSocialProtectionPps));
            add(Preparation.filterMap(initUnexpectedRatio));
            add(Preparation.filterMap(initNonPaymentRatio));
            add(Preparation.filterMap(initAssaultOffences));
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
    private Map<String, Number> consolidateOffencesRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                assaultOffences = Preparation.prepareData(initAssaultOffences, EU28_MEMBERS_EXTENDED),
                burglaryOffences = Preparation.prepareData(initBurglaryOffences, EU28_MEMBERS_EXTENDED), // no data
                robberyOffences = Preparation.prepareData(initRobberyOffences, EU28_MEMBERS_EXTENDED),
                sexualOffences = Preparation.prepareData(initSexualOffences, EU28_MEMBERS_EXTENDED),
                theftOffences = Preparation.prepareData(initTheftOffences, EU28_MEMBERS_EXTENDED),
                unlawfulOffences = Preparation.prepareData(initUnlawfulOffences, EU28_MEMBERS_EXTENDED);


        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            double ukSum = 0;

            for (int i = 0; i < EU28_MEMBERS_EXTENDED.length; i++) {
                String code = EU28_MEMBERS_EXTENDED[i];
                String key = MapUtils.generateKey(code, year);

                double sum = assaultOffences.get(key).doubleValue()
//                        + burglaryOffences.get(key).doubleValue()
                        + robberyOffences.get(key).doubleValue()
                        + sexualOffences.get(key).doubleValue()
                        + theftOffences.get(key).doubleValue()
                        + unlawfulOffences.get(key).doubleValue();

                if (code.equals("UKC-L") || code.equals("UKM") || code.equals("UKN")) {
                    ukSum += sum;
                } else {
                    Number value = MathUtils.generateThousandPerInhabitant(key, sum);
                    consolidatedList.put(key, value);
                }
            }

            String key = MapUtils.generateKey("UK", year);
            Number ukValue = MathUtils.generateThousandPerInhabitant(key, ukSum);
            consolidatedList.put(MapUtils.generateKey("UK", year), ukValue);
        }

        return consolidatedList;
    }
}
