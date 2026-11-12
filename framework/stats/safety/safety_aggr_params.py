from framework.common.param_units import ParamUnits

SAFETY = "safety"

CRIME_RATIO = f"{SAFETY}:crimeRatio"
NON_PAYMENT_RATIO = f"{SAFETY}:unpaidRatio"
OFFENCES_ASSAULT = f"{SAFETY}:offencesAssault"
OFFENCES_ATTEMPTED_HOMICIDE = f"{SAFETY}:offencesAttemptedHomicide"
OFFENCES_BRIBERY = f"{SAFETY}:offencesBribery"
OFFENCES_BURGLARY = f"{SAFETY}:offencesBurglary"
OFFENCES_BURGLARY_PRIVATE = f"{SAFETY}:offencesBurglaryPrivate"
OFFENCES_COMPUTERS = f"{SAFETY}:offencesComputers"
OFFENCES_CRIMINAL_GROUPS = f"{SAFETY}:offencesCriminalGroups"
OFFENCES_CORRUPTION = f"{SAFETY}:offencesCorruption"
OFFENCES_FRAUD = f"{SAFETY}:offencesFraud"
OFFENCES_HOMICIDE = f"{SAFETY}:offencesHomicide"
OFFENCES_KIDNAPPING = f"{SAFETY}:offencesKidnapping"
OFFENCES_MONEY_LAUNDERING = f"{SAFETY}:offencesMoneyLaundering"
OFFENCES_NARCOTICS = f"{SAFETY}:offencesNarcotics"
OFFENCES_RAPE = f"{SAFETY}:offencesRape"
OFFENCES_ROBBERY = f"{SAFETY}:offencesRobbery"
OFFENCES_SEXUAL_ASSAULT = f"{SAFETY}:offencesSexualAssault"
OFFENCES_SEXUAL_EXPLOITATION = f"{SAFETY}:offencesSexualExploitation"
OFFENCES_SEXUAL_VIOLENCE = f"{SAFETY}:offencesSexualViolence"
OFFENCES_THEFT = f"{SAFETY}:offencesTheft"
OFFENCES_THEFT_VEHICLE = f"{SAFETY}:offencesTheftVehicle"
OFFENCES_RATIO = f"{SAFETY}:offencesRatio"
PENSION_PPS_RATIO = f"{SAFETY}:pensionPpsRatio"
SOCIAL_PROTECTION_PPS_RATIO = f"{SAFETY}:socialProtectionPpsRatio"
UNEXPECTED_RATIO = f"{SAFETY}:unexpectedRatio"


AGGR_PARAMS_LABELS = {
    CRIME_RATIO: "Crime Ratio",
    NON_PAYMENT_RATIO: "Non Payment Ratio",
    OFFENCES_RATIO: "Total Offences Ratio",
    PENSION_PPS_RATIO: "Pension in PPS Ratio",
    SOCIAL_PROTECTION_PPS_RATIO: "Social Protection in PPS Ratio",
    UNEXPECTED_RATIO: "Unexpected Ratio",
}

AGGR_PARAMS_UNITS = {
    CRIME_RATIO: ParamUnits.PERCENT,
    NON_PAYMENT_RATIO: ParamUnits.PERCENT,
    OFFENCES_RATIO: ParamUnits.PER_HUNDRED_INHABITANTS,
    PENSION_PPS_RATIO: ParamUnits.PPS_PER_INHABITANT,
    SOCIAL_PROTECTION_PPS_RATIO: ParamUnits.PPS_PER_INHABITANT,
    UNEXPECTED_RATIO: ParamUnits.PERCENT,
}

AGGR_PARAMS = tuple(AGGR_PARAMS_LABELS)

AGGR_REVERSED_STATES = {
    CRIME_RATIO: True,
    NON_PAYMENT_RATIO: True,
    OFFENCES_RATIO: True,
    PENSION_PPS_RATIO: False,
    SOCIAL_PROTECTION_PPS_RATIO: False,
    UNEXPECTED_RATIO: True,
}

IND_PARAMS_LABELS = {
    CRIME_RATIO: "Crime Ratio",
    NON_PAYMENT_RATIO: "Non Payment Ratio",
    OFFENCES_ASSAULT: "Police-recorded Offences - Assaults",
    OFFENCES_ATTEMPTED_HOMICIDE: "Police-recorded Offences - Attempted International Homicide",
    OFFENCES_BRIBERY: "Police-recorded Offences - Bribery",
    OFFENCES_BURGLARY: "Police-recorded Offences - Burglary",
    OFFENCES_BURGLARY_PRIVATE: "Police-recorded Offences - Burglary of Private Residential Premises",
    OFFENCES_COMPUTERS: "Police-recorded Offences - Acts Against Computer Systems",
    OFFENCES_CRIMINAL_GROUPS: "Police-recorded Offences - Participation in an Organized Criminal Group",
    OFFENCES_CORRUPTION: "Police-recorded Offences - Corruption",
    OFFENCES_FRAUD: "Police-recorded Offences - Fraud",
    OFFENCES_HOMICIDE: "Police-recorded Offences - International Homicide",
    OFFENCES_KIDNAPPING: "Police-recorded Offences - Kidnapping",
    OFFENCES_MONEY_LAUNDERING: "Police-recorded Offences - Money Laundering",
    OFFENCES_NARCOTICS: "Police-recorded Offences - Narcotics",
    OFFENCES_RAPE: "Police-recorded Offences - Rape",
    OFFENCES_ROBBERY: "Police-recorded Offences - Robbery",
    OFFENCES_SEXUAL_ASSAULT: "Police-recorded Offences - Sexual Assault",
    OFFENCES_SEXUAL_EXPLOITATION: "Police-recorded Offences - Sexual Exploitation",
    OFFENCES_SEXUAL_VIOLENCE: "Police-recorded Offences - Sexual Violence",
    OFFENCES_THEFT: "Police-recorded Offences - Theft",
    OFFENCES_THEFT_VEHICLE: "Police-recorded Offences - Theft of a Motorized Vehicle or Parts of Thereof",
    PENSION_PPS_RATIO: "Pension in PPS Ratio",
    SOCIAL_PROTECTION_PPS_RATIO: "Social Protection in PPS Ratio",
    UNEXPECTED_RATIO: "Unexpected Ratio",
}

