from datetime import date
from typing import Dict, List, Union


# FIXME: .env
# These would need to be imported from your environment/config modules
# from config import EnvState, Constants
# For now, using placeholder values
IS_TESTING = False
MIN_YEAR = 2004
# TODO: check
MAX_YEAR = date.today().year - 1
INIT_MAP_MIN_YEAR = MIN_YEAR
INIT_MAP_MAX_YEAR = MAX_YEAR

# Constants for parameter names
class ParamsNames:
    LANG = "lang"
    GEO = "geo"
    TIME = "time"


def add_params(params: Dict[str, List[str]], property_name: str,
               values: Union[List[str], tuple]) -> None:
    """
    Add new parameters to the params dictionary.

    Args:
        params: Dictionary of parameters
        property_name: The name of the property to add
        values: List or tuple of values to add
    """
    if property_name not in params:
        params[property_name] = []

    for value in values:
        if value not in params[property_name]:
            params[property_name].append(value)


def add_params_from_dict(params: Dict[str, List[str]], property_name: str,
                         values_map: Dict[str, str]) -> None:
    """
    Add new parameters to the params dictionary from a dictionary's values.

    Args:
        params: Dictionary of parameters
        property_name: The name of the property to add
        values_map: Dictionary containing target properties (values will be extracted)
    """
    values = list(values_map.values())
    add_params(params, property_name, values)


def filter_params_keys(params: Dict[str, List[str]]) -> List[str]:
    """
    Filter the parameter keys and return a list of unique keys.

    Removes "lang" and "geo" from the returned keys.

    Args:
        params: Dictionary of parameters

    Returns:
        List of unique keys excluding "lang" and "geo"
    """
    filtered_keys = [key for key in params.keys()
                     if key not in [ParamsNames.LANG, ParamsNames.GEO]]
    return filtered_keys


def prepare_main_http_params(countries: List[str]) -> Dict[str, List[str]]:
    """
    Prepare the main params ('geo' for production and 'geo' & 'time' for testing).

    Args:
        countries: List of country codes

    Returns:
        Dict containing the main params
    """
    params = {ParamsNames.LANG: ["en"]}

    if IS_TESTING:
        params[ParamsNames.GEO] = ["RO"]
        params[ParamsNames.TIME] = ["2015"]
    else:
        add_params(params, ParamsNames.GEO, countries)

    return params


def consolidate_http_params(indicator_params: Dict[str, List[str]], country_codes: List[str]) -> Dict[str, List[str]]:
    """
    Consolidate the main parameters and the query parameters specific to the analyzed indicator.

    Args:
        indicator_params: Map containing query parameters specific to the analyzed indicator
        country_codes: List of country codes

    Returns:
        Dict containing both main and indicator-specific query parameters
    """
    http_params = prepare_main_http_params(country_codes)
    http_params.update(indicator_params)
    return http_params


__all__ = [
    'add_params',
    'add_params_from_dict',
    'consolidate_http_params',
    'filter_params_keys',
    'ParamsNames',
    'MIN_YEAR',
    'MAX_YEAR',
    'INIT_MAP_MIN_YEAR',
    'INIT_MAP_MAX_YEAR'
]
