package ro.webdata.qoli.aggr;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BuildProject {
    public static void main(String[] args) throws Exception {
        buildConstants();
        buildUtils();
        buildParams();
        buildPaths();
        buildCollector();
        buildStats();
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/StartupUtils.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/Main.java");
    }

    private static void buildConstants() throws Exception {
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/constants/Constants.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/constants/EnvConst.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/constants/ParamsNames.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/constants/ParamsValues.java");
    }

    private static void buildUtils() throws Exception {
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/Errors.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/MapOrder.java");

        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/utils/MapUtils.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/utils/MathUtils.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/utils/FileUtils.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/utils/StatsUtils.java");

        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/data/fetch/FetcherUtils.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/data/fetch/Fetcher.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/data/LocalParser.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/data/stats/MergeUtils.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/data/stats/Initializer.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/data/stats/Preparation.java");

        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/Print.java");
    }

    private static void buildCollector() throws Exception {
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/auxiliary/AuxiliaryCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/education/EducationCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/environment/EnvironmentCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/gov/GovRightsCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/health/HealthCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/leisureInteract/LeisureInteractCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/mainActivity/MainActivityCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/materialLiving/MaterialLivingCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/overall/OverallExperienceCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/safety/SafetyCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/data/fetch/DataCollector.java");
    }

    private static void buildParams() throws Exception {
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/auxiliary/AuxiliaryParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/education/EducationParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/environment/EnvironmentParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/gov/GovRightsParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/health/HealthParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/leisureInteract/LeisureInteractParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/mainActivity/MainActivityParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/materialLiving/MaterialLivingParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/overall/OverallExperienceParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/safety/SafetyParams.java");
    }

    private static void buildPaths() throws Exception {
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/auxiliary/AuxiliaryPaths.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/education/EducationPaths.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/environment/EnvironmentPaths.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/gov/GovRightsPaths.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/health/HealthPaths.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/leisureInteract/LeisureInteractPaths.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/mainActivity/MainActivityPaths.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/materialLiving/MaterialLivingPaths.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/overall/OverallExperiencePaths.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/safety/SafetyPaths.java");

        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/QoLIPaths.java");
    }

    private static void buildStats() throws Exception {
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/auxiliary/AuxiliaryStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/education/EducationStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/environment/EnvironmentStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/gov/GovRightsStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/health/HealthStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/leisureInteract/LeisureInteractStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/mainActivity/MainActivityStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/materialLiving/MaterialLivingStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/overall/OverallExperienceStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/safety/SafetyStats.java");

        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/aggr/commons/dimensions/QoLIStats.java");
    }

    // https://www.digitalocean.com/community/tutorials/compile-run-java-program-another-java-program
    private static void runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        printLines(command + " stdout:", pro.getInputStream());
        printLines(command + " stderr:", pro.getErrorStream());
        pro.waitFor();
        System.out.println(command + " exitValue() " + pro.exitValue());
    }

    private static void printLines(String cmd, InputStream ins) throws Exception {
        String line = null;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(cmd + " " + line);
        }
    }
}
