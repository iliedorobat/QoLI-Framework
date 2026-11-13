from framework.stats.dimensions.auxiliary.auxiliary_collector import fetch_auxiliary_data
from framework.stats.dimensions.education.education_collector import fetch_education_data
from framework.stats.dimensions.environment.environment_collector import fetch_environment_data
from framework.stats.dimensions.gov_rights.gov_rights_collector import fetch_gov_rights_data
from framework.stats.dimensions.health.health_collector import fetch_health_data
from framework.stats.dimensions.leisure_interaction.leisure_interaction_collector import fetch_leisure_interaction_data
from framework.stats.dimensions.main_activity.main_activity_collector import fetch_main_activity_data
from framework.stats.dimensions.material_living.material_living_collector import fetch_material_living_data
from framework.stats.dimensions.overall_experience.overall_experience_collector import fetch_overall_experience_data
from framework.stats.dimensions.safety.safety_collector import fetch_safety_data


def collect_data():
    print("Starting the data collection process...\n")

    fetch_auxiliary_data()
    fetch_education_data()
    fetch_environment_data()
    fetch_gov_rights_data()
    fetch_health_data()
    fetch_leisure_interaction_data()
    fetch_main_activity_data()
    fetch_material_living_data()
    fetch_overall_experience_data()
    fetch_safety_data()

    print("\nThe data collection process has been successfully completed!")


if __name__ == "__main__":
    collect_data()
