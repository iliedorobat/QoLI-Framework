package app.java.data.measurement.dao;

import java.util.ArrayList;
import java.util.Map;

public interface EducationStatsDAO {
    Map<String, Number> generateDimensionList();
    ArrayList<Map<String, Number>> getInitList();
}
