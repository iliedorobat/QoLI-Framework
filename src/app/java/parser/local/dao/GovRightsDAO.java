package app.java.parser.local.dao;

import app.java.parser.local.model.GovRightsObject;

import java.util.ArrayList;

public interface GovRightsDAO {
    ArrayList<GovRightsObject> getGovRightsList(int year);
    ArrayList<ArrayList<GovRightsObject>> getGovRightsLists(int start, int end);
    void printRelativeAmplitude();
}
