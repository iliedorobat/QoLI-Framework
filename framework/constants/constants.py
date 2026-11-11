import os
from pathlib import Path

# File extensions
JSON_EXTENSION = ".json"
CSV_EXTENSION = ".csv"
CSV_SEPARATOR = ";"
KEY_SEPARATOR = "_"
XLS_EXTENSION = ".xls"

# FIXME: .env
# Path configuration
# Note: Python equivalent needs to check environment variables or a config module
# For now, assuming IS_PRODUCTION and USE_TOMCAT_SERVER are defined elsewhere
# You may need to import these from an env_state module if it exists

def _get_base_path():
    """Determine the base path based on environment configuration."""
    # This assumes you have an env_state module similar to Java's EnvState
    try:
        from env_state import IS_PRODUCTION, USE_TOMCAT_SERVER
    except ImportError:
        # Fallback: assume development mode
        IS_PRODUCTION = False
        USE_TOMCAT_SERVER = False

    if IS_PRODUCTION:
        if USE_TOMCAT_SERVER:
            return os.path.join(os.sep, "opt", "tomcat", "webapps", "qoli", "WEB-INF")
        else:
            return os.path.join(os.sep, "var", "www", "life-index.eu", "server", "resources", "main")
    else:
        return os.path.join(os.getcwd(), "../fetching/", "src", "main", "resources")

BASE_PATH = _get_base_path()
PREPARED_DATASET_PATH = os.path.join(BASE_PATH, "static", "prepared")
# Get the absolute path to the project root by traversing up from this file
# This file is at: QoLI-Framework/framework/collector/constants/constants.py
CURRENT_FILE_PATH = Path(__file__).resolve()
FRAMEWORK_DIR = CURRENT_FILE_PATH.parents[2]  # Go up 3 levels to 'framework' directory

# Build the raw dataset path using absolute paths
RAW_DATASET_PATH = str(FRAMEWORK_DIR / "src" / "main" / "resources" / "static" / "raw" / "json" / "countries")

# Optional: Validate the path exists
if not os.path.exists(RAW_DATASET_PATH):
    raise RuntimeError(f"RAW_DATASET_PATH does not exist: {RAW_DATASET_PATH}")

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
