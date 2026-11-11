import time
from typing import Dict, List, Optional
from urllib.parse import quote

import requests

from framework.fetching.fetch_utils import consolidate_http_params, MIN_YEAR, MAX_YEAR
from framework.constants.constants import EU28_MEMBERS

# Constants
ENCODING_SCHEME = "UTF-8"
URI_PROTOCOL_NAME = "https"
URI_HOST_NAME = "ec.europa.eu/eurostat/api/dissemination"
URI_SERVICE = "statistics"
URI_SERVICE_VERSION = "1.0/data"
URI_FORMAT = "json"
URI_LANG = "en"
URI_SEPARATOR = "/"


def build_uri(dataset: str, params: Dict[str, List[str]]) -> str:
    """
    Generate the URI used to extract data.
    
    Args:
        dataset: The search path (e.g., "nama_10r_2hhinc")
        params: Parameters used in search path
    
    Returns:
        Complete URL string
    """
    path = (f"{URI_SERVICE}{URI_SEPARATOR}{URI_SERVICE_VERSION}"
            f"{URI_SEPARATOR}{dataset}")

    base_url = f"{URI_PROTOCOL_NAME}://{URI_HOST_NAME}{URI_SEPARATOR}{path}"

    # Build query parameters
    query_params = {
        "format": URI_FORMAT,
        "lang": URI_LANG,
        "sinceTimePeriod": str(MIN_YEAR),
        "untilTimePeriod": str(MAX_YEAR)
    }

    # Add additional parameters
    for key, values in params.items():
        for value in values:
            if key not in query_params:
                query_params[key] = value
            else:
                # Handle multiple values for same key
                if not isinstance(query_params[key], list):
                    query_params[key] = [query_params[key]]
                query_params[key].append(value)

    # Build URL with encoded parameters
    encoded_params = []
    for key, value in query_params.items():
        if isinstance(value, list):
            for v in value:
                encoded_params.append(f"{quote(key, safe='')}")
                encoded_params.append(f"{quote(str(v), safe='')}")
        else:
            encoded_params.append(f"{quote(key, safe='')}")
            encoded_params.append(f"{quote(str(value), safe='')}")

    query_string = "&".join([f"{encoded_params[i]}={encoded_params[i+1]}"
                             for i in range(0, len(encoded_params), 2)])

    return f"{base_url}?{query_string}"


def fetch_data(dataset: str, input_params: Dict[str, List[str]],
               country_codes: Optional[List[str]] = None) -> str:
    """
    Fetch the Eurostat data.
    
    Args:
        dataset: The search path (e.g., "nama_10r_2hhinc")
        input_params: Main parameters used in search path
        country_codes: List of country codes (uses EU28_MEMBERS if not provided)
    
    Returns:
        HTTP Response as a string
    """
    if country_codes is None:
        country_codes = EU28_MEMBERS

    params = consolidate_http_params(input_params, country_codes)
    uri = build_uri(dataset, params)

    try:
        response = requests.get(uri, timeout=30)
        response.raise_for_status()
        return response.text
    except requests.RequestException as e:
        print(f"Error fetching data: {e}")
        return ""


def fetch_satisfaction_ratio(params: Dict[str, List[str]]) -> str:
    """
    Percentage of the population rating their satisfaction as high, medium or low.
    
    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_pw05b
    Years: 2013; 2018; 2022
    
    Args:
        params: Query parameters
    
    Returns:
        HTTP Response as a string
    """
    return fetch_data("ilc_pw05b", params)


def fetch_active_people_ratio(params: Dict[str, List[str]]) -> str:
    """
    Participation in formal or informal voluntary activities or active citizenship.
    People aged 16 years or over.
    
    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_scp19
    Years: 2015; 2022
    
    Args:
        params: Query parameters
    
    Returns:
        HTTP Response as a string
    """
    return fetch_data("ilc_scp19", params)


def sleep(sleep_time: int) -> None:
    """
    Pause the thread execution.
    
    Args:
        sleep_time: The amount of milliseconds
    """
    print(f"Sleeping {sleep_time} milliseconds")
    time.sleep(sleep_time / 1000)  # Convert milliseconds to seconds


__all__ = [
    'fetch_satisfaction_ratio',
    'fetch_active_people_ratio',
    'fetch_data'
]
