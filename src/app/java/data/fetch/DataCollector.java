package app.java.data.fetch;

import app.java.commons.dimensions.auxiliary.AuxiliaryCollector;
import app.java.commons.dimensions.education.EducationCollector;
import app.java.commons.dimensions.environment.EnvironmentCollector;
import app.java.commons.dimensions.gov.GovRightsCollector;
import app.java.commons.dimensions.health.HealthCollector;
import app.java.commons.dimensions.leisureInteract.LeisureInteractCollector;
import app.java.commons.dimensions.mainActivity.MainActivityCollector;
import app.java.commons.dimensions.materialLiving.MaterialLivingCollector;
import app.java.commons.dimensions.overall.OverallExperienceCollector;
import app.java.commons.dimensions.safety.SafetyCollector;

/**
 * Collect the data from Eurostat portal and save it to json files
 */
public class DataCollector {
    public static void collectData() {
        System.out.println("Starting the data collection process...\n");

        AuxiliaryCollector.fetchData();
        EducationCollector.fetchData();
        EnvironmentCollector.fetchData();
        GovRightsCollector.fetchData();
        HealthCollector.fetchData();
        LeisureInteractCollector.fetchData();
        MainActivityCollector.fetchData();
        MaterialLivingCollector.fetchData();
        OverallExperienceCollector.fetchData();
        SafetyCollector.fetchData();

        System.out.println("\nThe data collection process has been successfully completed!");
    }
}
