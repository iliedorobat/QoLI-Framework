package app.java.data.measurement.dao.impl;

import app.java.commons.MapOrder;
import app.java.commons.MapUtils;
import app.java.commons.MathUtils;
import app.java.commons.constants.Constants;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.dao.SafetyStatsDAO;
import app.java.data.measurement.preparation.Initializer;
import app.java.data.measurement.preparation.Preparation;

import java.util.Map;
import java.util.TreeMap;

public class SafetyStatsImpl implements SafetyStatsDAO {
    private static final String[] EU28_MEMBERS = Constants.EU28_MEMBERS;
    private static final String[] EU28_MEMBERS_EXTENDED = Constants.EU28_MEMBERS_EXTENDED;

    // The lists of queried values
    private static final String[]
            CRIME_RATIO = {"TOTAL", "TOTAL", "PC"},
            PENSION_POWER = {"TOTAL", "TOTAL", "PPS_HAB"},
            SOCIAL_PROTECTION_POWER = {"SPBENEFNOREROUTE", "PPS_HAB"},
            UNEXPECTED_RATIO = {"TOTAL", "TOTAL", "PC"},
            UNPAID_RATIO = {"TOTAL", "TOTAL", "PC"},

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
            offencesPath = FilePathConst.SAFETY_PATH + FileNameConst.OFFENCES + JSON_EXT,
            pensionPowerPath = FilePathConst.SAFETY_PATH + FileNameConst.PENSION_RATIO + JSON_EXT,
            socialProtectionPowerPath = FilePathConst.SAFETY_PATH + FileNameConst.SOCIAL_PROTECTION_RATIO + JSON_EXT,
            unexpectedRatioPath = FilePathConst.SAFETY_PATH + FileNameConst.UNEXPECTED_RATIO + JSON_EXT,
            unpaidRatioPath = FilePathConst.SAFETY_PATH + FileNameConst.UNPAID_RATIO + JSON_EXT;

    private static final Map<String, Number>
            initCrimeRatio = Initializer.initConsolidatedList(CRIME_RATIO, crimeRatioPath),
            initPensionPower = Initializer.initConsolidatedList(PENSION_POWER, pensionPowerPath),
            initSocialProtectionPower = Initializer.initConsolidatedList(SOCIAL_PROTECTION_POWER, socialProtectionPowerPath),
            initUnexpectedRatio = Initializer.initConsolidatedList(UNEXPECTED_RATIO, unexpectedRatioPath),
            initUnpaidRatio = Initializer.initConsolidatedList(UNPAID_RATIO, unpaidRatioPath),

            // Intermediate data which should be consolidated into a single indicator
            initAssaultOffences = Initializer.initConsolidatedList(OFFENCES_ASSAULT, offencesPath, EU28_MEMBERS_EXTENDED),
            initBurglaryOffences = Initializer.initConsolidatedList(OFFENCES_BURGLARY, offencesPath, EU28_MEMBERS_EXTENDED),
            initRobberyOffences = Initializer.initConsolidatedList(OFFENCES_ROBBERY, offencesPath, EU28_MEMBERS_EXTENDED),
            initSexualOffences = Initializer.initConsolidatedList(OFFENCES_SEXUAL, offencesPath, EU28_MEMBERS_EXTENDED),
            initTheftOffences = Initializer.initConsolidatedList(OFFENCES_THEFT, offencesPath, EU28_MEMBERS_EXTENDED),
            initUnlawfulOffences = Initializer.initConsolidatedList(OFFENCES_UNLAWFUL, offencesPath, EU28_MEMBERS_EXTENDED);

    public Map<String, Number> calculateDimension() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                crimeRatio = Preparation.prepareData(initCrimeRatio),
                pensionPower = Preparation.prepareData(initPensionPower),
                socialProtectionPower = Preparation.prepareData(initSocialProtectionPower),
                unexpectedRatio = Preparation.prepareData(initUnexpectedRatio),
                unpaidRatio = Preparation.prepareData(initUnpaidRatio),
                offencesRatio = consolidateOffencesRatio();

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (int i = 0; i < EU28_MEMBERS.length; i++) {
                String code = EU28_MEMBERS[i];
                String key = MapUtils.generateKey(code, year);
                double product = 1
                        / MapUtils.getSafetyDouble(crimeRatio, key)
                        / MapUtils.getSafetyDouble(offencesRatio, key)
                        * MapUtils.getSafetyDouble(pensionPower, key) / 1000
                        * MapUtils.getSafetyDouble(socialProtectionPower, key) / 1000
                        / MapUtils.getSafetyDouble(unexpectedRatio, key)
                        / MapUtils.getSafetyDouble(unpaidRatio, key);
                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

        System.out.println(Math.log(30) + " " + Math.log(70) + "    " + Math.log(100));

//        Print.printVariation(Statistics.generateVariation(askingRatio, true));
//        Print.print(initUnpaidRatio, false);

        return consolidatedList;
    }

    private Map<String, Number> consolidateOffencesRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                assaultOffences = Preparation.prepareData(initAssaultOffences, EU28_MEMBERS_EXTENDED),
                // burglaryOffences - no data for Italy and Estonia
                burglaryOffences = Preparation.prepareData(initBurglaryOffences, EU28_MEMBERS_EXTENDED),
                robberyOffences = Preparation.prepareData(initRobberyOffences, EU28_MEMBERS_EXTENDED),
                sexualOffences = Preparation.prepareData(initSexualOffences, EU28_MEMBERS_EXTENDED),
                theftOffences = Preparation.prepareData(initTheftOffences, EU28_MEMBERS_EXTENDED),
                unlawfulOffences = Preparation.prepareData(initUnlawfulOffences, EU28_MEMBERS_EXTENDED);


        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            double ukSum = 0;

            for (int i = 0; i < EU28_MEMBERS_EXTENDED.length; i++) {
                String code = EU28_MEMBERS_EXTENDED[i];
                String key = MapUtils.generateKey(code, year);

                double product = assaultOffences.get(key).doubleValue()
//                        * burglaryOffences.get(key).doubleValue()
                        * robberyOffences.get(key).doubleValue()
                        * sexualOffences.get(key).doubleValue()
                        * theftOffences.get(key).doubleValue()
                        * unlawfulOffences.get(key).doubleValue();
                Number value = MathUtils.getSquareValue(product, 6);

                if (code.equals("UKC-L") || code.equals("UKM") || code.equals("UKN")) {
                    ukSum += value.doubleValue();
                } else {
                    consolidatedList.put(key, value);
                }
            }

            Number ukValue = ukSum / 3;
            consolidatedList.put(MapUtils.generateKey("UK", year), ukValue);
        }

        return consolidatedList;
    }
}
