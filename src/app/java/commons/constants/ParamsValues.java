package app.java.commons.constants;

import java.util.HashMap;

public class ParamsValues {
    public static HashMap<String, String> ACL00_INTERACTIONS = new HashMap<>() {{
        put("cinema", "AC521");
        put("culture", "AC523H");
        put("live", "AC522A");
        put("sports", "AC525");
    }};

    public static HashMap<String, String> ACL00_LEISURE = new HashMap<>() {{
        put("citizenship", "AC43A");    // Active citizenship
        put("formal", "AC41A");         // Formal voluntary activities
        put("informal", "AC42A");       // Informal voluntary activities
    }};

    public static final HashMap<String, String> AIRPOL = new HashMap<>() {{
        put("PM2_5", "PM2_5");
        put("PM10", "PM10");
    }};

    public static final HashMap<String, String> BMI = new HashMap<>() {{
        put("overweight", "BMI_GE25");
        put("obese", "BMI_GE30");
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

    public static HashMap<String, String> IND_TYPE = new HashMap<>() {{
        put("family", "FAM");
        put("friends", "FRD");
    }};

    public static HashMap<String, String> INDIC_WB = new HashMap<>() {{
        put("legal", "LEGTST");
        put("others", "OTHTST");
        put("police", "PLCTST");
        put("politic", "PLTTST");
    }};

    public static final HashMap<String, String> ISCED_11 = new HashMap<>() {{
        put("0_2", "ED0-2");                // Less than primary, primary and lower secondary education (levels 0-2)
        put("3_8", "ED3-8");                // Upper secondary, post-secondary non-tertiary and tertiary education (levels 3-8)
        put("3_4", "ED3_4");                // Upper secondary and post-secondary non-tertiary education (levels 3 and 4)
        put("3_4_GENERAL", "ED3_4GEN");     // Upper secondary and post-secondary non-tertiary education (levels 3 and 4) - general
        put("3_4_VOCATIONAL", "ED3_4VOC");  // Upper secondary and post-secondary non-tertiary education (levels 3 and 4) - vocational
        put("5_8", "ED5-8");                // Tertiary education (levels 5-8)
    }};

    public static HashMap<String, String> ISCO08 = new HashMap<>() {{
        put("dentists", "OC2261");
        put("doctors", "OC221");
        put("nurses", "OC222_322");
        put("pharmacists", "OC2262");
        put("physiotherapists", "OC2264");
    }};

    public static HashMap<String, String> REASON = new HashMap<>() {{
        put("away", "NNB");             // None in the neighbourhood
        put("financial", "FIN");        // Financial reasons
        put("other", "OTH");            // Other
        put("uninterested", "NINT");    // No interest
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
