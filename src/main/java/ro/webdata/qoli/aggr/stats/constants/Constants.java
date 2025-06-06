package ro.webdata.qoli.aggr.stats.constants;

import ro.webdata.qoli.EnvState;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

public class Constants {
    public static final String JSON_EXTENSION = ".json";
    public static final String CSV_EXTENSION = ".csv";
    public static final String CSV_SEPARATOR = ";";
    public static final String KEY_SEPARATOR = "_";
    public static final String XLS_EXTENSION = ".xls";

    public static final String BASE_PATH = EnvState.IS_PRODUCTION
            ? EnvState.USE_TOMCAT_SERVER
                ? File.separator + String.join(File.separator, "", "opt", "tomcat", "webapps", "qoli", "WEB-INF")
                : File.separator + String.join(File.separator, "var", "www", "life-index.eu", "server", "resources", "main")
            : String.join(File.separator, System.getProperty("user.dir"), "src", "main", "resources");
    public static final String PREPARED_DATASET_PATH = String.join(File.separator, BASE_PATH, "static", "prepared");
    public static final String RAW_DATASET_PATH = String.join(File.separator, BASE_PATH, "static", "raw", "json", "countries");

    /**
     * The minimum value for which the PERCENTAGE_SAFETY_THRESHOLD can be applied
     */
    public static final int PERCENTAGE_MIN_VALUE = -100;

    public static final String[] EU_EASTERN_MEMBERS = {
            "BG", "CZ", "HU", "PL", "RO", "SK"
    };
    public static final String[] EU_NORTHERN_MEMBERS = {
            "DK", "EE", "FI", "IE", "LV", "LT", "SE", "UK"
    };
    public static final String[] EU_SOUTHERN_MEMBERS = {
            "HR", "EL", "IT", "MT", "PT", "SI", "ES", "CY"
    };
    public static final String[] EU_WESTERN_MEMBERS = {
            "AT", "BE", "FR", "DE", "LU", "NL"
    };

    public static final String[] EU28_MEMBERS = {
//            "EU", // European Union (EU6-1958, EU9-1973, EU10-1981, EU12-1986, EU15-1995, EU25-2004, EU27-2007, EU28-2013, EU27-2020)
//            "EU27_2020", // European Union - 27 countries (from 2020)
//            "EU28", // European Union - 28 countries (2013-2020)
            "AT", // Austria
            "BE", // Belgium
            "BG", // Bulgaria
            "CY", // Cyprus
            "CZ", // Czechia
            "DE", // Germany (until 1990 former territory of the FRG)
            "DK", // Denmark
            "EE", // Estonia
            "EL", // Greece
            "ES", // Spain
            "FI", // Finland
            "FR", // France
            "HR", // Croatia
            "HU", // Hungary
            "IE", // Ireland
            "IT", // Italy
            "LT", // Lithuania
            "LU", // Luxembourg
            "LV", // Latvia
            "MT", // Malta
            "NL", // Netherlands
            "PL", // Poland
            "PT", // Portugal
            "RO", // Romania
            "SE", // Sweden
            "SI", // Slovenia
            "SK", // Slovakia
            "UK"  // United Kingdom
    };

    public static final Map<String, String> EU28_MEMBERS_NAME = new TreeMap<>();
    static {
        EU28_MEMBERS_NAME.put("AT", "Austria");
        EU28_MEMBERS_NAME.put("BE", "Belgium");
        EU28_MEMBERS_NAME.put("BG", "Bulgaria");
        EU28_MEMBERS_NAME.put("CY", "Cyprus");
        EU28_MEMBERS_NAME.put("CZ", "Czech Republic");
        EU28_MEMBERS_NAME.put("DE", "Germany");
        EU28_MEMBERS_NAME.put("DK", "Denmark");
        EU28_MEMBERS_NAME.put("EE", "Estonia");
        EU28_MEMBERS_NAME.put("EL", "Greece");
        EU28_MEMBERS_NAME.put("ES", "Spain");
        EU28_MEMBERS_NAME.put("FI", "Finland");
        EU28_MEMBERS_NAME.put("FR", "France");
        EU28_MEMBERS_NAME.put("HR", "Croatia");
        EU28_MEMBERS_NAME.put("HU", "Hungary");
        EU28_MEMBERS_NAME.put("IE", "Ireland");
        EU28_MEMBERS_NAME.put("IT", "Italy");
        EU28_MEMBERS_NAME.put("LT", "Lithuania");
        EU28_MEMBERS_NAME.put("LU", "Luxembourg");
        EU28_MEMBERS_NAME.put("LV", "Latvia");
        EU28_MEMBERS_NAME.put("MT", "Malta");
        EU28_MEMBERS_NAME.put("NL", "Netherlands");
        EU28_MEMBERS_NAME.put("PL", "Poland");
        EU28_MEMBERS_NAME.put("PT", "Portugal");
        EU28_MEMBERS_NAME.put("RO", "Romania");
        EU28_MEMBERS_NAME.put("SE", "Sweden");
        EU28_MEMBERS_NAME.put("SI", "Slovenia");
        EU28_MEMBERS_NAME.put("SK", "Slovakia");
        EU28_MEMBERS_NAME.put("UK", "United Kingdom");
    }

