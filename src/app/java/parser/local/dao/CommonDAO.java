package app.java.parser.local.dao;

import app.java.parser.local.model.CommonObject;

import java.util.ArrayList;

public interface CommonDAO {
    ArrayList<CommonObject> getCommonList(int year);
    ArrayList<ArrayList<CommonObject>> getCommonLists(int start, int end);
}
