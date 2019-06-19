package app.java.parser.local.dao;

import app.java.parser.local.model.MainActivityObject;

import java.util.ArrayList;

public interface MainActivityDAO {
    ArrayList<MainActivityObject> getMainActivityList(int year);
    ArrayList<ArrayList<MainActivityObject>> getMainActivityLists(int start, int end);
    void printRelativeAmplitude();
}
