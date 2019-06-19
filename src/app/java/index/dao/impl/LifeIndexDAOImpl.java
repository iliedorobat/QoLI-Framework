package app.java.index.dao.impl;

import app.java.commons.Constants;
import app.java.commons.MathUtils;
import app.java.commons.Utils;
import app.java.index.dao.LifeIndexDAO;
import app.java.parser.local.dao.*;
import app.java.parser.local.dao.impl.*;
import app.java.relation.dao.*;
import app.java.relation.dao.impl.*;

import java.text.DecimalFormat;

public class LifeIndexDAOImpl implements LifeIndexDAO {
    private static DecimalFormat df2 = new DecimalFormat(".##");
    private static MathUtils math = new MathUtils();
    private static Utils utils = new Utils();

    public double getLifeIndex(String region, short year) {
        EnvironmentRelDAO environmentRelDAO = new EnvironmentRelDAOImpl();
        EducationRelDAO educationRelDAO = new EducationRelDAOImpl();
        GovRightsRelDAO govRightsRelDAO = new GovRightsRelDAOImpl();
        HealthRelDAO healthRelDAO = new HealthRelDAOImpl();
        MainActivityRelDAO mainActivityRelDAO = new MainActivityRelDAOImpl();
        MaterialLifeRelDAO materialLifeRelDAO = new MaterialLifeRelDAOImpl();
        SafetyRelDAO safetyRelDAO = new SafetyRelDAOImpl();
        SocialActivityRelDAO socialActivityRelDAO = new SocialActivityRelDAOImpl();

        double educationRate = educationRelDAO.getEducationRate(region, year);
        double environmentRate = environmentRelDAO.getEnvironmentRate(region, year);
        double govRightsRate = govRightsRelDAO.getGovRightsRate(region, year);
        double healthRate = healthRelDAO.getHealthRate(region, year);
        double mainActivityRate = mainActivityRelDAO.getMainActivityRate(region, year);
        double materialLifeRate = materialLifeRelDAO.getMaterialLifeRate(region, year);
        double safetyRate = safetyRelDAO.getSafetyRate(region, year);
        double socialActivityRate = socialActivityRelDAO.getSocialActivityRate(region, year);

//        if (region.equals("Regiunea NORD-EST") || region.equals("Regiunea VEST")) {
//            System.out.println(year + " " + region + " edu " + educationRate);
//            System.out.println(year + " " + region + " env " + environmentRate);
//            System.out.println(year + " " + region + " gov " + govRightsRate);
//            System.out.println(year + " " + region + " health " + healthRate);
//            System.out.println(year + " " + region + " main " + mainActivityRate);
//            System.out.println(year + " " + region + " material " + materialLifeRate);
//            System.out.println(year + " " + region + " safety " + safetyRate);
//            System.out.println(year + " " + region + " social " + socialActivityRate);
//        }
//
//        System.out.println("HEALTH|" + year + "|" + region + "|" + healthRate);

        double index = educationRate * environmentRate * govRightsRate * healthRate
                * mainActivityRate * materialLifeRate * safetyRate * socialActivityRate;

        return math.getSquareValue(index, 8);
    }

    public void printLifeIndexByRegion(String region) {
        System.out.println(region);
        for (short year = Constants.MIN_YEAR; year <= Constants.MAX_YEAR; year++) {
            double lifeIndex = getLifeIndex(region, year);

            if (Constants.PRINT_DIMENSION_VALUE)
                System.out.println(year + "\t" + lifeIndex);
        }
        System.out.println("------------------------------------");
    }

    public void printLifeIndexByYear(short year) {
        System.out.println(year);
        String[] regions = utils.getRegions();
        for (short i = 0; i < regions.length; i++) {
            String region = regions[i];
            double lifeIndex = getLifeIndex(region, year);

            if (Constants.PRINT_DIMENSION_VALUE)
                System.out.println(region + "\t" + lifeIndex);
        }
        System.out.println("------------------------------------");
    }

    public void printLifeIndex() {
        System.out.println("Regiunea, Anul, Quality of Life Index");
        String[] regions = utils.getRegions();
        for (short i = 0; i < regions.length; i++) {
            String region = regions[i];
            for (short year = Constants.MIN_YEAR; year <= Constants.MAX_YEAR; year++) {
                double lifeIndex = getLifeIndex(region, year);

                if (Constants.PRINT_DIMENSION_VALUE)
                    System.out.println(region + ", " + year + ", " + df2.format(lifeIndex) + "\n");
            }
        }
    }

    public void printDimensionInfo(int year, String region, Constants.DIMENSION dimension, Constants.TYPES type) {
        if (Constants.PRINT_DIMENSION_IFO)
            System.out.println(dimension);
    }

    public void printDimensionMissing(int year, String region, Constants.DIMENSION dimension, Constants.TYPES type) {
        if (Constants.PRINT_DIMENSION_MISSING)
            System.err.println(dimension);
    }

    public void printVariation(String region, Constants.TYPES type, double amplitude, double avg) {
        if (Constants.PRINT_AMPLITUDE) {
            boolean isSignificantVariation = (amplitude - avg) > (avg + Constants.VARIATION_THRESHOLD) ? true : false;
            System.out.println(type + ": " + isSignificantVariation + "     " + region);
        }
    }

    public void printRelativeAmplitude() {
        EducationDAO educationDAO = new EducationDAOImpl();
        EnvironmentDAO environmentDAO = new EnvironmentDAOImpl();
        GovRightsDAO govRightsDAO = new GovRightsDAOImpl();
        HealthDAO healthDAO = new HealthDAOImpl();
        MainActivityDAO mainActivityDAO = new MainActivityDAOImpl();
        MaterialLifeDAO materialLifeDAO = new MaterialLifeDAOImpl();
        SafetyDAO safetyDAO = new SafetyDAOImpl();
        SocialActivityDAO socialActivityDAO = new SocialActivityDAOImpl();

        educationDAO.printRelativeAmplitude();
        environmentDAO.printRelativeAmplitude();
        govRightsDAO.printRelativeAmplitude();
        healthDAO.printRelativeAmplitude();
        mainActivityDAO.printRelativeAmplitude();
        materialLifeDAO.printRelativeAmplitude();
        safetyDAO.printRelativeAmplitude();
        socialActivityDAO.printRelativeAmplitude();
    }
}
