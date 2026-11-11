import os

from framework.constants.constants import RAW_DATASET_PATH
from framework.processing.local_parser import read_json_file, get_dimension_keys


def main():
    print("Hello from framework!")
    file_path = os.path.join(RAW_DATASET_PATH, "education", "digitalSkillsRatio.json")
    keys = get_dimension_keys(file_path)
    test = read_json_file(file_path)
    print()


if __name__ == "__main__":
    main()
