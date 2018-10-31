package app.java.parser.dao;

import app.java.parser.model.SafetyObject;

import java.util.ArrayList;

public interface SafetyDAO {
    ArrayList<SafetyObject> getSafetyList(int year);
    ArrayList<ArrayList<SafetyObject>> getSafetyLists(int start, int end);
    void printRelativeAmplitude();
}
