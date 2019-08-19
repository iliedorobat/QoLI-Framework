package app.java.data.measurement.dao;

import java.util.ArrayList;
import java.util.Map;

public interface SafetyStatsDAO {
    Map<String, Number> generateDimensionList();
    ArrayList<Map<String, Number>> getInitList();
}
