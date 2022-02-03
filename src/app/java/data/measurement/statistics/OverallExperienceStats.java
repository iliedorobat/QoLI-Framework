package app.java.data.measurement.statistics;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.measurement.preparation.Initializer;
import app.java.data.measurement.preparation.Preparation;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.constants.Constants.JSON_EXTENSION;

public class OverallExperienceStats {
    // The list of queried values
    private static final String[]
            HIGH_SATISFACTION_RATIO = {"PC", "HIGH", "TOTAL", "LIFESAT", "T", "Y_GE16"};

    private static final String highSatisfactionRatioPath = FilePathConst.OVERALL_EXPERIENCE_PATH
            + FileNameConst.HIGH_SATISFACTION_RATIO + JSON_EXTENSION;

    private static final Map<String, Number>
            initHighSatisfactionRatio = Initializer.initConsolidatedMap(HIGH_SATISFACTION_RATIO, highSatisfactionRatioPath);

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number> highSatisfactionRatio = Preparation.prepareData(initHighSatisfactionRatio);

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double product = 1
                        * MathUtils.percentageSafetyDouble(highSatisfactionRatio, key);
                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }

    public static ArrayList<Map<String, Number>> getInitList() {
        return new ArrayList<>() {{
            add(Preparation.filterMap(initHighSatisfactionRatio));
        }};
    }
}
