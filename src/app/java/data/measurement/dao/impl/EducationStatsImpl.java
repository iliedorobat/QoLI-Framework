package app.java.data.measurement.dao.impl;

import app.java.commons.constants.Constants;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.dao.EducationStatsDAO;
import app.java.data.parse.LocalParser;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EducationStatsImpl implements EducationStatsDAO {
    private static String JSON_EXT = Constants.JSON_EXTENSION;
    private static String
            digitalSkillsRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.DIGITAL_SKILLS_RATIO + JSON_EXT,
            earlyEducationRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.EARLY_EDU_RATIO + JSON_EXT,
            educationRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.EDU_RATIO + JSON_EXT,
            excludedRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.EXCLUDED_RATIO + JSON_EXT,
            leaversRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.LEAVERS_RATIO + JSON_EXT,
            pupilsRatio2012Path = FilePathConst.EDUCATION_PATH + FileNameConst.PUPILS_RATIO_2012 + JSON_EXT,
            pupilsRatio2013Path = FilePathConst.EDUCATION_PATH + FileNameConst.PUPILS_RATIO_2013 + JSON_EXT,
            trainingRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.TRAINING_RATIO + JSON_EXT,
            zeroForeignLangRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.ZERO_FOREIGN_LANG_RATIO + JSON_EXT;

    public void testing() {
        System.out.println("EDUCATION testing");

        Map<List<String>, Number> listListMap = LocalParser.readJSONFile(digitalSkillsRatioPath);
        for (Map.Entry<List<String>, Number> listListEntry : listListMap.entrySet()) {
            List<String> key = listListEntry.getKey();
            Number value = listListEntry.getValue();
            int size = key.size();

            String country = key.get(size - 2);
            int year = Integer.valueOf(key.get(size - 1));

            System.out.println(key + "   " + value + "   " + country + " " + year);
        }

//        Set<String> keys = LocalParser.getDimensionsOrder(digitalSkillsRatioPath);
//        Iterator iterator = keys.iterator();
//        while (iterator.hasNext())
//            System.out.print(iterator.next() + ", ");
    }
}
