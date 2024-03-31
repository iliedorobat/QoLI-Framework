package ro.webdata.qoli.aggr;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BuildProject {
    public static void main(String[] args) throws Exception {
        buildConstants();
        buildUtils();
        buildAggrParams();
        buildParams();
        buildPaths();
        buildCollector();
        buildStats();
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/ParamsUtils.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/Main.java");
    }

    private static void buildConstants() throws Exception {
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/constants/Constants.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/constants/EnvConst.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/constants/ParamsNames.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/constants/ParamsValues.java");
    }

    private static void buildUtils() throws Exception {
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/Errors.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/MapOrder.java");

        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/utils/MapUtils.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/utils/MathUtils.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/utils/FileUtils.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/utils/StatsUtils.java");

        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/data/fetch/FetcherUtils.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/data/fetch/Fetcher.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/data/LocalParser.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/data/stats/MergeUtils.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/data/stats/Initializer.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/data/stats/Preparation.java");

        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/Print.java");
    }

    private static void buildAggrParams() throws Exception {
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/QoLIAggrParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/education/EducationAggrParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/environment/EnvironmentAggrParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/gov/GovRightsAggrParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/health/HealthAggrParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/leisureInteract/LeisureInteractAggrParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/mainActivity/MainActivityAggrParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/materialLiving/MaterialLivingAggrParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/overall/OverallExperienceAggrParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/safety/SafetyAggrParams.java");
    }

    private static void buildCollector() throws Exception {
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/auxiliary/AuxiliaryCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/education/EducationCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/environment/EnvironmentCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/gov/GovRightsCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/health/HealthCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/leisureInteract/LeisureInteractCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/mainActivity/MainActivityCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/materialLiving/MaterialLivingCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/overall/OverallExperienceCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/safety/SafetyCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/data/fetch/DataCollector.java");
    }

    private static void buildParams() throws Exception {
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/auxiliary/AuxiliaryParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/education/EducationParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/environment/EnvironmentParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/gov/GovRightsParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/health/HealthParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/leisureInteract/LeisureInteractParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/mainActivity/MainActivityParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/materialLiving/MaterialLivingParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/overall/OverallExperienceParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/safety/SafetyParams.java");
    }

    private static void buildPaths() throws Exception {
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/auxiliary/AuxiliaryPaths.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/education/EducationPaths.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/environment/EnvironmentPaths.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/gov/GovRightsPaths.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/health/HealthPaths.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/leisureInteract/LeisureInteractPaths.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/mainActivity/MainActivityPaths.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/materialLiving/MaterialLivingPaths.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/overall/OverallExperiencePaths.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/safety/SafetyPaths.java");

        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/QoLIPaths.java");
    }

    private static void buildStats() throws Exception {
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/auxiliary/AuxiliaryStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/education/EducationStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/environment/EnvironmentStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/gov/GovRightsStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/health/HealthStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/leisureInteract/LeisureInteractStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/mainActivity/MainActivityStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/materialLiving/MaterialLivingStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/overall/OverallExperienceStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/safety/SafetyStats.java");

        runProcess("javac -d ./out/production/QoLI-Framework src/ro/webdata/qoli/aggr/stats/dimensions/QoLIStats.java");
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
