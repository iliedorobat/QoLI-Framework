"""
Auxiliary parameters for statistical dimensions
"""

from typing import Dict, List, Optional

from framework.common.param_names import ParamNames


LOW_WORK_INTENSITY_RATIO_PARAMS = {
    ParamNames.AGE: ["Y_LT60"],
    ParamNames.FREQ: ["A"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC_Y_LT60"]
}

POPULATION_PARAMS = {
    ParamNames.AGE: ["TOTAL"],
    ParamNames.FREQ: ["A"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["NR"]
}

def get_active_people_params(activities: List[str]) -> Optional[Dict[str, List[str]]]:
    """
    Get parameters for active people.

    Args:
        activities: The activity types:
            - AC41A: Formal voluntary activities
            - AC42A: Informal voluntary activities
            - AC43A: Active citizenship

    Returns:
        Dictionary of parameters or None if error occurs
    """
    try:
        # Validate activities (equivalent to Errors.throwNewError)
        if not activities:
            raise ValueError("Activities cannot be empty")

        params = {
            ParamNames.AGE: ["Y_GE16"],
            ParamNames.FREQ: ["A"],
            ParamNames.ISCED_11: ["TOTAL"],
            ParamNames.SEX: ["T"],
            ParamNames.UNIT: ["PC"],
            ParamNames.ACL_00: activities
        }

        return params
    except Exception as e:
        print(f"Error: {e}")
        return None

def get_satisfaction_params(satisfaction_level: str, satisfaction_type: str) -> Dict[str, List[str]]:
    """
    Get parameters for satisfaction measurements.

    Args:
        satisfaction_level: The satisfaction level (HIGH, MED, LOW)
        satisfaction_type: The type of well-being (ACCOM, COM, FIN, JOB, LIVENV,
                         MEANLIFE, PER_RELS, REC_GA, TIME)

    Returns:
        Dictionary of parameters
    """
    return {
        ParamNames.AGE: ["Y_GE16"],
        ParamNames.ISCED_11: ["TOTAL"],
        ParamNames.LEV_SATIS: [satisfaction_level],
        ParamNames.LIFE_SAT: [satisfaction_type],
        ParamNames.SEX: ["T"],
        ParamNames.FREQ: ["A"],
        ParamNames.UNIT: ["PC"]
    }
