package app.java.data.fetch.dao.impl;

import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.Fetcher;
import app.java.data.fetch.FetcherUtils;
import app.java.data.fetch.dao.LeisureDAO;
import org.apache.commons.collections4.MultiValuedMap;

public class LeisureDAOImpl implements LeisureDAO {
    private static final String HIGH_SATIS_LEVEL = "HIGH";
    private static final String TIME_SPENT_SATISFACTION = "TIMESAT";
    private static final String[] VOLUNTARY_ACTIVITIES = {
            "AC41A",    // Formal voluntary activities
            "AC42A"     // Informal voluntary activities
    };

    public StringBuilder getSocialActivitiesRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.DEG_URB, "TOTAL");
        params.put(ParamsConst.FREQUENCY, "GE1");
        params.put(ParamsConst.HHTYP, "TOTAL");
        params.put(ParamsConst.QUANTILE, "TOTAL");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("ilc_scp02", params);
    }

    public StringBuilder getTimeSpentSatisfaction() {
        return FetcherUtils.getSatisfactionRatio(HIGH_SATIS_LEVEL, TIME_SPENT_SATISFACTION);
    }

    public StringBuilder getVoluntaryActivitiesRatio() {
        return FetcherUtils.getActivePeopleRatio(VOLUNTARY_ACTIVITIES);
    }
}
