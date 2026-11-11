"""
Parameter values mapping for QoLI Framework.
Contains enums that map friendly names to their corresponding codes.
"""


class ACL00:
    """Civic and leisure activities"""
    CITIZENSHIP = "AC43A"      # Active citizenship
    FORMAL = "AC41A"           # Formal voluntary activities
    INFORMAL = "AC42A"         # Informal voluntary activities
    CINEMA = "AC521"
    CULTURE = "AC523H"
    LIVE = "AC522A"
    SPORTS = "AC525"


class AIR_POL:
    """Air pollutants"""
    AMMONIA = "NH3"                        # Ammonia
    CARBON_MONOXIDE = "CO"                 # Carbon monoxide (Ozone precursor)
    METHANE = "CH4"                        # Methane (Ozone precursor)
    NON_METHANE = "NMVOC"                  # Non-methane volatile organic compounds
    NITROGEN_OXIDES = "NOX"                # Nitrogen oxides (Ozone precursor)
    PM2_5 = "PM2_5"
    PM10 = "PM10"


class DOMAIN:
    """Domain categories"""
    LEGAL = "LEG"
    OTHER = "OTH"
    POLICE = "POLC"
    POLITIC = "POLIT"


class HAPPINESS_LEVELS:
    """Happiness levels"""
    ALWAYS = "ALW"          # Always
    MOST = "MOST"           # Most of the time
    NEVER = "NVR"           # Never
    RARELY = "RAR"          # Rarely
    SOMETIMES = "SMT"       # Sometimes
    UNKNOWN = "UNK"         # Unknown


class HEALTH_PROBLEMS:
    """Health problems"""
    DEPRESSIVE = "DPR"
    MAJOR_DEPRESSIVE = "DPR_MJR"
    OTHER_DEPRESSIVE = "DPR_OTH"


class ICCS:
    """ICCS crime classifications"""
    ASSAULT = "ICCS020111"                  # Serious Assault
    ATTEMPTED_HOMICIDE = "ICCS0102"         # Attempted intentional homicide
    BRIBERY = "ICCS07031"                   # Bribery
    BURGLARY = "ICCS0501"                   # Burglary
    BURGLARY_PRIVATE = "ICCS05012"          # Burglary of private residential premises
    COMPUTERS = "ICCS0903"                  # Acts against computer systems
    CRIMINAL_GROUPS = "ICCS09051"           # Participation in organized criminal group
    CORRUPTION = "ICCS0703"                 # Corruption
    FRAUD = "ICCS0701"                      # Fraud
    HOMICIDE = "ICCS0101"                   # Intentional homicide
    KIDNAPPING = "ICCS020221"               # Kidnapping
    MONEY_LAUNDERING = "ICCS07041"          # Money Laundering
    NARCOTICS = "ICCS0601"                  # Unlawful acts involving controlled drugs
    RAPE = "ICCS03011"                      # Rape
    ROBBERY = "ICCS0401"                    # Robbery
    SEXUAL_ASSAULT = "ICCS03012"            # Sexual assault
    SEXUAL_VIOLENCE = "ICCS0301"            # Sexual violence
    SEXUAL_EXPLOITATION = "ICCS0302"        # Sexual exploitation
    THEFT = "ICCS0502"                      # Theft
    THEFT_VEHICLE = "ICCS05021"             # Theft of a motorized land vehicle


class ISCED_11:
    """ISCED-11 education levels"""
    ED_0_2 = "ED0-2"                        # Less than primary, primary and lower secondary (0-2)
    ED_3_8 = "ED3-8"                        # Upper secondary, post-secondary and tertiary (3-8)
    ED_3_4 = "ED3_4"                        # Upper secondary and post-secondary (3-4)
    ED_3_4_GENERAL = "ED3_4GEN"             # Upper secondary and post-secondary - general
    ED_3_4_VOCATIONAL = "ED3_4VOC"          # Upper secondary and post-secondary - vocational
    ED_5_8 = "ED5-8"                        # Tertiary education (5-8)


class MED_SPEC:
    """Medical specialties"""
    CARING_PERSONAL = "PER_CARE"
    DENTISTS = "DENT"
    DOCTORS = "PHYS"
    MIDWIVES = "MWS"
    NURSES = "NRS"
    PHARMACISTS = "PHARM"
    PHYSIOTHERAPISTS = "PHYSIO"


class PERS_CAT:
    """Personal categories"""
    FAMILY = "FAM_REL"
    FRIENDS = "FRD"


class SATISFACTION_LEVELS:
    """Satisfaction levels"""
    HIGH = "HIGH"
    LOW = "LOW"
    MEDIUM = "MED"


class SATISFACTION_TYPES:
    """Satisfaction types"""
    ACCOMMODATION = "ACCOM"
    COMMUTING_TIME = "COM"
    FINANCIAL = "FIN"
    GREEN_AREAS = "REC_GA"
    JOB = "JOB"
    ENVIRONMENT = "LIVENV"
    MEANING_OF_LIFE = "MEANLIFE"
    RELATIONSHIPS = "PER_RELS"
    TIME_SPENT = "TIME"


class SOCIAL_ACTIVITIES_NP_REASON:
    """Social activities non-participation reasons"""
    AWAY = "NACT_NB"            # None in the neighbourhood
    FINANCIAL = "FIN"           # Financial reasons
    OTHER = "OTH"               # Other
    UNINTERESTED = "NINT"       # No interest


class VOLUNTARY_ACTIVITIES_NP_REASON:
    """Voluntary activities non-participation reasons"""
    TIME = "NTIME"              # Time
    NO_INTEREST = "NINT"        # No interest
    OTHER = "OTH"               # Other


class SEX:
    """Gender categories"""
    FEMALE = "F"
    MALE = "M"
    TOTAL = "T"


class LEV_DIFF:
    """Level of difficulty making ends meet"""
    GREAT_DIFFICULTY = "GRT"        # Households making ends meet with great difficulty
    DIFFICULTY = "DIF"              # Households making ends meet with difficulty
    SOME_DIFFICULTY = "SOME"        # Households making ends meet with some difficulty
    FAIRLY_EASILY = "FEASY"         # Households making ends meet fairly easily
    EASILY = "EASY"                 # Households making ends meet easily
    VERY_EASILY = "VEASY"           # Households making ends meet very easily


class WORKING_FLEXIBILITY:
    """Working flexibility categories"""
    NO_RESPONSE = "NRP"                     # No response
    EMPLOYER_DECISION = "EMPL_MAIN"         # Employer or organisation mainly decides
    PERSON_DECISION = "PER_FUL"             # Person can fully decide
    RESTRICTIVE_DECISION = "PER_RSTR"       # Person can decide with restrictions
    TOTAL = "TOTAL"                         # Total
