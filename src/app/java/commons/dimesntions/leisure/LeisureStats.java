package app.java.commons.dimesntions.leisure;

import app.java.commons.MapOrder;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.constants.ParamsValues;
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

public class LeisureStats {
    // Queried params values
    private static final MultiValuedMap<String, String>
            FORMAL_VOLUNTARY_RATIO = LeisureParams.getVoluntaryActivitiesParams(ParamsValues.ACL00_LEISURE.get("formal")),
            INFORMAL_VOLUNTARY_RATIO = LeisureParams.getVoluntaryActivitiesParams(ParamsValues.ACL00_LEISURE.get("informal")),
            SATISFACTION_RATIO = LeisureParams.getTimeSpentSatisfactionParams(),
            SOCIAL_ACTIVITIES_RATIO = LeisureParams.getSocialActivitiesParams();

    private static final String
            satisfactionRatioPath = FilePathConst.LEISURE_PATH + FileNameConst.TIME_SPENT_SATISFACTION + JSON_EXTENSION,
            socialActivitiesRatioPath = FilePathConst.LEISURE_PATH + FileNameConst.SOCIAL_ACTIVITIES_RATIO + JSON_EXTENSION,
            voluntaryRatioPath = FilePathConst.LEISURE_PATH + FileNameConst.VOLUNTARY_ACTIVITIES_RATIO + JSON_EXTENSION;

    private static final Map<String, Number>
            initSatisfactionRatio = Initializer.initConsolidatedMap(SATISFACTION_RATIO, satisfactionRatioPath),
            initSocialActivitiesRatio = Initializer.initConsolidatedMap(SOCIAL_ACTIVITIES_RATIO, socialActivitiesRatioPath),
            initFormalVoluntaryRatio = Initializer.initConsolidatedMap(FORMAL_VOLUNTARY_RATIO, voluntaryRatioPath),
            initInformalVoluntaryRatio = Initializer.initConsolidatedMap(INFORMAL_VOLUNTARY_RATIO, voluntaryRatioPath);


    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                formalVoluntaryRatio = Preparation.prepareData(initFormalVoluntaryRatio),
                informalVoluntaryRatio = Preparation.prepareData(initInformalVoluntaryRatio),
                satisfactionRatio = Preparation.prepareData(initSatisfactionRatio),
                socialActivitiesRatio = Preparation.prepareData(initSocialActivitiesRatio);

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double product = 1
                        * MathUtils.percentageSafetyDouble(formalVoluntaryRatio, key)
                        * MathUtils.percentageSafetyDouble(informalVoluntaryRatio, key)
                        * MathUtils.percentageSafetyDouble(satisfactionRatio, key)
                        * MathUtils.percentageSafetyDouble(socialActivitiesRatio, key);
                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(StatsUtils.generateVariation(askingRatio, true));
//        Print.print(askingRatio, true);

        return consolidatedList;
    }

    public static ArrayList<Map<String, Number>> getInitList() {
        return new ArrayList<>() {{
            add(Preparation.filterMap(initFormalVoluntaryRatio));
            add(Preparation.filterMap(initInformalVoluntaryRatio));
            add(Preparation.filterMap(initSocialActivitiesRatio));
            add(Preparation.filterMap(initSatisfactionRatio));
        }};
    }
}
