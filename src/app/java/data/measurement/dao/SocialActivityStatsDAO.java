package app.java.data.measurement.dao;

import java.util.ArrayList;
import java.util.Map;

public interface SocialActivityStatsDAO {
    /**
     * Calculate the LSA Dimension for every analyzed country and year
     * @return An ordered map with calculated LSA Dimension by countries and years
     */
    Map<String, Number> generateDimensionList();
    ArrayList<Map<String, Number>> getInitList();
}
