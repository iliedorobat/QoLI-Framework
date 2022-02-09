package app.java.commons.constants;

import java.util.Map;
import java.util.TreeMap;

public class Constants {
    public static final String JSON_EXTENSION = ".json";
    public static final String CSV_EXTENSION = ".csv";
    public static final String CSV_SEPARATOR = ";";
    public static final String KEY_SEPARATOR = "_";

    /**
     * The minimum value for which the PERCENTAGE_SAFETY_THRESHOLD can be applied
     */
    public static final int PERCENTAGE_MIN_VALUE = -100;
    /**
     * A value that should be added to a percentage value in order to avoid the
     * multiplication or division by 0<br/>
     * E.g.:<br/>
     *      * percent = 0 => output = 0 + 101 = 101
     *      * percent = -100 => output = -100 + 101 = 1
     */
    public static final int PERCENTAGE_SAFETY_THRESHOLD = 101;

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

    public static final String SERIES_TYPE_COUNTRY = "country";
    public static final String SERIES_TYPE_REGION = "region";
}
