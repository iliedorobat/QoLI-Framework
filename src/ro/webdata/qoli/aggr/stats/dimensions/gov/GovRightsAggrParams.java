package ro.webdata.qoli.aggr.stats.dimensions.gov;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ro.webdata.qoli.aggr.stats.constants.ParamsUnits.*;

public class GovRightsAggrParams {
    public static final String GOVERNANCE = "governance";

    public static final String CITIZENSHIP_RATIO = GOVERNANCE + ":citizenshipRatio";
    public static final String GENDER_EMP_GAP = GOVERNANCE + ":genderEmpGap";
    public static final String GENDER_PAY_GAP = GOVERNANCE + ":genderPayGap";
    public static final String POPULATION_TRUST = GOVERNANCE + ":populationTrustRatio";
    public static final String POPULATION_TRUST_LEGTST = GOVERNANCE + ":populationTrustLegtstRatio";
    public static final String POPULATION_TRUST_OTHERS = GOVERNANCE + ":populationTrustOthersRatio";
    public static final String POPULATION_TRUST_PLCTST = GOVERNANCE + ":populationTrustPlctstRatio";
    public static final String POPULATION_TRUST_PLTTST = GOVERNANCE + ":populationTrustPlttstRatio";
    public static final String VOTER_TURNOUT = GOVERNANCE + ":voterTurnout";
    public static final String VOTER_TURNOUT_EU_PARLIAMENT = GOVERNANCE + ":voterTurnoutEuParliament";
    public static final String VOTER_TURNOUT_PARLIAMENTARY = GOVERNANCE + ":voterTurnoutParliamentary";
    public static final String VOTER_TURNOUT_PRESIDENTIAL = GOVERNANCE + ":voterTurnoutPresidential";

    public static final Map<String, String> AGGR_PARAM_LABELS = new HashMap<>() {{
        put(CITIZENSHIP_RATIO, "Citizenship Ratio");
        put(GENDER_EMP_GAP, "Gender Employment Gap");
        put(GENDER_PAY_GAP, "Gender Pay Gap");
        put(POPULATION_TRUST, "Population Trust Ratio");
        put(VOTER_TURNOUT, "Voter Turnout");
    }};

    public static final Map<String, String> AGGR_PARAMS_UNITS = new HashMap<>() {{
        put(CITIZENSHIP_RATIO, PERCENT);
        put(GENDER_EMP_GAP, PERCENT); // TODO: check
        put(GENDER_PAY_GAP, PERCENT); // TODO: check
        put(POPULATION_TRUST, GEO_MEAN_SCORE_10_100);
        put(VOTER_TURNOUT, GEO_MEAN_PERCENT_POP);
    }};

    public static final List<String> AGGR_PARAMS = List.copyOf(AGGR_PARAM_LABELS.keySet());

    public static final Map<String, Boolean> AGGR_REVERSED_STATE = new HashMap<>() {{
        put(CITIZENSHIP_RATIO, false);
        put(GENDER_EMP_GAP, false); // is already reversed through the means of MathUtils.reverseGenderGap
        put(GENDER_PAY_GAP, false); // is already reversed through the means of MathUtils.reverseGenderGap
        put(POPULATION_TRUST, false);
        put(VOTER_TURNOUT, false);
    }};
}
