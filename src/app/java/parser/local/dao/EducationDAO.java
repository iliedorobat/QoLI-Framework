package app.java.parser.local.dao;

import app.java.parser.local.model.EducationObject;

import java.util.ArrayList;

public interface EducationDAO {
    ArrayList<EducationObject> getEducationList(int year);
    ArrayList<ArrayList<EducationObject>> getEducationLists(int start, int end);
    void printRelativeAmplitude();
}
