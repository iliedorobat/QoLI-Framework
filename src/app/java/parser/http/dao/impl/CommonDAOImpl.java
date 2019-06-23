package app.java.parser.http.dao.impl;

import app.java.commons.Errors;
import app.java.parser.ParserUtils;
import app.java.parser.http.DataFetcher;
import app.java.parser.http.dao.CommonDAO;

import java.util.Map;

public class CommonDAOImpl implements CommonDAO {
    public static final String[] SATIS_LEVEL = {
            "HIGH",
            "MED",
            "LOW"
    };
    public static final String[] WEL_BEING_TYPE = {
            "ACCSAT",
            "COMSAT",
            "FINSAT",
            "JOBSAT",
            "GREENSAT",
            "LIFESAT",
            "LIVENVSAT",
            "MEANLIFE",
            "RELSAT",
            "TIMESAT"
    };

    public StringBuilder getSatisfactionRatio(String satisLevel, String wellBeing) {
        try {
            Errors.throwNewError(SATIS_LEVEL, satisLevel, "satisfaction levels");
            Errors.throwNewError(WEL_BEING_TYPE, wellBeing, "well being levels");

            Map<String, String> params = ParserUtils.getGeneralHttpParams();
            params.put("age", "Y_GE16");
            params.put("indic_wb", wellBeing);
            params.put("isced11", "TOTAL");
            params.put("lev_satis", satisLevel);
            params.put("sex", "T");
            params.put("unit", "PC");

            return DataFetcher.fetchData("ilc_pw05", params);
        } catch (Exception e) {
            return null;
        }
    }
}
