package app.java.commons.dimesntions.overall;

import app.java.commons.MapOrder;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.stats.Initializer;
import app.java.data.stats.Preparation;
import org.apache.commons.collections4.MultiValuedMap;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.constants.Constants.JSON_EXTENSION;

public class OverallExperienceStats {
    // Queried params values
    private static final MultiValuedMap<String, String>
            HAPPINESS_RATIO = OverallExperienceParams.getHappinessParams(),
            HIGH_SATISFACTION_RATIO = OverallExperienceParams.getHighSatisfactionParams();

    private static final String
            happinessRatioPath = FilePathConst.OVERALL_EXPERIENCE_PATH + FileNameConst.HAPPINESS_RATIO + JSON_EXTENSION,
            highSatisfactionRatioPath = FilePathConst.OVERALL_EXPERIENCE_PATH + FileNameConst.HIGH_SATISFACTION_RATIO + JSON_EXTENSION;

    private static final Map<String, Number>
            initHappinessRatio = Initializer.initConsolidatedMap(HAPPINESS_RATIO, happinessRatioPath),
            initHighSatisfactionRatio = Initializer.initConsolidatedMap(HIGH_SATISFACTION_RATIO, highSatisfactionRatioPath);

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                happinessRatio = Preparation.prepareData(initHappinessRatio),
                highSatisfactionRatio = Preparation.prepareData(initHighSatisfactionRatio);

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double product = 1
                        * MathUtils.percentageSafetyDouble(happinessRatio, key)
                        * MathUtils.percentageSafetyDouble(highSatisfactionRatio, key);
                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }

    public static ArrayList<Map<String, Number>> getInitList() {
        return new ArrayList<>() {{
            add(Preparation.filterMap(initHappinessRatio));
            add(Preparation.filterMap(initHighSatisfactionRatio));
        }};
    }
}
