package app.java.commons.dimensions.gov;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.constants.ParamsValues;
import app.java.commons.constants.DimensionNames;
import app.java.commons.constants.IndicatorNames;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.stats.Initializer;
import app.java.data.stats.Preparation;
import org.apache.commons.collections4.MultiValuedMap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.*;

public class GovRightsStats {
    // Queried params values
    private static final MultiValuedMap<String, String>
            ACTIVE_CITIZENSHIP_RATIO = GovRightsParams.getCitizenshipParams(),
            EMPLOYMENT_FEMALE_RATIO = GovRightsParams.getEmploymentParams(ParamsValues.SEX.get("female")),
            EMPLOYMENT_MALE_RATIO = GovRightsParams.getEmploymentParams(ParamsValues.SEX.get("male")),
            GENDER_PAY_GAP = GovRightsParams.getGenderPayGapParams(),
            POPULATION_LEGTST_TRUST = GovRightsParams.getPopulationTrustParams(ParamsValues.INDIC_WB.get("legal")),
            POPULATION_PLCTST_TRUST = GovRightsParams.getPopulationTrustParams(ParamsValues.INDIC_WB.get("police")),
            POPULATION_PLTTST_TRUST = GovRightsParams.getPopulationTrustParams(ParamsValues.INDIC_WB.get("politic")),
            POPULATION_OTHERS_TRUST = GovRightsParams.getPopulationTrustParams(ParamsValues.INDIC_WB.get("others"));

    private static final String
            citizenshipRatioPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.CITIZENSHIP_RATIO + JSON_EXTENSION,
            employmentRatioPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.EMPLOYMENT_RATIO_BY_SEX + JSON_EXTENSION,
            genderPayGapPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.GENDER_PAY_GAP + JSON_EXTENSION,
            populationTrustPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.POPULATION_TRUST + JSON_EXTENSION,
            voterTurnoutPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.VOTER_TURNOUT + CSV_EXTENSION;

    // Intermediate data which should be consolidated into a single indicator
    private static final Map<String, Number>
            initEmploymentFemaleRatio = Initializer.initConsolidatedMap(EMPLOYMENT_FEMALE_RATIO, employmentRatioPath),
            initEmploymentMaleRatio = Initializer.initConsolidatedMap(EMPLOYMENT_MALE_RATIO, employmentRatioPath),
            initPopulationLegtstTrust = Initializer.initConsolidatedMap(POPULATION_LEGTST_TRUST, populationTrustPath),
            initPopulationOthersTrust = Initializer.initConsolidatedMap(POPULATION_OTHERS_TRUST, populationTrustPath),
            initPopulationPlctstTrust = Initializer.initConsolidatedMap(POPULATION_PLCTST_TRUST, populationTrustPath),
            initPopulationPlttstTrust = Initializer.initConsolidatedMap(POPULATION_PLTTST_TRUST, populationTrustPath);
    private static final ArrayList<Map<String, Number>> voterTurnoutList = new ArrayList<>() {{
        add(voterTurnoutCsvToMap(voterTurnoutPath));
    }};

    private static final Map<String, Number>
            initCitizenshipRatio = Initializer.initConsolidatedMap(ACTIVE_CITIZENSHIP_RATIO, citizenshipRatioPath),
            initGenderPayGap = Initializer.initConsolidatedMap(GENDER_PAY_GAP, genderPayGapPath),
            initVoterTurnout = Initializer.initConsolidatedMaps(voterTurnoutList);

    public static final Map<String, Number>
            citizenship = Preparation.prepareData(initCitizenshipRatio),

            employmentFemaleRatio = Preparation.prepareData(initEmploymentFemaleRatio),
            employmentMaleRatio = Preparation.prepareData(initEmploymentMaleRatio),
            compactEmploymentGenderGap = consolidateEmploymentGenderGap(),

            genderPayGap = Preparation.prepareData(initGenderPayGap),

            populationLegtstTrustRatio = Preparation.prepareData(initPopulationLegtstTrust),
            populationOthersTrustRatio = Preparation.prepareData(initPopulationOthersTrust),
            populationPlctstTrustRatio = Preparation.prepareData(initPopulationPlctstTrust),
            populationPlttstTrustRatio = Preparation.prepareData(initPopulationPlttstTrust),
            compactPopulationTrust = consolidatePopulationTrust(),

            voterTurnout = Preparation.prepareData(initVoterTurnout);

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double
                        employmentGap = - compactEmploymentGenderGap.get(key).doubleValue(),
                        genderGap = - genderPayGap.get(key).doubleValue(),
                        // Transform the 1-10 notes into 1-100 notes
                        trust = compactPopulationTrust.get(key).doubleValue() * 10;

                double product = 1
                        * MathUtils.percentageSafetyDouble(citizenship, key)
                        * MathUtils.percentageSafetyDouble(employmentGap)
                        * MathUtils.percentageSafetyDouble(genderGap)
                        * MathUtils.percentageSafetyDouble(trust)
                        * MathUtils.percentageSafetyDouble(voterTurnout, key);

                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }


