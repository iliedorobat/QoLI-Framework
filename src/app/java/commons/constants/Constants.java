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

    // Eastern: BG; CZ; HU; PL; RO; SK
    // Northern: DK; EE; FI; IE; LV; LT; SE; UK
    // Southern: HR; EL; IT; MT; PT; SI; ES; CY
    // Western: AT; BE; FR; DE; LU; NL
    public static final String[] EU28_MEMBERS = {
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

    // In the offences ratio the UK is composed by England and Wales, Scotland and Northern Ireland
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
            // The offences rate for United Kingdom (UK) is split in three
            "UKC-L",// England and Wales
            "UKM",  // Scotland
            "UKN"   // Northern Ireland (UK)
    };
}
