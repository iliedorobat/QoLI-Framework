package app.java.commons;

public class JSONUtils {
    public static final String[] EU28_MEMBERS = {
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

    public static String getGeoParams() {
        String output = "";

        for (int i = 0; i < EU28_MEMBERS.length; i++) {
            output += "geo=" + EU28_MEMBERS[i];

            if (i < EU28_MEMBERS.length - 1) {
                output += "&";
            }
        }

        return output;
    }
}
