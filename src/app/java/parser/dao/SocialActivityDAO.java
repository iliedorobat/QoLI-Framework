package app.java.parser.dao;

import app.java.parser.model.SocialActivityObject;

import java.util.ArrayList;

public interface SocialActivityDAO {
    ArrayList<SocialActivityObject> getSocialActivityList(int year);
    ArrayList<ArrayList<SocialActivityObject>> getSocialActivityLists(int start, int end);
    void printRelativeAmplitude();
}
