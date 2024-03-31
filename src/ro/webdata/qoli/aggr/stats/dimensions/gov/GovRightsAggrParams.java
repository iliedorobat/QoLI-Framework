package ro.webdata.qoli.aggr.commons.dimensions.gov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GovRightsAggrParams {
    public static final String GOVERNANCE = "governance";

    public static final String CITIZENSHIP_RATIO = "citizenshipRatio";
    public static final String GENDER_EMP_GAP = "genderEmpGap";
    public static final String GENDER_PAY_GAP = "genderPayGap";
    public static final String POPULATION_TRUST = "populationTrustRatio";
    public static final String POPULATION_TRUST_LEGTST = "populationTrustLegtstRatio";
    public static final String POPULATION_TRUST_OTHERS = "populationTrustOthersRatio";
    public static final String POPULATION_TRUST_PLCTST = "populationTrustPlctstRatio";
    public static final String POPULATION_TRUST_PLTTST = "populationTrustPlttstRatio";
    public static final String VOTER_TURNOUT = "voterTurnout";
    public static final String VOTER_TURNOUT_EU_PARLIAMENT = "voterTurnoutEuParliament";
    public static final String VOTER_TURNOUT_PARLIAMENTARY = "voterTurnoutParliamentary";
    public static final String VOTER_TURNOUT_PRESIDENTIAL = "voterTurnoutPresidential";

    public static final List<String> ALLOWED_PARAMS = new ArrayList<>() {{
        add(CITIZENSHIP_RATIO);
        add(GENDER_EMP_GAP);
        add(GENDER_PAY_GAP);
        add(POPULATION_TRUST);
        add(VOTER_TURNOUT);
    }};

    public static final Map<String, Boolean> IS_REVERSED = new HashMap<>() {{
        put(CITIZENSHIP_RATIO, false);
        put(GENDER_EMP_GAP, false); // is already reversed through the means of MathUtils.reverseGenderGap
        put(GENDER_PAY_GAP, false); // is already reversed through the means of MathUtils.reverseGenderGap
        put(POPULATION_TRUST, false);
        put(VOTER_TURNOUT, false);
    }};
}
