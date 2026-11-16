from framework.common.constants import DIRECTION_ROW
from framework.config.env_state import EnvState
from framework.stats.dimensions.qoli_stats import QoLIStats
from framework.stats.qoli_csv_stats import QoLICsvStats
from framework.stats.qoli_json_stats import QoLIJsonStats
from test.framework.common import DIMENSIONS

data_by_countries = QoLIStats.prepare_extended_dimensions(DIMENSIONS, None, EnvState.MIN_YEAR, EnvState.MAX_YEAR)

QoLICsvStats.write_dimensions(data_by_countries, DIRECTION_ROW, False, EnvState.MIN_YEAR, EnvState.MAX_YEAR)
QoLIJsonStats.write_dimensions(data_by_countries, False, EnvState.MIN_YEAR, EnvState.MAX_YEAR)
