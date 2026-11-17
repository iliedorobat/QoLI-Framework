# Minimum value for percentage safety threshold
PERCENTAGE_MIN_VALUE = -100

# EU member states by region
EU_EASTERN_MEMBERS = ["BG", "CZ", "HU", "PL", "RO", "SK"]
EU_NORTHERN_MEMBERS = ["DK", "EE", "FI", "IE", "LV", "LT", "SE", "UK"]
EU_SOUTHERN_MEMBERS = ["HR", "EL", "IT", "MT", "PT", "SI", "ES", "CY"]
EU_WESTERN_MEMBERS = ["AT", "BE", "FR", "DE", "LU", "NL"]

# EU28 member states
EU28_MEMBERS = [
    "AT",  # Austria
    "BE",  # Belgium
    "BG",  # Bulgaria
    "CY",  # Cyprus
    "CZ",  # Czechia
    "DE",  # Germany (until 1990 former territory of the FRG)
    "DK",  # Denmark
    "EE",  # Estonia
    "EL",  # Greece
    "ES",  # Spain
    "FI",  # Finland
    "FR",  # France
    "HR",  # Croatia
    "HU",  # Hungary
    "IE",  # Ireland
    "IT",  # Italy
    "LT",  # Lithuania
    "LU",  # Luxembourg
    "LV",  # Latvia
    "MT",  # Malta
    "NL",  # Netherlands
    "PL",  # Poland
    "PT",  # Portugal
    "RO",  # Romania
    "SE",  # Sweden
    "SI",  # Slovenia
    "SK",  # Slovakia
    "UK"   # United Kingdom
]

# EU28 member states with full names
EU28_MEMBERS_NAME = {
    "AT": "Austria",
    "BE": "Belgium",
    "BG": "Bulgaria",
    "CY": "Cyprus",
    "CZ": "Czech Republic",
    "DE": "Germany",
    "DK": "Denmark",
    "EE": "Estonia",
    "EL": "Greece",
    "ES": "Spain",
    "FI": "Finland",
    "FR": "France",
    "HR": "Croatia",
    "HU": "Hungary",
    "IE": "Ireland",
    "IT": "Italy",
    "LT": "Lithuania",
    "LU": "Luxembourg",
    "LV": "Latvia",
    "MT": "Malta",
    "NL": "Netherlands",
    "PL": "Poland",
    "PT": "Portugal",
    "RO": "Romania",
    "SE": "Sweden",
    "SI": "Slovenia",
    "SK": "Slovakia",
    "UK": "United Kingdom",
}

# Non-EU member states and territories
NON_EU_MEMBERS_NAME = {
    "AL": "Albania",
    "AD": "Andorra",
    "AM": "Armenia",
    "AZ": "Azerbaijan",
    "BA": "Bosnia and Herzegovina",
    "BY": "Belarus",
    "CH": "Switzerland",
    "FO": "Faroe Islands",
    "GE": "Georgia",
    "IS": "Iceland",
    "KZ": "Kazakhstan",
    "LI": "Liechtenstein",
    "MC": "Monaco",
    "MD": "Moldova",
    "ME": "Montenegro",
    "MK": "North Macedonia",
    "NO": "Norway",
    "RU": "Russian Federation",
    "SM": "San Marino",
    "RS": "Serbia",
    "TR": "Türkiye",
    "UA": "Ukraine",
    "VA": "Vatican City",
}

# EU28 member states extended (includes UK subdivisions)
EU28_MEMBERS_EXTENDED = [
    "AT",      # Austria
    "BE",      # Belgium
    "BG",      # Bulgaria
    "CY",      # Cyprus
    "CZ",      # Czechia
    "DE",      # Germany (until 1990 former territory of the FRG)
    "DK",      # Denmark
    "EE",      # Estonia
    "EL",      # Greece
    "ES",      # Spain
    "FI",      # Finland
    "FR",      # France
    "HR",      # Croatia
    "HU",      # Hungary
    "IE",      # Ireland
    "IT",      # Italy
    "LT",      # Lithuania
    "LU",      # Luxembourg
    "LV",      # Latvia
    "MT",      # Malta
    "NL",      # Netherlands
    "PL",      # Poland
    "PT",      # Portugal
    "RO",      # Romania
    "SE",      # Sweden
    "SI",      # Slovenia
    "SK",      # Slovakia
    "UKC-L",   # England and Wales
    "UKM",     # Scotland
    "UKN"      # Northern Ireland (UK)
]

# EU regions
EU28_REGIONS = [
    "EU_EASTERN",
    "EU_NORTHERN",
    "EU_SOUTHERN",
    "EU_WESTERN"
]

# Series and direction types
SERIES_TYPE_COUNTRY = "COUNTRY"
SERIES_TYPE_REGION = "REGION"
DIRECTION_COLUMN = "COLUMN"
DIRECTION_ROW = "ROW"


__all__ = [
    "DIRECTION_COLUMN",
    "DIRECTION_ROW",
    "EU28_MEMBERS",
    "EU28_MEMBERS_EXTENDED",
    "EU28_MEMBERS_NAME",
    "EU28_REGIONS",
    "EU_EASTERN_MEMBERS",
    "EU_NORTHERN_MEMBERS",
    "EU_SOUTHERN_MEMBERS",
    "EU_WESTERN_MEMBERS",
    "NON_EU_MEMBERS_NAME",
    "PERCENTAGE_MIN_VALUE",
    "SERIES_TYPE_COUNTRY",
    "SERIES_TYPE_REGION",
]
