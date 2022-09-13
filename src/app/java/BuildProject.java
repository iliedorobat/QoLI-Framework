package app.java;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BuildProject {
    // TODO: renamve dimesntions => dimensions
    public static void main(String[] args) throws Exception {
        buildConstants();
        buildUtils();
        buildParams();
        buildCollector();
        buildStats();
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/Main.java");
    }

    private static void buildConstants() throws Exception {
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/constants/Constants.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/constants/DimensionNames.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/constants/EnvConst.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/constants/FileNameConst.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/constants/FilePathConst.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/constants/IndicatorNames.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/constants/ParamsConst.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/constants/ParamsValues.java");
    }

    private static void buildUtils() throws Exception {
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/Errors.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/MapOrder.java");

        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/utils/MapUtils.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/utils/MathUtils.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/utils/FileUtils.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/utils/StatsUtils.java");

        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/data/fetch/FetcherUtils.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/data/fetch/Fetcher.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/data/LocalParser.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/data/stats/MergeUtils.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/data/stats/Initializer.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/data/stats/Preparation.java");

        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/Print.java");
    }

    private static void buildCollector() throws Exception {
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/common/CommonCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/education/EducationCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/environment/EnvironmentCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/gov/GovRightsCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/health/HealthCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/interactions/InteractionsCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/leisure/LeisureCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/mainActivity/MainActivityCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/materialLiving/MaterialLivingCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/overall/OverallExperienceCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/safety/SafetyCollector.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/data/fetch/DataCollector.java");
    }

    private static void buildParams() throws Exception {
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/common/CommonParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/education/EducationParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/environment/EnvironmentParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/gov/GovRightsParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/health/HealthParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/interactions/InteractionsParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/leisure/LeisureParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/mainActivity/MainActivityParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/materialLiving/MaterialLivingParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/overall/OverallExperienceParams.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/safety/SafetyParams.java");
    }

    private static void buildStats() throws Exception {
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/common/CommonStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/education/EducationStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/environment/EnvironmentStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/gov/GovRightsStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/health/HealthStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/interactions/InteractionsStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/leisure/LeisureStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/mainActivity/MainActivityStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/materialLiving/MaterialLivingStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/overall/OverallExperienceStats.java");
        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/safety/SafetyStats.java");

        runProcess("javac -d ./out/production/QoLI-Framework src/app/java/commons/dimesntions/QoLIStats.java");
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
