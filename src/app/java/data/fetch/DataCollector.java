package app.java.data.fetch;

import app.java.commons.dimesntions.common.CommonCollector;
import app.java.commons.dimesntions.education.EducationCollector;
import app.java.commons.dimesntions.environment.EnvironmentCollector;
import app.java.commons.dimesntions.gov.GovRightsCollector;
import app.java.commons.dimesntions.health.HealthCollector;
import app.java.commons.dimesntions.interactions.InteractionsCollector;
import app.java.commons.dimesntions.leisure.LeisureCollector;
import app.java.commons.dimesntions.mainActivity.MainActivityCollector;
import app.java.commons.dimesntions.materialLiving.MaterialLivingCollector;
import app.java.commons.dimesntions.overall.OverallExperienceCollector;
import app.java.commons.dimesntions.safety.SafetyCollector;

/**
 * Collect the data from Eurostat portal and save it to json files
 */
public class DataCollector {
    public static void collectData() {
        System.out.println("Starting the data collection process...\n");

        EducationCollector.fetchData();
        EnvironmentCollector.fetchData();
        CommonCollector.fetchData();
        GovRightsCollector.fetchData();
        HealthCollector.fetchData();
        InteractionsCollector.fetchData();
        LeisureCollector.fetchData();
        MainActivityCollector.fetchData();
        MaterialLivingCollector.fetchData();
        OverallExperienceCollector.fetchData();
        SafetyCollector.fetchData();

        System.out.println("\nThe data collection process has been successfully completed!");
    }
}
