package app.java.index.dao;

import app.java.commons.Constants;

public interface LifeIndexDAO {
    double getLifeIndex(String region, short year);
    void printLifeIndexByRegion(String region);
    void printLifeIndexByYear(short year);
    void printLifeIndex();
    void printDimensionInfo(int year, String region, Constants.DIMENSION dimension, Constants.TYPES type);
    void printDimensionMissing(int year, String region, Constants.DIMENSION dimension, Constants.TYPES type);
    void printVariation(String region, Constants.TYPES type, double amplitude, double avg);
    void printRelativeAmplitude();
}
