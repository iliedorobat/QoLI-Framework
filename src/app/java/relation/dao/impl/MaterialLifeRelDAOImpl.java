package app.java.relation.dao.impl;

import app.java.commons.Constants;
import app.java.commons.Utils;
import app.java.index.dao.LifeIndexDAO;
import app.java.parser.local.model.MaterialLifeObject;
import app.java.relation.dao.CommonRelDAO;
import app.java.relation.dao.MaterialLifeRelDAO;
import app.java.index.dao.impl.LifeIndexDAOImpl;
import app.java.parser.local.dao.impl.MaterialLifeDAOImpl;
import app.java.commons.MathUtils;
import app.java.parser.local.dao.MaterialLifeDAO;

import java.util.ArrayList;

public class MaterialLifeRelDAOImpl implements MaterialLifeRelDAO {
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();
    private static MaterialLifeDAO materialLifeDAO = new MaterialLifeDAOImpl();
    private static CommonRelDAO relation = new CommonRelDAOImpl();
    private static MathUtils math = new MathUtils();
    private static Utils utils = new Utils();

    // moneyRate = ponderea cheltuielilor totale din totalul veniturilor pe gospodarie
    // incomeRate = ponderea veniturilor totale pe gospodarie fata de salariul mediu net
    // povertyRate = (INSSE) ponderea persoanelor sarace din totalul populatiei
    // povertyRisk = (INSSE) ponderea in total populatie a persoanelor aflate in risc de saracie sau excluziune sociala
    // deprivationRate = (INSSE) ponderea in total populatie a persoanelor aflate in stare de deprivare materiala severa
    // (persoane care nu isi pot permite plata la timp a obligatiilor, plata unei vacante de 1 saptamana etc.)
    public double getMaterialLifeRate(String region, short year) {
        ArrayList<ArrayList<MaterialLifeObject>> lists = materialLifeDAO.getMaterialLifeLists(Constants.MIN_YEAR, Constants.MAX_YEAR);
        ArrayList<MaterialLifeObject> mainList = materialLifeDAO.getMaterialLifeList(year);

        double material = 0;

        for (int i = 0; i < mainList.size(); i++) {
            MaterialLifeObject item = mainList.get(i);
            String itemRegion = item.getRegion();

            if (region.equals(itemRegion)) {
                double venueRate = getVenueRate(lists, item);
                double poorRate = getPoorRate(lists, item);
                material = venueRate / poorRate;

//                System.out.println(year + " " + region + " venueRate: " + venueRate);
            }
        }

        if (material == 0)
            System.err.println("Material and Living Conditions Index: " + material);

        return material;
    }

    private double getVenueRate(ArrayList<ArrayList<MaterialLifeObject>> lists,
                               MaterialLifeObject item) {
        String itemRegion = item.getRegion();
        double avgNetSalary = relation.getAvgNetSalary(itemRegion, (short) item.getYear());
        double avgSpend = item.getAvgTotalSpend();
        double avgIncome = item.getAvgTotalIncome();

        if (avgSpend == 0)
            avgSpend = item.getAvgRate(lists, itemRegion, Constants.TYPES.AVG_TOTAL_SPEND);
        if (avgIncome == 0)
            avgIncome = item.getAvgRate(lists, itemRegion, Constants.TYPES.AVG_TOTAL_INCOME);

        double moneyRate = avgIncome / avgSpend * 100;
        //TODO: de eliminat incomeRate sau avgNetSalary
        double incomeRate = avgIncome/ avgNetSalary * 100;
        double venueRate = avgNetSalary * moneyRate * incomeRate;

        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.MATERIAL_LIFE, Constants.TYPES.AVG_TOTAL_SPEND);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.MATERIAL_LIFE, Constants.TYPES.AVG_TOTAL_INCOME);

        return math.getSquareValue(venueRate, 3);
    }

    private double getPoorRate(ArrayList<ArrayList<MaterialLifeObject>> lists,
                               MaterialLifeObject item) {
        String itemRegion = item.getRegion();
        double relativePovertyRate = item.getRelativePoverty();
        double povertyRisk = item.getPovertyRisk();
        double deprivationRate = item.getDeprivationRate();

        if (relativePovertyRate == 0)
            relativePovertyRate = item.getAvgRate(lists, itemRegion, Constants.TYPES.RELATIVE_POVERTY);
        if (povertyRisk == 0)
            povertyRisk = item.getAvgRate(lists, itemRegion, Constants.TYPES.POVERTY_RISK);
        if (deprivationRate == 0)
            deprivationRate = item.getAvgRate(lists, itemRegion, Constants.TYPES.DEPRIVATION_RATE);

        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.MATERIAL_LIFE, Constants.TYPES.RELATIVE_POVERTY);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.MATERIAL_LIFE, Constants.TYPES.POVERTY_RISK);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.MATERIAL_LIFE, Constants.TYPES.DEPRIVATION_RATE);

        double poorRate = relativePovertyRate * povertyRisk * deprivationRate;

        return math.getSquareValue(poorRate, 3);
    }
}
