package app.java.parser.local.dao;

import app.java.parser.local.model.MaterialLifeObject;

import java.util.ArrayList;

public interface MaterialLifeDAO {
    ArrayList<MaterialLifeObject> getMaterialLifeList(int year);
    ArrayList<ArrayList<MaterialLifeObject>> getMaterialLifeLists(int start, int end);
    void printRelativeAmplitude();
}
