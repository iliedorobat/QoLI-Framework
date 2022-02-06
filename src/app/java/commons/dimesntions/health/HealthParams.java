package app.java.commons.dimesntions.health;

import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;

public class HealthParams {
    private static MultiValuedMap<String, String> getConsumptionParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "TOTAL");
        params.put(ParamsConst.QUANTILE, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    private static MultiValuedMap<String, String> getUnmetHealthParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y_GE16");
        params.put(ParamsConst.QUANTILE, "TOTAL");
        params.put(ParamsConst.REASON, "TOOEFW");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getAlcoholicParams() {
        MultiValuedMap<String, String> params = getConsumptionParams();
        params.put(ParamsConst.AGE, "TOTAL");
        params.put(ParamsConst.C_BIRTH, "NAT");
        params.put(ParamsConst.FREQUENCY, "DAY");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getBodyMassIndexParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "TOTAL");
        params.put(ParamsConst.BMI, "BMI_GE25"); // Overweight
        params.put(ParamsConst.BMI, "BMI_GE30"); // Obese
        params.put(ParamsConst.QUANTILE, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getFVParams() {
        MultiValuedMap<String, String> params = getConsumptionParams();
        params.put(ParamsConst.AGE, "TOTAL");
        params.put(ParamsConst.N_PORTION, "GE5");
        params.put(ParamsConst.QUANTILE, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getHealthPersonnelParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.UNIT, "P_HTHAB");
        return params;
    }

    public static MultiValuedMap<String, String> getHealthyLifeParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y_GE16");
        params.put(ParamsConst.LEVELS, "VG_G");
        params.put(ParamsConst.QUANTILE, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getHealthyLifeYearsParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        // Healthy life years in absolute value at birth
        params.put(ParamsConst.INDIC_HE, "HLY_0");
        params.put(ParamsConst.SEX, "F");
        params.put(ParamsConst.SEX, "M");
        return params;
    }

    public static MultiValuedMap<String, String> getHospitalBedsParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.FACILITY, "HBEDT");
        params.put(ParamsConst.UNIT, "P_HTHAB");
        return params;
    }

    public static MultiValuedMap<String, String> getLifeExpectancyParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        // Life expectancy for people less than 1 year
        params.put(ParamsConst.AGE, "Y_LT1");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "YR");
        return params;
    }

    public static MultiValuedMap<String, String> getLongHealthIssueParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y_GE16");
        params.put(ParamsConst.QUANTILE, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getPhysicalActivitiesParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "TOTAL");
        params.put(ParamsConst.ISCED_11, "TOTAL");
        params.put(ParamsConst.PHYSICAL_ACTIVITY, "MV_AERO_MSC");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getSmokersParams() {
        MultiValuedMap<String, String> params = getConsumptionParams();
        params.put(ParamsConst.AGE, "TOTAL");
        params.put(ParamsConst.QUANTILE, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.SMOKING, "TOTAL");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getUnmetDentalParams() {
        return getUnmetHealthParams();
    }

    public static MultiValuedMap<String, String> getUnmetMedicalParams() {
        return getUnmetHealthParams();
    }

    public static MultiValuedMap<String, String> getWorkAccidentsParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.INJURY, "TOTAL");
        params.put(ParamsConst.SEVERITY, "D_GE4");
        params.put(ParamsConst.UNIT, "NR");
        return params;
    }
}
