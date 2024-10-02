package ro.webdata.qoli;

import java.io.*;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EnvState {
    private static final int CRR_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    private static final String ENV_PATH = Paths.get("").toAbsolutePath() + "/src/main/.env";
    private static final Map<String, String> VALUES = initState();

    public static final String AUTH_USER = VALUES.get("AUTH_USER");
    public static final String AUTH_PASSWORD = VALUES.get("AUTH_PASSWORD");
    public static final String HOST_NAME = VALUES.get("HOST_NAME");
    public static final boolean IS_PRODUCTION = VALUES.containsKey("IS_PRODUCTION") && VALUES.get("IS_PRODUCTION").equals("true");
    public static final boolean IS_TESTING = VALUES.containsKey("IS_TESTING") && VALUES.get("IS_TESTING").equals("true");
    public static final String KEY_STORE_FILE = VALUES.get("KEY_STORE_FILE");
    public static final String KEY_STORE_PASS = VALUES.get("KEY_STORE_PASS");
    public static final boolean USE_TOMCAT_SERVER = VALUES.containsKey("USE_TOMCAT_SERVER") && VALUES.get("USE_TOMCAT_SERVER").equals("true");

    public static final int MIN_YEAR = 2004;
    public static final int MAX_YEAR = CRR_YEAR - 1;
    public static final int INIT_MAP_MIN_YEAR = MIN_YEAR;
    public static final int INIT_MAP_MAX_YEAR = MAX_YEAR;

    private static Map<String, String> initState() {
        Map<String, String> values = new HashMap<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(ENV_PATH));
            String readLine;

            while((readLine = br.readLine()) != null) {
                if (!readLine.trim().isEmpty()) {
                    String[] pair = readLine.split("=");

                    if (pair.length == 2) {
                        values.put(pair[0], pair[1]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file " + ENV_PATH + " has not been found.");
        } catch (IOException e) {
            System.err.println("Error at reading the file " + ENV_PATH + " from the disk.");
        } finally {
            try {
                if (br != null)
                    br.close();
                else
                    System.out.println("The file " + ENV_PATH + " has already been closed.");
            } catch (IOException e) {
                System.err.println("The file " + ENV_PATH + " could not be closed.");
            }
        }

        return values;
    }
}
