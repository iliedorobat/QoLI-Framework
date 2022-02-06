package app.java.commons.dimesntions.mainActivity;

import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.FetcherUtils;
import app.java.commons.dimesntions.common.CommonParams;
import org.apache.commons.collections4.MultiValuedMap;

import static app.java.commons.dimesntions.common.CommonParams.SATISFACTION_LEVELS_PARAMS;
import static app.java.commons.dimesntions.common.CommonParams.SATISFACTION_TYPES_PARAMS;

public class MainActivityParams {
    public static MultiValuedMap<String, String> getActivePopulationParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y15-64");
        params.put(ParamsConst.INDIC_EM, "ACT");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC_POP");
        return params;
    }

    private static MultiValuedMap<String, String> getAvgWorkHoursParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y15-64");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "HR");
        params.put(ParamsConst.WORK_TIME, "FT");
        params.put(ParamsConst.WORKING_STATUS, "EMP");
        return params;
    }

    public static MultiValuedMap<String, String> getAvgWorkHoursParams2007() {
        MultiValuedMap<String, String> params = getAvgWorkHoursParams();
        params.put("nace_r1", "TOTAL");
        return params;
    }

    public static MultiValuedMap<String, String> getAvgWorkHoursParams2008() {
        MultiValuedMap<String, String> params = getAvgWorkHoursParams();
        params.put("nace_r2", "TOTAL");
        return params;
    }

    public static MultiValuedMap<String, String> getEmploymentParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y15-64");
        params.put(ParamsConst.ISCED_11, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getInactivePopulationParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y15-64");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC_POP");
        return params;
    }

    public static MultiValuedMap<String, String> getInvoluntaryPartTimeParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y15-64");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getJobSatisfactionParams() {
        return CommonParams.getSatisfactionParams(
                SATISFACTION_LEVELS_PARAMS.get("HIGH"),
                SATISFACTION_TYPES_PARAMS.get("JOB")
        );
    }

    public static MultiValuedMap<String, String> getLongTermUnemploymentParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y15-74");
        params.put(ParamsConst.INDIC_EM, "LTU");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC_ACT");
        return params;
    }

    public static MultiValuedMap<String, String> getLowWageEarnersParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.UNIT, "PC");
        params.put(ParamsConst.AGE, "TOTAL");
        // TODO: check
//        params.put(ParamsConst.SIZECLAS, "GE10");
        return params;
    }

    public static MultiValuedMap<String, String> getOverQualifiedParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y15-64");
        params.put(ParamsConst.ISCED_11, "TOTAL");
        params.put(ParamsConst.MGSTATUS, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getResearchersParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.PROF_POS, "TOTAL");
        params.put(ParamsConst.SECT_PERF, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "FTE");
        return params;
    }

    public static MultiValuedMap<String, String> getTemporaryEmploymentParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y15-64");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC_EMP");
        params.put(ParamsConst.WORK_TIME, "TEMP");
        return params;
    }

    public static MultiValuedMap<String, String> getUnemploymentParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y15-74");
        params.put(ParamsConst.ISCED_11, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getWorkingNightsParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y15-64");
        params.put(ParamsConst.FREQUENCY, "USU");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        params.put(ParamsConst.WORKING_STATUS, "EMP");
        return params;
    }
}
