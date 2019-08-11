package app.java.commons.constants;

public class Constants {
    public static final String JSON_EXTENSION = ".json";
    public static final String CSV_EXTENSION = ".csv";
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
