from framework.common.constants import SERIES_TYPE_COUNTRY, DIRECTION_ROW
from framework.stats.qoli_log import log_aggr_indicators
from test.framework.common import DIMENSIONS

ind_list = ["education:digitalSkillsRatio", "education:dropoutRatio"]
log_aggr_indicators(DIMENSIONS, ind_list, SERIES_TYPE_COUNTRY, DIRECTION_ROW)
