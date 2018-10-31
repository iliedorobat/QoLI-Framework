package app.java.parser.dao;

import app.java.parser.model.MaterialLifeObject;

import java.util.ArrayList;

public interface MaterialLifeDAO {
    ArrayList<MaterialLifeObject> getMaterialLifeList(int year);
    ArrayList<ArrayList<MaterialLifeObject>> getMaterialLifeLists(int start, int end);
    void printRelativeAmplitude();
}
