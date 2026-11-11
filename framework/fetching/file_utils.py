"""
File utilities module for handling file operations including:
- Converting XLS files to CSV
- Downloading Excel files
- Reading and writing files
- Exporting data to JSON format
"""

import os
from pathlib import Path

import requests

from framework.common.constants import XLS_EXTENSION, CSV_EXTENSION, CSV_SEPARATOR, JSON_EXTENSION


def convert_xls_to_csv(file_path: str, file_name: str, extension: str) -> None:
    """
    Convert an XLS file to CSV.

    Args:
        file_path: The target/destination path
        file_name: The name of the target/destination file
        extension: The extension of the target file
    """
    if extension != XLS_EXTENSION:
        return

    try:
        import openpyxl
    except ImportError:
        print("Error: openpyxl is required for XLS to CSV conversion.")
        print("Install it with: pip install openpyxl")
        return

    xls_full_path = os.path.join(file_path, file_name + extension)
    csv_full_path = os.path.join(file_path, file_name + CSV_EXTENSION)

    try:
        # Load the workbook
        workbook = openpyxl.load_workbook(xls_full_path)
        sheet = workbook.active

        # Write to CSV
        with open(csv_full_path, 'w', newline='', encoding='utf-8') as csv_file:
            for row_idx, row in enumerate(sheet.iter_rows(values_only=False), 1):
                cells_data = []
                for cell in row:
                    cell_value = cell.value if cell.value is not None else ""
                    cells_data.append(str(cell_value))

                csv_line = CSV_SEPARATOR.join(cells_data)
                csv_file.write(csv_line)

                if row_idx < sheet.max_row:
                    csv_file.write('\n')

        workbook.close()

    except FileNotFoundError:
        print(f"Error: The file {xls_full_path} was not found.")
    except Exception as e:
        print(f"Error converting XLS to CSV: {e}")

def download_excel_file(http_link: str, file_path: str, file_name: str, extension: str) -> None:
    """
    Download an Excel file from an HTTP URL.

    Args:
        http_link: The HTTP address
        file_path: The full access path to the destination file
        file_name: The name of the destination file
        extension: The extension of the destination file
    """
    # Create directory if it doesn't exist
    Path(file_path).mkdir(parents=True, exist_ok=True)

    full_path = os.path.join(file_path, file_name + extension)

    try:
        response = requests.get(http_link, timeout=30)
        response.raise_for_status()

        with open(full_path, 'wb') as f:
            f.write(response.content)

    except requests.exceptions.RequestException as e:
        print(f"Error downloading file from {http_link}: {e}")
    except IOError as e:
        print(f"Error writing file to {full_path}: {e}")

def read_file(path: str) -> str:
    """
    Read the data from disk.

    Args:
        path: The path to the desired file

    Returns:
        The file contents as a string
    """
    try:
        with open(path, 'r', encoding='utf-8') as f:
            lines = []
            for line in f:
                stripped_line = line.strip()
                if stripped_line:
                    lines.append(stripped_line)
            return '\n'.join(lines)

    except FileNotFoundError:
        print(f"Error: The file {path} has not been found.")
        return ""
    except IOError as e:
        print(f"Error: Error reading the file {path} from the disk. {e}")
        return ""

def write_to_file(content: str, file_path: str, file_name: str, extension: str) -> None:
    """
    Write the data to disk.

    Args:
        content: The data to be written
        file_path: The path to the desired file
        file_name: The name of the output file
        extension: The extension of the output file
    """
    # Create directory if it doesn't exist
    Path(file_path).mkdir(parents=True, exist_ok=True)

    full_path = os.path.join(file_path, file_name + extension)

    try:
        with open(full_path, 'w', encoding='utf-8') as f:
            f.write(content)
        print(f"The file {full_path} was written on the disk.")

    except IOError as e:
        print(f"Error: The file {full_path} could not be written. {e}")

def write_to_json_file(content: str, file_path: str, file_name: str) -> None:
    """
    Export the data to a JSON file.

    Args:
        content: The data to be written
        file_path: The path to the desired file
        file_name: The name of the output file
    """
    write_to_file(content, file_path, file_name, JSON_EXTENSION)