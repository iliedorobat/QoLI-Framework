package app.java;

import app.java.commons.Print;
import app.java.data.measurement.QoLI;
import app.java.data.measurement.dao.*;
import app.java.data.measurement.dao.impl.*;
import app.java.data.parse.LocalParser;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    private static EducationStatsDAO educationStatsDAO = new EducationStatsImpl();
    private static EnvironmentStatsDAO environmentStatsDAO= new EnvironmentStatsImpl();
    private static GovRightsStatsDAO govRightsStatsDAO = new GovRightsStatsImpl();
    private static HealthStatsDAO healthStatsDAO = new HealthStatsImpl();
    private static MainActivityStatsDAO mainActivityStatsDAO = new MainActivityStatsImpl();
    private static MaterialLivingStatsDAO materialLivingStatsDAO = new MaterialLivingStatsImpl();
    private static OverallExperienceStatsDAO overallExperienceStatsDAO = new OverallExperienceStatsImpl();
    private static SafetyStatsDAO safetyStatsDAO = new SafetyStatsImpl();
    private static SocialActivityStatsDAO socialActivityStatsDAO = new SocialActivityStatsImpl();

    public static void main(String[] args) {
//        // 1. Write a file to disk
//        GeneralDAO dao = new GeneralDAOImpl();
//        StringBuilder sb = dao.getPopulation();
//        FileUtils.writeToJSONFile(sb, FilePathConst.DATASET_PATH + FileNameConst.POPULATION);

//        // 2. (OPTIONAL) Print the data inconsistencies (available dataset and expected dataset)
//        Print.printDataInconsistencies();

        // 3. Print the QoLI and the QoLI dimensions values
        Map<String, Number>
                qoliList = QoLI.generateIndicatorList(),
                educationStats = educationStatsDAO.generateDimensionList(),
                environmentStats = environmentStatsDAO.generateDimensionList(),
                govRightsStats = govRightsStatsDAO.generateDimensionList(),
                healthStats = healthStatsDAO.generateDimensionList(),
                mainActivityStats = mainActivityStatsDAO.generateDimensionList(),
                materialLivingStats = materialLivingStatsDAO.generateDimensionList(),
                overallExperienceStats = overallExperienceStatsDAO.generateDimensionList(),
                safetyStats = safetyStatsDAO.generateDimensionList(),
                socialActivityStats = socialActivityStatsDAO.generateDimensionList();

        //TODO: write to disk
        Print.printCSV(qoliList, "QoLI");
//        Print.printCSV(educationStats, "Education");
//        Print.printCSV(environmentStats, "Environment");
//        Print.printCSV(govRightsStats, "GBR");
//        Print.printCSV(healthStats, "Health");
//        Print.printCSV(mainActivityStats, "PMA");
//        Print.printCSV(materialLivingStats, "MLC");
//        Print.printCSV(overallExperienceStats, "Overall Exp");
//        Print.printCSV(safetyStats, "Safety");
//        Print.printCSV(socialActivityStats, "LSI");
    }

    // For testing
    public static void print(String filePath) {
        Map<List<String>, Number> entries = LocalParser.readJSONFile(filePath);
        System.out.println(entries);

        Set<String> dimensions = LocalParser.getDimensionsOrder(filePath);
        System.out.println(dimensions);
    }
}
