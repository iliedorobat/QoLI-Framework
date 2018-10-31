package app.java;

import app.java.index.dao.LifeIndexDAO;
import app.java.index.dao.impl.LifeIndexDAOImpl;

public class Main {
    public static void main(String[] args) {
        LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();
        lifeIndexDAO.printLifeIndex();
//        lifeIndexDAO.printRelativeAmplitude();
    }
}
