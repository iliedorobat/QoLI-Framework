package app.java.parser.dao;

import app.java.parser.model.MainActivityObject;

import java.util.ArrayList;

public interface MainActivityDAO {
    ArrayList<MainActivityObject> getMainActivityList(int year);
    ArrayList<ArrayList<MainActivityObject>> getMainActivityLists(int start, int end);
    void printRelativeAmplitude();
}
