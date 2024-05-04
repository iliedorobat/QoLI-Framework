package ro.webdata.qoli.aggr.stats.constants;

import java.util.HashMap;

public class ParamsValues {
    public static HashMap<String, String> ACL00 = new HashMap<>() {{
        put("citizenship", "AC43A");    // Active citizenship
        put("formal", "AC41A");         // Formal voluntary activities
        put("informal", "AC42A");       // Informal voluntary activities

        put("cinema", "AC521");
        put("culture", "AC523H");
        put("live", "AC522A");
        put("sports", "AC525");
    }};

    public static final HashMap<String, String> AIR_POL = new HashMap<>() {{
        put("ammonia", "NH3");          // Ammonia
        put("carbonMonoxide", "CO");    // Carbon monoxide (Ozone precursor)
        put("methane", "CH4");          // Methane (Ozone precursor)
        put("nonMethane", "NMVOC");     // Non-methane volatile organic compounds (Ozone precursor)
        put("nitrogenOxides", "NOX");   // Nitrogen oxides (Ozone precursor)
        put("PM2_5", "PM2_5");
        put("PM10", "PM10");
    }};

    public static HashMap<String, String> DOMAIN = new HashMap<>() {{
        put("legal", "LEG");
        put("other", "OTH");
        put("police", "POLC");
        put("politic", "POLIT");
    }};

    public static HashMap<String, String> HAPPINESS_LEVELS = new HashMap<>() {{
        put("always", "ALW");       // Always
        put("most", "MOST");        // Most of the time
        put("never", "NVR");        // Never
        put("rarely", "RAR");       // Rarely
        put("sometimes", "SMT");    // Sometimes
        put("unknown", "UNK");      // Unknown
    }};

    public static final HashMap<String, String> HEALTH_PROBLEMS = new HashMap<>() {{
        put("depressive", "DPR");
        put("majorDepressive", "DPR_MJR");
        put("otherDepressive", "DPR_OTH");
    }};

    public static HashMap<String, String> ICCS = new HashMap<>() {{
        put("assault", "ICCS020111");           // Serious Assault
        put("attemptedHomicide", "ICCS0102");   // Attempted intentional homicide
        put("bribery", "ICCS07031");            // Bribery
        put("burglary", "ICCS0501");            // Burglary
        put("burglaryPrivate", "ICCS05012");    // Burglary of private residential premises
        put("computers", "ICCS0903");           // Acts against computer systems
        put("criminalGroups", "ICCS09051");     // Participation in an organized criminal group
        put("corruption", "ICCS0703");          // Corruption
        put("fraud", "ICCS0701");               // Fraud
        put("homicide", "ICCS0101");            // Intentional homicide
        put("kidnapping", "ICCS020221");        // Kidnapping
        put("moneyLaundering", "ICCS07041");    // Money Laundering
        put("narcotics", "ICCS0601");           // Unlawful acts involving controlled drugs or precursors
        put("rape", "ICCS03011");               // Rape
        put("robbery", "ICCS0401");             // Robbery
        put("sexualAssault", "ICCS03012");      // Sexual assault
        put("sexualViolence", "ICCS0301");      // Sexual violence
        put("sexualExploitation", "ICCS0302");  // Sexual exploitation
        put("theft", "ICCS0502");               // Theft
        put("theftVehicle", "ICCS05021");       // Theft of a motorized land vehicle
    }};

    public static final HashMap<String, String> ISCED_11 = new HashMap<>() {{
        put("0_2", "ED0-2");                // Less than primary, primary and lower secondary education (levels 0-2)
        put("3_8", "ED3-8");                // Upper secondary, post-secondary non-tertiary and tertiary education (levels 3-8)
        put("3_4", "ED3_4");                // Upper secondary and post-secondary non-tertiary education (levels 3 and 4)
        put("3_4_GENERAL", "ED3_4GEN");     // Upper secondary and post-secondary non-tertiary education (levels 3 and 4) - general
        put("3_4_VOCATIONAL", "ED3_4VOC");  // Upper secondary and post-secondary non-tertiary education (levels 3 and 4) - vocational
        put("5_8", "ED5-8");                // Tertiary education (levels 5-8)
    }};

    public static HashMap<String, String> MED_SPEC = new HashMap<>() {{
        put("caringPersonal", "PER_CARE");
        put("dentists", "DENT");
        put("doctors", "PHYS");
        put("midwives", "MWS");
        put("nurses", "NRS");
        put("pharmacists", "PHARM");
        put("physiotherapists", "PHYSIO");
    }};

    public static HashMap<String, String> PERS_CAT = new HashMap<>() {{
        put("family", "FAM_REL");
        put("friends", "FRD");
    }};

    public static final HashMap<String, String> SATISFACTION_LEVELS = new HashMap<>() {{
        put("high", "HIGH");
        put("low", "LOW");
        put("medium", "MED");
    }};

    public static final HashMap<String, String> SATISFACTION_TYPES = new HashMap<>() {{
        put("accommodation", "ACCOM");
        put("commutingTime", "COM");
        put("financial", "FIN");
        put("greenAreas", "REC_GA");
        put("job", "JOB");
        put("environment", "LIVENV");
        put("meaningOfLife", "MEANLIFE");
        put("relationships", "PER_RELS");
        put("timeSpent", "TIME");
    }};

    public static HashMap<String, String> SOCIAL_ACTIVITIES_NP_REASON = new HashMap<>() {{
        put("away", "NACT_NB");         // None in the neighbourhood
        put("financial", "FIN");        // Financial reasons
        put("other", "OTH");            // Other
        put("uninterested", "NINT");    // No interest
    }};

    public static HashMap<String, String> VOLUNTARY_ACTIVITIES_NP_REASON = new HashMap<>() {{
        put("time", "NTIME");           // Time
        put("noInterest", "NINT");      // No interest
        put("other", "OTH");            // Other
    }};

    public static HashMap<String, String> SEX = new HashMap<>() {{
        put("female", "F");
        put("male", "M");
        put("total", "T");
    }};

    public static HashMap<String, String> SUBJNMON = new HashMap<>() {{
        put("greatDifficulty", "EM_GD");    // Households making ends meet with great difficulty
        put("difficulty", "EM_D");          // Households making ends meet with difficulty
        put("someDifficulty", "EM_SD");     // Households making ends meet with some difficulty
        put("fairlyEasily", "EM_FE");       // Households making ends meet fairly easily
        put("easily", "EM_E");              // Households making ends meet easily
        put("veryEasily", "EM_VE");         // Households making ends meet very easily
    }};

    public static HashMap<String, String> WORKING_FLEXIBILITY = new HashMap<>() {{
        put("noResponse", "NRP");                   // No response
        put("employerDecision", "EMPL_MAIN");       // Employer or organisation mainly decides
        put("personDecision", "PER_FUL");           // Person can fully decide
        put("restrictiveDecision", "PER_RSTR");     // Person can decide with certain restrictions
        put("total", "TOTAL");                      // Total
    }};
}
