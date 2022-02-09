package app.java.commons.dimesntions.mainActivity;

import app.java.commons.constants.ParamsConst;
import app.java.commons.dimesntions.common.CommonParams;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import static app.java.commons.dimesntions.common.CommonParams.SATISFACTION_LEVELS_PARAMS;
import static app.java.commons.dimesntions.common.CommonParams.SATISFACTION_TYPES_PARAMS;

public class MainActivityParams {
    private static MultiValuedMap<String, String> getAvgWorkHoursParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y15-64");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "HR");
            put(ParamsConst.WORK_TIME, "FT");
            put(ParamsConst.WORKING_STATUS, "EMP");
        }};
    }

    public static MultiValuedMap<String, String> getAvgWorkHoursParams2007() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.NACE_R1, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "HR");
            put(ParamsConst.WORK_TIME, "TOTAL");
            put(ParamsConst.WORKING_STATUS, "EMP");
        }};
    }

    public static MultiValuedMap<String, String> getAvgWorkHoursParams2008() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.NACE_R2, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "HR");
            put(ParamsConst.WORK_TIME, "TOTAL");
            put(ParamsConst.WORKING_STATUS, "EMP");
        }};
    }

    public static MultiValuedMap<String, String> getEmploymentParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y15-64");
            put(ParamsConst.ISCED_11, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getInactivePopulationParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y15-64");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC_POP");
        }};
    }

    public static MultiValuedMap<String, String> getInvoluntaryPartTimeParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y15-64");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getJobSatisfactionParams() {
        return CommonParams.getSatisfactionParams(
                SATISFACTION_LEVELS_PARAMS.get("HIGH"),
                SATISFACTION_TYPES_PARAMS.get("JOB")
        );
    }

    public static MultiValuedMap<String, String> getLongTermUnemploymentParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y15-74");
            put(ParamsConst.INDIC_EM, "LTU");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC_ACT");
        }};
    }

    public static MultiValuedMap<String, String> getLowWageEarnersParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.UNIT, "PC");
            put(ParamsConst.AGE, "TOTAL");
            put(ParamsConst.SIZECLAS, "GE10");
        }};
    }

    public static MultiValuedMap<String, String> getOverQualifiedParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y15-64");
            put(ParamsConst.ISCED_11, "TOTAL");
            put(ParamsConst.MGSTATUS, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getResearchersParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.PROF_POS, "TOTAL");
            put(ParamsConst.SECT_PERF, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "FTE");
        }};
    }

    public static MultiValuedMap<String, String> getTemporaryEmploymentParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y15-64");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC_EMP");
            put(ParamsConst.WORKING_STATUS, "EMP_TEMP");
        }};
    }

    public static MultiValuedMap<String, String> getUnemploymentParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y15-74");
            put(ParamsConst.ISCED_11, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getWorkingNightsParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y15-64");
            put(ParamsConst.FREQUENCY, "USU");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
            put(ParamsConst.WORKING_STATUS, "EMP");
        }};
    }
}
