package app.java.data.fetch.dao.impl;

import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.Fetcher;
import app.java.data.fetch.FetcherUtils;
import app.java.data.fetch.dao.GeneralDAO;
import org.apache.commons.collections4.MultiValuedMap;

public class GeneralDAOImpl implements GeneralDAO {
    public StringBuilder getPopulation() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "NR");
        return Fetcher.fetchData("demo_pjan", params);
    }
}
