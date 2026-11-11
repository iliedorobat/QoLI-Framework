from framework.constants.param_names import ParamNames

DIGITAL_SKILLS_PARAMS = {
    ParamNames.FREQ: ["A"],
    ParamNames.INDIC_IS: ["I_DSK2_BAB"],
    ParamNames.IND_TYPE: ["IND_TOTAL"],
    ParamNames.UNIT: ["PC_IND"],
}

DROPOUT_RATIO_PARAMS = {
    ParamNames.AGE: ["Y18-24"],
    ParamNames.FREQ: ["A"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
    ParamNames.WORKING_STATUS: ["POP"],
}

EARLY_EDUCATION_RATIO_PARAMS = {
    ParamNames.FREQ: ["A"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
}

EDUCATION_RATIO_PARAMS = {
    ParamNames.AGE: ["Y15-64"],
    ParamNames.FREQ: ["A"],
    ParamNames.ISCED_11: ["ED3-8"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
}

INACTIVE_YOUNG_RATIO_PARAMS = {
    ParamNames.AGE: ["Y15-29"],
    ParamNames.FREQ: ["A"],
    ParamNames.SEX: ["T"],
    ParamNames.TRAINING: ["NO_FE_NO_NFE"],
    ParamNames.UNIT: ["PC"],
    ParamNames.WORKING_STATUS: ["NEMP"],
}

NO_FOREIGN_LANG_KNOWN_RATIO_PARAMS = {
    ParamNames.AGE: ["Y25-64"],
    ParamNames.FREQ: ["A"],
    ParamNames.N_LANG: ["0"],
    ParamNames.UNIT: ["PC"],
}

PUPILS_RATIO_2012_PARAMS = {
    ParamNames.FREQ: ["A"],
    ParamNames.INDIC_ED: ["ST1_1"],
}

PUPILS_RATIO_2013_PARAMS = {
    ParamNames.FREQ: ["A"],
    ParamNames.ISCED_11: ["ED1-3"],
    ParamNames.UNIT: ["RT"],
}

TRAINING_LAST_MONTH_RATIO_PARAMS = {
    ParamNames.AGE: ["Y25-64"],
    ParamNames.FREQ: ["A"],
    ParamNames.ISCED_11: ["TOTAL"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
}

TRAINING_LAST_YEAR_RATIO_PARAMS = {
    ParamNames.AGE: ["Y25-64"],
    ParamNames.FREQ: ["A"],
    ParamNames.SEX: ["T"],
    ParamNames.TRAINING: ["FE_NFE"],  # Formal and non-formal education and training
    ParamNames.UNIT: ["PC"],
}


__all__ = [
    'DIGITAL_SKILLS_PARAMS',
    'DROPOUT_RATIO_PARAMS',
    'EARLY_EDUCATION_RATIO_PARAMS',
    'EDUCATION_RATIO_PARAMS',
    'INACTIVE_YOUNG_RATIO_PARAMS',
    'NO_FOREIGN_LANG_KNOWN_RATIO_PARAMS',
    'PUPILS_RATIO_2012_PARAMS',
    'PUPILS_RATIO_2013_PARAMS',
    'TRAINING_LAST_MONTH_RATIO_PARAMS',
    'TRAINING_LAST_YEAR_RATIO_PARAMS',
]
