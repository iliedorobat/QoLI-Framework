package app.java.data.fetch.dao.impl;

import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.Fetcher;
import app.java.data.fetch.FetcherUtils;
import app.java.data.fetch.dao.MaterialLivingDAO;
import org.apache.commons.collections4.MultiValuedMap;

public class MaterialLivingDAOImpl implements MaterialLivingDAO {
    private static final String[] END_MEET_DIFFICULTIES = {
            "EM_GD",    // households making ends meet with great difficulty
            "EM_D"      // households making ends meet with difficulty
    };
    private static final String[] QUINTILE_AGES = {
            "TOTAL",
            "Y_GE65",
            "Y_LT65"
    };

    public StringBuilder getDwellingIssuesRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getHomeConditionsParams();
        return Fetcher.fetchData("ilc_mdho01", params);
    }

    public StringBuilder getEndMeetInabilityRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.HHTYP, "TOTAL");
        params.put(ParamsConst.INC_GRP, "TOTAL");
        FetcherUtils.addParams(params, ParamsConst.SUBJNMON, END_MEET_DIFFICULTIES);
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("ilc_mdes09", params);
    }

    public StringBuilder getHighIncomeRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "TOTAL");
        params.put(ParamsConst.INDIC_IL, "LI_GE130MD");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("ilc_di20", params);
    }

    public StringBuilder getIncomeQuintileRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        FetcherUtils.addParams(params, ParamsConst.AGE, QUINTILE_AGES);
        params.put(ParamsConst.INDIC_IL, "S80_S20");
        params.put(ParamsConst.SEX, "T");
        return Fetcher.fetchData("ilc_di11", params);
    }

    public StringBuilder getLackOfBathsRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getHomeConditionsParams();
        return Fetcher.fetchData("ilc_mdho05", params);
    }

    public StringBuilder getLowWorkIntensityRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y_LT60");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC_Y_LT60");
        return Fetcher.fetchData("ilc_lvhl11", params);
    }

    public StringBuilder getMaterialDeprivationRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("ilc_mddd11", params);
    }

    public StringBuilder getMedianIncome() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "TOTAL");
        params.put(ParamsConst.INDIC_IL, "MED_E");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PPS");
        return Fetcher.fetchData("ilc_di03", params);
    }

    public StringBuilder getOverOccupiedRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getWorkOccupationParams();
        return Fetcher.fetchData("ilc_lvho05a", params);
    }

    public StringBuilder getPovertyRiskRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.HHTYP, "TOTAL");
        params.put(ParamsConst.INDIC_IL, "LI_R_MD60");
        return Fetcher.fetchData("ilc_li03", params);
    }

    public StringBuilder getPublicWaterRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.UNIT, "PC");
        params.put(ParamsConst.WAT_PROC, "POP_PWS");
        return Fetcher.fetchData("env_wat_pop", params);
    }

    public StringBuilder getPpsRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.NA_ITEM, "B1GQ");
        params.put(ParamsConst.UNIT, "PC_EU28_HAB_MEUR_CP");
        return Fetcher.fetchData("nama_10_pc", params);
    }

    public StringBuilder getUnderOccupiedRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getWorkOccupationParams();
        return Fetcher.fetchData("ilc_lvho50a", params);
    }
}
