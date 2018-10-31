package app.java.parser.dao;

import app.java.parser.model.HealthObject;

import java.util.ArrayList;

public interface HealthDAO {
    ArrayList<HealthObject> getHealthList(int year);
    ArrayList<ArrayList<HealthObject>> getHealthLists(int start, int end);
    void printRelativeAmplitude();
}
