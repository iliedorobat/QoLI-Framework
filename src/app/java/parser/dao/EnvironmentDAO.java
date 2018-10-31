package app.java.parser.dao;

import app.java.parser.model.EnvironmentObject;

import java.util.ArrayList;

public interface EnvironmentDAO {
    ArrayList<EnvironmentObject> getEnvironmentList(int year);
    ArrayList<ArrayList<EnvironmentObject>> getEnvironmentLists(int start, int end);
    void printRelativeAmplitude();
}
