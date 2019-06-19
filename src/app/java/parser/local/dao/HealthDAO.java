package app.java.parser.local.dao;

import app.java.parser.local.model.HealthObject;

import java.util.ArrayList;

public interface HealthDAO {
    ArrayList<HealthObject> getHealthList(int year);
    ArrayList<ArrayList<HealthObject>> getHealthLists(int start, int end);
    void printRelativeAmplitude();
}