    public static final Map<String, String> NON_EU_MEMBERS_NAME = new TreeMap<>();
    static {
        NON_EU_MEMBERS_NAME.put("AL", "Albania");
        NON_EU_MEMBERS_NAME.put("AD", "Andorra");
        NON_EU_MEMBERS_NAME.put("AM", "Armenia");
        NON_EU_MEMBERS_NAME.put("AZ", "Azerbaijan");
        NON_EU_MEMBERS_NAME.put("BA", "Bosnia and Herzegovina");
        NON_EU_MEMBERS_NAME.put("BY", "Belarus");
        NON_EU_MEMBERS_NAME.put("CH", "Switzerland");
        NON_EU_MEMBERS_NAME.put("FO", "Faroe Islands");
        NON_EU_MEMBERS_NAME.put("GE", "Georgia");
        NON_EU_MEMBERS_NAME.put("IS", "Iceland");
        NON_EU_MEMBERS_NAME.put("KZ", "Kazakhstan");
        NON_EU_MEMBERS_NAME.put("LI", "Liechtenstein");
        NON_EU_MEMBERS_NAME.put("MC", "Monaco");
        NON_EU_MEMBERS_NAME.put("MD", "Moldova");
        NON_EU_MEMBERS_NAME.put("ME", "Montenegro");
        NON_EU_MEMBERS_NAME.put("MK", "North Macedonia");
        NON_EU_MEMBERS_NAME.put("NO", "Norway");
        NON_EU_MEMBERS_NAME.put("RU", "Russian Federation");
        NON_EU_MEMBERS_NAME.put("SM", "San Marino");
        NON_EU_MEMBERS_NAME.put("RS", "Serbia");
        NON_EU_MEMBERS_NAME.put("TR", "Türkiye");
        NON_EU_MEMBERS_NAME.put("UA", "Ukraine");
        NON_EU_MEMBERS_NAME.put("VA", "Vatican City");
    }

    public static final String[] EU28_MEMBERS_EXTENDED = {
//            "EU28", // European Union - 28 countries
            "AT", // Austria
            "BE", // Belgium
            "BG", // Bulgaria
            "CY", // Cyprus
            "CZ", // Czechia
            "DE", // Germany (until 1990 former territory of the FRG)
            "DK", // Denmark
            "EE", // Estonia
            "EL", // Greece
            "ES", // Spain
            "FI", // Finland
            "FR", // France
            "HR", // Croatia
            "HU", // Hungary
            "IE", // Ireland
            "IT", // Italy
            "LT", // Lithuania
            "LU", // Luxembourg
            "LV", // Latvia
            "MT", // Malta
            "NL", // Netherlands
            "PL", // Poland
            "PT", // Portugal
            "RO", // Romania
            "SE", // Sweden
            "SI", // Slovenia
            "SK", // Slovakia
            // The offence ratio of the UK is split in 3 entries: England and Wales, Scotland and Northern Ireland
            "UKC-L",// England and Wales
            "UKM",  // Scotland
            "UKN"   // Northern Ireland (UK)
    };

    public static final String[] EU28_REGIONS = {
            "EU_EASTERN",
            "EU_NORTHERN",
            "EU_SOUTHERN",
            "EU_WESTERN"
    };

    public static final String SERIES_TYPE_COUNTRY = "COUNTRY";
    public static final String SERIES_TYPE_REGION = "REGION";
    public static final String DIRECTION_COLUMN = "COLUMN";
    public static final String DIRECTION_ROW = "ROW";
}
