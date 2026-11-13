"""
Parse JSON-stat v2 datasets from local files.
"""

import json
from typing import Dict, List, Any, Union

from pyjstat import pyjstat


def read_json_file(file_path: str) -> Dict[tuple, Union[float, str]]:
    """
    Read data from disk and convert to map format.

    Args:
        file_path: The full access path to the desired file

    Returns:
        JSON-stat v2 dataset as a dictionary with tuple keys
    """
    from framework.stats.education.education_paths import DROPOUT_RATIO_PATH
    if file_path == DROPOUT_RATIO_PATH:
        print()
    dataset = get_dataset(file_path)
    test = pyjstat.Dataset.read(file_path)
    return as_map(dataset)


def as_map(dataset: Dict[str, Any]) -> Dict[tuple, Union[float, str]]:
    """
    Convert a JSON-stat v2 dataset to a map format.

    The keys are the dimensions and values their associated values.

    Args:
        dataset: The dataset dictionary from JSON-stat v2 format

    Returns:
        Dictionary mapping dimension tuples to numeric values
    """
    result = {}

    if 'value' not in dataset or 'dimension' not in dataset:
        return result

    values = dataset['value']
    dimensions = dataset['dimension']
    dim_ids = dataset['id']

    # Build the result map
    for idx, key in enumerate(values):
        value = values[key]
        if value is None:
            continue

        # Create the key tuple by getting the dimension value for this index
        key_parts = []
        for dim_id in dim_ids:
            dim_size = dataset['size'][dim_ids.index(dim_id)]
            # Calculate which dimension value applies to this index
            dim_index = (idx // (1 if dim_ids.index(dim_id) == len(dim_ids) - 1
                                else sum(dataset['size'][dim_ids.index(dim_id) + 1:]))) % dim_size

            dim_data = dimensions.get(dim_id, {})
            category = dim_data.get('category', {})
            index_map = category.get('index', {})

            # Find the dimension value for this index
            if isinstance(index_map, dict):
                for key, idx_val in index_map.items():
                    if idx_val == dim_index:
                        key_parts.append(key)
                        break
            elif isinstance(index_map, list):
                if dim_index < len(index_map):
                    key_parts.append(index_map[dim_index])

        if key_parts:
            result[tuple(key_parts)] = value

    return result


def print_json_entries(entries_map: Dict[tuple, Union[float, str]]) -> None:
    """
    Print JSON entries to console.

    Args:
        entries_map: Dictionary mapping dimension tuples to numeric values
    """
    for key, value in entries_map.items():
        print(f"{key}: {value}")


def get_dimension_keys(file_path: str) -> List[str]:
    """
    Extract dimension keys from a dataset.

    Args:
        file_path: The full access path to the desired file

    Returns:
        List of dimension key names
    """
    dataset = get_dataset(file_path)
    dimensions = dataset.get('dimension', {})
    dim_ids = dataset.get('id', [])
    return list(dim_ids) if isinstance(dim_ids, list) else list(dimensions.keys())


def get_dataset(file_path: str) -> Dict[str, Any]:
    """
    Parse a JSON-stat v2 file and return the dataset.

    Args:
        file_path: The full access path to the desired file

    Returns:
        Parsed dataset dictionary

    Raises:
        RuntimeError: If JSON parsing fails
    """
    try:
        # Read file content
        with open(file_path, 'r', encoding='utf-8') as file:
            content = file.read()

        # Parse JSON
        return json.loads(content)

    except json.JSONDecodeError as e:
        raise RuntimeError(f"JSON parsing error: {e}")
    except FileNotFoundError as e:
        raise RuntimeError(f"File not found: {file_path}")
    except StopIteration:
        raise RuntimeError("No dataset found in JSON file")
    except Exception as e:
        raise RuntimeError(f"Error reading dataset: {e}")
