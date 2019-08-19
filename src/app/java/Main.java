package app.java;

import app.java.commons.Print;
import app.java.data.parse.LocalParser;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
//        // Write a file to disk
//        GeneralDAO dao = new GeneralDAOImpl();
//        StringBuilder sb = dao.getPopulation();
//        FileUtils.writeToJSONFile(sb, FilePathConst.DATASET_PATH + FileNameConst.POPULATION);

        // Print the data inconsistencies (available dataset and expected dataset)
        Print.printDataInconsistencies();

//        // Generate a dimension list
//        EducationStatsDAO statsDAO = new EducationStatsImpl();
//        EnvironmentStatsDAO statsDAO = new EnvironmentStatsImpl();
//        GovRightsStatsDAO statsDAO = new GovRightsStatsImpl();
//        HealthStatsDAO statsDAO = new HealthStatsImpl();
//        MainActivityStatsDAO statsDAO = new MainActivityStatsImpl();
//        MaterialLivingStatsDAO statsDAO = new MaterialLivingStatsImpl();
//        SafetyStatsDAO statsDAO = new SafetyStatsImpl();
//        SocialActivityStatsDAO statsDAO = new SocialActivityStatsImpl();
//        OverallExperienceStatsDAO statsDAO = new OverallExperienceStatsImpl();
//        statsDAO.generateDimensionList();

//        Map<String, Number> qoliList = QOLI.generateIndicatorList();
////        Print.print(qoliList, true);
//        Print.printCSV(qoliList);
    }

    // For testing
    public static void print(String filePath) {
        Map<List<String>, Number> entries = LocalParser.readJSONFile(filePath);
        System.out.println(entries);

        Set<String> dimensions = LocalParser.getDimensionsOrder(filePath);
        System.out.println(dimensions);
    }
}
