package app.java.data;

import app.java.data.collect.*;
import app.java.data.fetch.dao.EducationDAO;
import app.java.data.fetch.dao.impl.EducationDAOImpl;

public class DataCollector {
    private static EducationDAO educationDAO = new EducationDAOImpl();

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
