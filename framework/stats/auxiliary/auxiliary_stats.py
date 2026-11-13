from framework.processing.data_initialization import Initializer
from framework.processing.data_preparation import Preparation
from framework.stats.auxiliary.auxiliary_params import POPULATION_PARAMS
from framework.stats.auxiliary.auxiliary_paths import POPULATION_PATH, POPULATION_FILE_NAME


class AuxiliaryStats:
    # Initialize population data from consolidated map
    init_population = Initializer.init_consolidated_map(
        POPULATION_PARAMS,
        POPULATION_PATH
    )

    # Prepare the population data for analysis
    population = Preparation.prepare_data(init_population)

    # Raw indicators stored in a sorted dictionary
    raw_indicators = dict(sorted({
        POPULATION_FILE_NAME: init_population,
    }.items()))

    # Aggregated indicators stored in a regular dictionary
    aggr_indicators = {
        POPULATION_FILE_NAME: population
    }
