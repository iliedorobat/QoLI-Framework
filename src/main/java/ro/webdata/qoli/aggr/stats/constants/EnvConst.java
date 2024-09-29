package ro.webdata.qoli.aggr.stats.constants;

import java.util.Calendar;

public class EnvConst {
    private static final int CRR_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    public static final String AUTH_USER = "admin";
    public static final String AUTH_PASSWORD = "admin1234";
    public static final boolean IS_TESTING = false;
    public static final boolean USE_TOMCAT_SERVER = false;

    public static final boolean PRINT_DIMENSION_IFO = false;
    public static final boolean PRINT_DIMENSION_VALUE = true;
    public static final boolean PRINT_DIMENSION_MISSING = false; // true;
    public static final boolean PRINT_AMPLITUDE = true;

    public static final int MIN_YEAR = 2004;
    public static final int MAX_YEAR = CRR_YEAR - 1;

    public static final int INIT_MAP_MIN_YEAR = MIN_YEAR;
    public static final int INIT_MAP_MAX_YEAR = MAX_YEAR;
}
