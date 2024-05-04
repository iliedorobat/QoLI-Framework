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
    public static final String POPULATION_TRUST_LEGAL = GOVERNANCE + ":populationTrustLegtstRatio";
    public static final String POPULATION_TRUST_OTHER = GOVERNANCE + ":populationTrustOthersRatio";
    public static final String POPULATION_TRUST_POLICE = GOVERNANCE + ":populationTrustPlctstRatio";
    public static final String POPULATION_TRUST_POLITIC = GOVERNANCE + ":populationTrustPlttstRatio";
    public static final String VOTER_TURNOUT = GOVERNANCE + ":voterTurnout";
    public static final String VOTER_TURNOUT_EU_PARLIAMENT = GOVERNANCE + ":voterTurnoutEuParliament";
    public static final String VOTER_TURNOUT_PARLIAMENTARY = GOVERNANCE + ":voterTurnoutParliamentary";
    public static final String VOTER_TURNOUT_PRESIDENTIAL = GOVERNANCE + ":voterTurnoutPresidential";

    public static final Map<String, String> AGGR_PARAMS_LABELS = new HashMap<>() {{
        put(CITIZENSHIP_RATIO, "Citizenship Ratio");
        put(GENDER_EMP_GAP, "Gender Employment Gap");
        put(GENDER_PAY_GAP, "Gender Pay Gap");
        put(POPULATION_TRUST, "Population Trust Ratio");
        put(VOTER_TURNOUT, "Voter Turnout");
    }};

    public static final Map<String, String> AGGR_PARAMS_UNITS = new HashMap<>() {{
        put(CITIZENSHIP_RATIO, PERCENT);
        put(GENDER_EMP_GAP, PERCENT);
        put(GENDER_PAY_GAP, PERCENT);
        put(POPULATION_TRUST, SCORE_10_100);
        put(VOTER_TURNOUT, PERCENT);
    }};

    public static final List<String> AGGR_PARAMS = List.copyOf(AGGR_PARAMS_LABELS.keySet());

    public static final Map<String, Boolean> AGGR_REVERSED_STATES = new HashMap<>() {{
        put(CITIZENSHIP_RATIO, false);
        put(GENDER_EMP_GAP, false); // has already been reversed through the means of MathUtils.reverseGenderGap
        put(GENDER_PAY_GAP, false); // has already been reversed through the means of MathUtils.reverseGenderGap
        put(POPULATION_TRUST, false);
        put(VOTER_TURNOUT, false);
    }};

    public static final Map<String, String> IND_PARAMS_LABELS = new HashMap<>() {{
        put(CITIZENSHIP_RATIO, "Citizenship Ratio");
        put(GENDER_EMP_GAP, "Gender Employment Gap");
        put(GENDER_PAY_GAP, "Gender Pay Gap");
        put(POPULATION_TRUST_LEGAL, "Population Trust in The Legal System");
        put(POPULATION_TRUST_OTHER, "Population Trust in Others");
        put(POPULATION_TRUST_POLICE, "Population Trust in The Police");
        put(POPULATION_TRUST_POLITIC, "Population Trust in The Political System");
        put(VOTER_TURNOUT_EU_PARLIAMENT, "Voter Turnout in The Elections For The European Parliament");
        put(VOTER_TURNOUT_PARLIAMENTARY, "Voter Turnout in The Elections For The National Parliament");
        put(VOTER_TURNOUT_PRESIDENTIAL, "Voter Turnout in The Elections For The National President");
    }};

    public static final Map<String, String> IND_PARAMS_UNITS = new HashMap<>() {{
        put(CITIZENSHIP_RATIO, PERCENT);
        put(GENDER_EMP_GAP, PERCENT);
        put(GENDER_PAY_GAP, PERCENT);
        put(POPULATION_TRUST_LEGAL, SCORE_1_10);
        put(POPULATION_TRUST_OTHER, SCORE_1_10);
        put(POPULATION_TRUST_POLICE, SCORE_1_10);
        put(POPULATION_TRUST_POLITIC, SCORE_1_10);
        put(VOTER_TURNOUT_EU_PARLIAMENT, PERCENT);
        put(VOTER_TURNOUT_PARLIAMENTARY, PERCENT);
        put(VOTER_TURNOUT_PRESIDENTIAL, PERCENT);
    }};

    public static final List<String> IND_PARAMS = List.copyOf(IND_PARAMS_LABELS.keySet());

    public static final Map<String, Boolean> IND_REVERSED_STATES = new HashMap<>() {{
        put(CITIZENSHIP_RATIO, false);
        put(GENDER_EMP_GAP, true);
        put(GENDER_PAY_GAP, true);
        put(POPULATION_TRUST_LEGAL, false);
        put(POPULATION_TRUST_OTHER, false);
        put(POPULATION_TRUST_POLICE, false);
        put(POPULATION_TRUST_POLITIC, false);
        put(VOTER_TURNOUT_EU_PARLIAMENT, false);
        put(VOTER_TURNOUT_PARLIAMENTARY, false);
        put(VOTER_TURNOUT_PRESIDENTIAL, false);
    }};
}
