package app.java.parser.http.dao.impl;

import app.java.parser.ParserUtils;
import app.java.parser.http.DataFetcher;
import app.java.parser.http.DataUtils;
import app.java.parser.http.JSONUtils;
import app.java.parser.http.dao.HealthDAO;
import org.apache.commons.collections4.MultiValuedMap;

public class HealthDAOImpl implements HealthDAO {
    public StringBuilder getLifeExpectancy() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        // Life expectancy for people less than 1 year
        params.put("age", "Y_LT1");
        params.put("sex", "T");
        params.put("unit", "YR");
        return DataFetcher.fetchData("demo_mlexpec", params);
    }

    public StringBuilder getHealthyLifeYears() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        // Healthy life years in absolute value at birth
        params.put("indic_he", "F_0_DFLE");
        params.put("indic_he", "M_0_DFLE");
        return DataFetcher.fetchData("hlth_hlye", params);
    }

    public StringBuilder getHealthyLifeRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("age", "Y_GE16");
        params.put("levels", "VG_G");
        params.put("quantile", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData("hlth_silc_10", params);
    }

    public StringBuilder getLongHealthIssueRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("age", "Y_GE16");
        params.put("quantile", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData("hlth_silc_11", params);
    }

    public StringBuilder getUnmetMedicalStatus() {
        MultiValuedMap<String, String> params = DataUtils.getUnmetHealthParams();
        return DataFetcher.fetchData("hlth_silc_08", params);
    }

    public StringBuilder getUnmetDentalStatus() {
        MultiValuedMap<String, String> params = DataUtils.getUnmetHealthParams();
        return DataFetcher.fetchData("hlth_silc_09", params);
    }

    public StringBuilder getBodyMassIndex() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("age", "TOTAL");
        params.put("bmi", "BMI_GE25"); // Overweight
        params.put("bmi", "BMI_GE30"); // Obese
        params.put("quantile", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData("hlth_ehis_bm1i", params);
    }

    public StringBuilder getSmokersRatio() {
        MultiValuedMap<String, String> params = DataUtils.getConsumptionParams();
        params.put("age", "TOTAL");
        params.put("quantile", "TOTAL");
        params.put("sex", "T");
        params.put("smoking", "TOTAL");
        params.put("unit", "PC");
        return DataFetcher.fetchData("hlth_ehis_sk3i", params);
    }

    public StringBuilder getAlcoholicRatio() {
        MultiValuedMap<String, String> params = DataUtils.getConsumptionParams();
        params.put("age", "TOTAL");
        params.put("c_birth", "NAT");
        params.put("frequenc", "DAY");
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData("hlth_ehis_al1b", params);
    }

    public StringBuilder getFVRatio() {
        MultiValuedMap<String, String> params = DataUtils.getConsumptionParams();
        params.put("age", "TOTAL");
        params.put("n_portion", "GE5");
        params.put("quantile", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData("hlth_ehis_fv3i", params);
    }

    public StringBuilder getPhysicalActivities() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("age", "TOTAL");
        params.put("isced11", "TOTAL");
        params.put("physact", "MV_AERO_MSC");
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData("hlth_ehis_pe9e", params);
    }

    public StringBuilder getHospitalBeds() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("facility", "HBEDT");
        params.put("unit", "P_HTHAB");
        return DataFetcher.fetchData("hlth_rs_bds", params);
    }

    public StringBuilder getWorkAccidents() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("injury", "TOTAL");
        params.put("severity", "D_GE4");
        params.put("unit", "NR");
        return DataFetcher.fetchData("hsw_mi07", params);
    }

    public StringBuilder getHealthPersonnelJSON() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("unit", "P_HTHAB");
        ParserUtils.addParams(params, JSONUtils.EU28_MEMBERS, "geo");
        return DataFetcher.fetchData("hlth_rs_prsrg", params);
    }
}
