package app.java.data;

import app.java.data.collect.*;

/**
 * Collect the data from Eurostat portal and save it to json files
 */
public class DataCollector {
    public static void collectData() {
        System.out.println("Starting the data collection process...\n");

        EducationCollector.dataCollector();
        EnvironmentCollector.dataCollector();
        GeneralCollector.dataCollector();
        GovRightsCollector.dataCollector();
        HealthCollector.dataCollector();
        InteractionsCollector.dataCollector();
        LeisureCollector.dataCollector();
        MainActivityCollector.dataCollector();
        MaterialLivingCollector.dataCollector();
        OverallExperienceCollector.dataCollector();
        SafetyCollector.dataCollector();

        System.out.println("\nThe data collection process has been successfully completed!");
    }
}
