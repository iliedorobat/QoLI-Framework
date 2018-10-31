package app.java.parser.dao;

import app.java.parser.model.EducationObject;

import java.util.ArrayList;

public interface EducationDAO {
    ArrayList<EducationObject> getEducationList(int year);
    ArrayList<ArrayList<EducationObject>> getEducationLists(int start, int end);
    void printRelativeAmplitude();
}
