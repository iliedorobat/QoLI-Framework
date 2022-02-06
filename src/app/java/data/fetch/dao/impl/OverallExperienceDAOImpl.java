package app.java.data.fetch.dao.impl;

import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.Fetcher;
import app.java.data.fetch.FetcherUtils;
import app.java.data.fetch.dao.OverallExperienceDAO;
import org.apache.commons.collections4.MultiValuedMap;

public class OverallExperienceDAOImpl implements OverallExperienceDAO {
    private static final String HIGH_SATIS_LEVEL = "HIGH";
    private static final String OVERALL_LIFE_SATIS = "LIFESAT";

    public StringBuilder getHappinessRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y_GE16");
        params.put(ParamsConst.ISCED_11, "TOTAL");
        params.put(ParamsConst.UNIT, "PC");
        params.put(ParamsConst.SEX, "T");
        return Fetcher.fetchData("ilc_pw08", params);
    }

    public StringBuilder getHighSatisfactionRatio() {
        return FetcherUtils.getSatisfactionRatio(HIGH_SATIS_LEVEL, OVERALL_LIFE_SATIS);
    }
}
