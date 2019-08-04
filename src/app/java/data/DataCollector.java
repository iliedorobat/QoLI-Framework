package app.java.data;

import app.java.data.collect.*;

/**
 * Collect the data from Eurostat portal and save it to json files
 */
public class DataCollector {
    public static void main(String[] args) {
        EducationCollector.dataCollector();
        EnvironmentCollector.dataCollector();
        GovRightsCollector.dataCollector();
        HealthCollector.dataCollector();
        MainActivityCollector.dataCollector();
        MaterialLivingCollector.dataCollector();
        OverallExperienceCollector.dataCollector();
        SafetyCollector.dataCollector();
        SocialActivityCollector.dataCollector();
    }
}