//        Print.printVariation(StatsUtils.generateVariation(populationTrust, true));
//        Print.print(populationTrust, true);

        return consolidatedList;
    }

    public static ArrayList<Map<String, Number>> getInitList() {
        return new ArrayList<>() {{
            add(Preparation.filterMap(initCitizenshipRatio));
            add(Preparation.filterMap(initEmploymentFemaleRatio));
            add(Preparation.filterMap(initEmploymentMaleRatio));
            add(Preparation.filterMap(initGenderPayGap));
            add(Preparation.filterMap(initPopulationLegtstTrust));
            add(Preparation.filterMap(initPopulationOthersTrust));
            add(Preparation.filterMap(initPopulationPlctstTrust));
            add(Preparation.filterMap(initPopulationPlttstTrust));
            add(Preparation.filterMap(initVoterTurnout));
        }};
    }

    public static void printIndicators(List<String> args, String seriesType) {
        if (args.contains("--dimension=" + DimensionNames.GOVERNMENT)) {
            if (args.contains("--indicator=" + IndicatorNames.CITIZENSHIP))
                Print.printChartData(citizenship, EU28_MEMBERS, seriesType, IndicatorNames.CITIZENSHIP);
            if (args.contains("--indicator=" + IndicatorNames.EMPLOYMENT_FEMALE_RATIO))
                Print.printChartData(employmentFemaleRatio, EU28_MEMBERS, seriesType, IndicatorNames.EMPLOYMENT_FEMALE_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.EMPLOYMENT_MALE_RATIO))
                Print.printChartData(employmentMaleRatio, EU28_MEMBERS, seriesType, IndicatorNames.EMPLOYMENT_MALE_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.COMPACT_EMPLOYMENT_GENDER_GAP))
                Print.printChartData(compactEmploymentGenderGap, EU28_MEMBERS, seriesType, IndicatorNames.COMPACT_EMPLOYMENT_GENDER_GAP);
            if (args.contains("--indicator=" + IndicatorNames.GENDER_PAY_GAP))
                Print.printChartData(genderPayGap, EU28_MEMBERS, seriesType, IndicatorNames.GENDER_PAY_GAP);
            if (args.contains("--indicator=" + IndicatorNames.POPULATION_LEGTST_TRUST_RATIO))
                Print.printChartData(populationLegtstTrustRatio, EU28_MEMBERS, seriesType, IndicatorNames.POPULATION_LEGTST_TRUST_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.POPULATION_OTHERS_TRUST_RATIO))
                Print.printChartData(populationOthersTrustRatio, EU28_MEMBERS, seriesType, IndicatorNames.POPULATION_OTHERS_TRUST_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.POPULATION_PLCTST_TRUST_RATIO))
                Print.printChartData(populationPlctstTrustRatio, EU28_MEMBERS, seriesType, IndicatorNames.POPULATION_PLCTST_TRUST_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.POPULATION_PLTTST_TRUST_RATIO))
                Print.printChartData(populationPlttstTrustRatio, EU28_MEMBERS, seriesType, IndicatorNames.POPULATION_PLTTST_TRUST_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.COMPACT_POPULATION_TRUST))
                Print.printChartData(compactPopulationTrust, EU28_MEMBERS, seriesType, IndicatorNames.COMPACT_POPULATION_TRUST);
            if (args.contains("--indicator=" + IndicatorNames.VOTER_TURNOUT))
                Print.printChartData(voterTurnout, EU28_MEMBERS, seriesType, IndicatorNames.VOTER_TURNOUT);
        }
    }

    /**
     * Convert the voter turnout data from csv into Java Map
     *
     * @param voterTurnoutPath The voter turnout file path
     * @return An ordered map with prepared data
     */
    private static Map<String, Number> voterTurnoutCsvToMap(String voterTurnoutPath) {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());
        String csvHeader = "Country;Election type;Year;Voter Turnout";
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(voterTurnoutPath));
            String readLine;

            while((readLine = br.readLine()) != null) {
                if (readLine.trim().length() > 0 && !readLine.contains(csvHeader)) {
                    String[] items = readLine.split(";");

                    String country = items[0].trim();
                    String voterType = items[1].trim();
                    int year = (int) Double.parseDouble(items[2].trim());
                    String valueStr = items[3].replace("%", "").trim();

                    if (valueStr.length() > 0) {
                        Number value = Double.parseDouble(valueStr);

                        for (Map.Entry<String, String> entry : EU28_MEMBERS_NAME.entrySet()) {
                            String entryKey = entry.getKey();
                            String entryValue = entry.getValue();

                            if (country.equals(entryValue)) {
                                String key = MapUtils.generateKey(entryKey, year);
                                preparedMap.put(key, value);
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file " + voterTurnoutPath + " has not been found.");
        } catch (IOException e) {
            System.err.println("Error at reading the file " + voterTurnoutPath + " from the disk.");
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.err.println("The file 'Buffered Reader' could not be closed.");
            }
        }

        return preparedMap;
    }

    /**
     * Generate the employment gender gap (male employment ratio - female employment ratio)
     *
     * @return An ordered map with prepared data
     */
    private static Map<String, Number> consolidateEmploymentGenderGap() {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());

        for (String code : EU28_MEMBERS) {
            for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
                String key = MapUtils.generateKey(code, year);
                double femaleRatio = employmentFemaleRatio.get(key).doubleValue();
                double maleRatio = employmentMaleRatio.get(key).doubleValue();
                Number genderGap = maleRatio - femaleRatio;
                preparedMap.put(key, genderGap);
            }
        }

        return preparedMap;
    }

    /**
     * Aggregate the population trust ratios into a single index (the average)
     *
     * @return An ordered map with aggregated data
     */
    private static Map<String, Number> consolidatePopulationTrust() {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());

        for (String code : EU28_MEMBERS) {
            for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
                String key = MapUtils.generateKey(code, year);

                // Get the EU average if it is missing from the country's dataset
                double valueLegtst = populationLegtstTrustRatio.get(key).doubleValue();
                double valueOthers = populationOthersTrustRatio.get(key).doubleValue();
                double valuePlctst = populationPlctstTrustRatio.get(key).doubleValue();
                double valuePlttst = populationPlttstTrustRatio.get(key).doubleValue();

                Number value = (valueLegtst + valueOthers + valuePlctst + valuePlttst) / 4;
                preparedMap.put(key, value);
            }
        }

        return preparedMap;
    }
}
