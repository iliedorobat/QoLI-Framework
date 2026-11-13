"""
Parse JSON-stat v2 datasets from local files.
"""

import json
from itertools import product
from typing import Any
from typing import Union

from pyjstat import pyjstat


def read_json_file(file_path: str) -> pyjstat.Dataset:
    """
    Read data from disk.

    Args:
        file_path: The full access path to the desired file

    Returns:
        JSON-stat v2 dataset
    """
    json_data = parse_json(file_path)
    dataset = pyjstat.Dataset.read(json.dumps(json_data))

    return dataset


def dataset_to_map(dataset: pyjstat.Dataset) -> dict[tuple, float]:
    """
    Convert a JSON-stat v2 dataset to a map format.

    The keys are the dimensions and values their associated values.

    Args:
        dataset: The pyjstat Dataset object

    Returns:
        Dictionary mapping dimension tuples to numeric values
    """
    dimension_ids = dataset["id"]

    indexes = [
        list(dataset["dimension"][dim]["category"]["index"])
        for dim in dimension_ids
    ]

    values = dataset["value"]
    result = {}

    for i, categories in enumerate(product(*indexes)):
        value = (
            values.get(str(i))
            if isinstance(values, dict)
            else values[i]
        )

        if value is not None:
            result[categories] = value

    return result


def print_json_entries(entries_map: dict[tuple, Union[float, str]]) -> None:
    """
    Print JSON entries to console.

    Args:
        entries_map: Dictionary mapping dimension tuples to numeric values
    """
    for key, value in entries_map.items():
        print(f"{key}: {value}")


def parse_json(file_path: str) -> dict[str, Any]:
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
        with open(file_path, "r", encoding="utf-8") as file:
            content = file.read()
        return json.loads(content)
    except json.JSONDecodeError as e:
        raise RuntimeError(f"JSON parsing error: {e}")
    except FileNotFoundError as e:
        raise RuntimeError(f"File not found: {file_path}")
    except StopIteration:
        raise RuntimeError("No dataset found in JSON file")
    except Exception as e:
        raise RuntimeError(f"Error reading dataset: {e}")


__all__ = [
    "dataset_to_map",
    "parse_json",
    "print_json_entries",
    "read_json_file",
]
