package app.java.parser.dao;

import app.java.parser.model.GovRightsObject;

import java.util.ArrayList;

public interface GovRightsDAO {
    ArrayList<GovRightsObject> getGovRightsList(int year);
    ArrayList<ArrayList<GovRightsObject>> getGovRightsLists(int start, int end);
    void printRelativeAmplitude();
}
