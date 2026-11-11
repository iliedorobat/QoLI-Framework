import os

from framework.constants.constants import RAW_DATASET_PATH
from framework.processing.local_parser import read_json_file, get_dimension_keys
from framework.stats.auxiliary.auxiliary_collector import fetch_auxiliary_data
from framework.stats.education.education_collector import fetch_education_data
from framework.stats.environment.environment_collector import fetch_environment_data


def main():
    print("Hello from framework!")
    file_path = os.path.join(RAW_DATASET_PATH, "education", "digitalSkillsRatio.json")
    keys = get_dimension_keys(file_path)
    test = read_json_file(file_path)
    print()

def collect_data():
    print("Starting the data collection process...\n")

    # TODO:
    fetch_auxiliary_data()
    fetch_education_data()
    fetch_environment_data()

    print("\nThe data collection process has been successfully completed!")


if __name__ == "__main__":
    collect_data()
