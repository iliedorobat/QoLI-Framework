package app.java.data.measurement.dao;

import java.util.Map;

public interface EnvironmentStatsDAO {
    Map<String, Number> generateDimensionList();
}