IND_PARAMS_UNITS = {
    CRIME_RATIO: ParamUnits.PERCENT,
    NON_PAYMENT_RATIO: ParamUnits.PERCENT,
    OFFENCES_ASSAULT: ParamUnits.NUMBER,
    OFFENCES_ATTEMPTED_HOMICIDE: ParamUnits.NUMBER,
    OFFENCES_BRIBERY: ParamUnits.NUMBER,
    OFFENCES_BURGLARY: ParamUnits.NUMBER,
    OFFENCES_BURGLARY_PRIVATE: ParamUnits.NUMBER,
    OFFENCES_COMPUTERS: ParamUnits.NUMBER,
    OFFENCES_CRIMINAL_GROUPS: ParamUnits.NUMBER,
    OFFENCES_CORRUPTION: ParamUnits.NUMBER,
    OFFENCES_FRAUD: ParamUnits.NUMBER,
    OFFENCES_HOMICIDE: ParamUnits.NUMBER,
    OFFENCES_KIDNAPPING: ParamUnits.NUMBER,
    OFFENCES_MONEY_LAUNDERING: ParamUnits.NUMBER,
    OFFENCES_NARCOTICS: ParamUnits.NUMBER,
    OFFENCES_RAPE: ParamUnits.NUMBER,
    OFFENCES_ROBBERY: ParamUnits.NUMBER,
    OFFENCES_SEXUAL_ASSAULT: ParamUnits.NUMBER,
    OFFENCES_SEXUAL_EXPLOITATION: ParamUnits.NUMBER,
    OFFENCES_SEXUAL_VIOLENCE: ParamUnits.NUMBER,
    OFFENCES_THEFT: ParamUnits.NUMBER,
    OFFENCES_THEFT_VEHICLE: ParamUnits.NUMBER,
    PENSION_PPS_RATIO: ParamUnits.PPS_PER_INHABITANT,
    SOCIAL_PROTECTION_PPS_RATIO: ParamUnits.PPS_PER_INHABITANT,
    UNEXPECTED_RATIO: ParamUnits.PERCENT,
}

IND_PARAMS = tuple(IND_PARAMS_LABELS)

IND_REVERSED_STATES = {
    CRIME_RATIO: True,
    NON_PAYMENT_RATIO: True,
    OFFENCES_ASSAULT: True,
    OFFENCES_ATTEMPTED_HOMICIDE: True,
    OFFENCES_BRIBERY: True,
    OFFENCES_BURGLARY: True,
    OFFENCES_BURGLARY_PRIVATE: True,
    OFFENCES_COMPUTERS: True,
    OFFENCES_CRIMINAL_GROUPS: True,
    OFFENCES_CORRUPTION: True,
    OFFENCES_FRAUD: True,
    OFFENCES_HOMICIDE: True,
    OFFENCES_KIDNAPPING: True,
    OFFENCES_MONEY_LAUNDERING: True,
    OFFENCES_NARCOTICS: True,
    OFFENCES_RAPE: True,
    OFFENCES_ROBBERY: True,
    OFFENCES_SEXUAL_ASSAULT: True,
    OFFENCES_SEXUAL_EXPLOITATION: True,
    OFFENCES_SEXUAL_VIOLENCE: True,
    OFFENCES_THEFT: True,
    OFFENCES_THEFT_VEHICLE: True,
    PENSION_PPS_RATIO: False,
    SOCIAL_PROTECTION_PPS_RATIO: False,
    UNEXPECTED_RATIO: True,
}


__all__ = [
    "SAFETY",
    "CRIME_RATIO",
    "NON_PAYMENT_RATIO",
    "OFFENCES_ASSAULT",
    "OFFENCES_ATTEMPTED_HOMICIDE",
    "OFFENCES_BRIBERY",
    "OFFENCES_BURGLARY",
    "OFFENCES_BURGLARY_PRIVATE",
    "OFFENCES_COMPUTERS",
    "OFFENCES_CRIMINAL_GROUPS",
    "OFFENCES_CORRUPTION",
    "OFFENCES_FRAUD",
    "OFFENCES_HOMICIDE",
    "OFFENCES_KIDNAPPING",
    "OFFENCES_MONEY_LAUNDERING",
    "OFFENCES_NARCOTICS",
    "OFFENCES_RAPE",
    "OFFENCES_ROBBERY",
    "OFFENCES_SEXUAL_ASSAULT",
    "OFFENCES_SEXUAL_EXPLOITATION",
    "OFFENCES_SEXUAL_VIOLENCE",
    "OFFENCES_THEFT",
    "OFFENCES_THEFT_VEHICLE",
    "OFFENCES_RATIO",
    "PENSION_PPS_RATIO",
    "SOCIAL_PROTECTION_PPS_RATIO",
    "UNEXPECTED_RATIO",
    "AGGR_PARAMS_LABELS",
    "AGGR_PARAMS_UNITS",
    "AGGR_PARAMS",
    "AGGR_REVERSED_STATES",
    "IND_PARAMS_LABELS",
    "IND_PARAMS_UNITS",
    "IND_PARAMS",
    "IND_REVERSED_STATES",
]
