import os

from framework.common.constants import RAW_DATASET_PATH
from framework.processing.local_parser import read_json_file, get_dimension_keys
from framework.stats.auxiliary.auxiliary_collector import fetch_auxiliary_data
from framework.stats.education.education_collector import fetch_education_data
from framework.stats.environment.environment_collector import fetch_environment_data
from framework.stats.gov_rights.gov_rights_collector import fetch_gov_rights_data
from framework.stats.health.health_collector import fetch_health_data
from framework.stats.leisure_interaction.leisure_interaction_collector import fetch_leisure_interaction_data
from framework.stats.main_activity.main_activity_collector import fetch_main_activity_data


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
    fetch_gov_rights_data()
    fetch_health_data()
    fetch_leisure_interaction_data()
    fetch_main_activity_data()

    print("\nThe data collection process has been successfully completed!")


if __name__ == "__main__":
    collect_data()
