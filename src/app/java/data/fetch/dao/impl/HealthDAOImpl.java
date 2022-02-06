package app.java.data.fetch.dao.impl;

import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.Fetcher;
import app.java.data.fetch.FetcherUtils;
import app.java.data.fetch.dao.HealthDAO;
import org.apache.commons.collections4.MultiValuedMap;

public class HealthDAOImpl implements HealthDAO {
    public StringBuilder getAlcoholicRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getConsumptionParams();
        params.put(ParamsConst.AGE, "TOTAL");
        params.put(ParamsConst.C_BIRTH, "NAT");
        params.put(ParamsConst.FREQUENCY, "DAY");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("hlth_ehis_al1b", params);
    }

    public StringBuilder getBodyMassIndexRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "TOTAL");
        params.put(ParamsConst.BMI, "BMI_GE25"); // Overweight
        params.put(ParamsConst.BMI, "BMI_GE30"); // Obese
        params.put(ParamsConst.QUANTILE, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("hlth_ehis_bm1i", params);
    }

    public StringBuilder getFVRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getConsumptionParams();
        params.put(ParamsConst.AGE, "TOTAL");
        params.put(ParamsConst.N_PORTION, "GE5");
        params.put(ParamsConst.QUANTILE, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("hlth_ehis_fv3i", params);
    }

    public StringBuilder getHealthPersonnelRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.UNIT, "P_HTHAB");
        return Fetcher.fetchData("hlth_rs_prsrg", params);
    }

    public StringBuilder getHealthyLifeRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y_GE16");
        params.put(ParamsConst.LEVELS, "VG_G");
        params.put(ParamsConst.QUANTILE, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("hlth_silc_10", params);
    }

    public StringBuilder getHealthyLifeYears() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        // Healthy life years in absolute value at birth
        params.put(ParamsConst.INDIC_HE, "HLY_0");
        params.put(ParamsConst.SEX, "F");
        params.put(ParamsConst.SEX, "M");
        return Fetcher.fetchData("hlth_hlye", params);
    }

    public StringBuilder getHospitalBedsRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.FACILITY, "HBEDT");
        params.put(ParamsConst.UNIT, "P_HTHAB");
        return Fetcher.fetchData("hlth_rs_bds", params);
    }

    public StringBuilder getLifeExpectancy() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        // Life expectancy for people less than 1 year
        params.put(ParamsConst.AGE, "Y_LT1");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "YR");
        return Fetcher.fetchData("demo_mlexpec", params);
    }

    public StringBuilder getLongHealthIssueRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y_GE16");
        params.put(ParamsConst.QUANTILE, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("hlth_silc_11", params);
    }

    public StringBuilder getPhysicalActivitiesRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "TOTAL");
        params.put(ParamsConst.ISCED_11, "TOTAL");
        params.put(ParamsConst.PHYSICAL_ACTIVITY, "MV_AERO_MSC");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("hlth_ehis_pe9e", params);
    }

    public StringBuilder getSmokersRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getConsumptionParams();
        params.put(ParamsConst.AGE, "TOTAL");
        params.put(ParamsConst.QUANTILE, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.SMOKING, "TOTAL");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("hlth_ehis_sk3i", params);
    }

    public StringBuilder getUnmetDentalRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getUnmetHealthParams();
        return Fetcher.fetchData("hlth_silc_09", params);
    }

    public StringBuilder getUnmetMedicalRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getUnmetHealthParams();
        return Fetcher.fetchData("hlth_silc_08", params);
    }

    public StringBuilder getWorkAccidents() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.INJURY, "TOTAL");
        params.put(ParamsConst.SEVERITY, "D_GE4");
        params.put(ParamsConst.UNIT, "NR");
        return Fetcher.fetchData("hsw_mi07", params);
    }
}
