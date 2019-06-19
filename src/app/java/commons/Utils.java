package app.java.commons;

import app.java.parser.local.model.InsseObject;

import java.util.ArrayList;

public class Utils {
    /**
     * Get the list with Romanian economic development regions
     * @return <b>String[]</b>
     */
    public String[] getRegions() {
        String[] regions = new String[] {
                Constants.B_IF_REGION,
                Constants.CENTER_REGION,
                Constants.NE_REGION,
                Constants.NV_REGION,
                Constants.SE_REGION,
                Constants.S_M_REGION,
                Constants.SV_REGION,
                Constants.V_REGION
        };

        return regions;
    }

    /**
     * Get the Romanian economic development region formatted as in Constants class
     * @param regions <b>String[]</b>: the array with regions from Constants class
     * @param item <b>InsseObject</b>: the object created using INSSE data
     * @return <b>String</b>
     */
    public String getRegion(String[] regions, InsseObject item) {
        for (int i = 0; i < regions.length; i++) {
            // The white space replacement is needed for the "AMG156F - AMIGO - Employment rate.csv"
            // data set from "files/gov-rights" directory
            String region = regions[i].trim().toLowerCase().replaceAll(" ", "");
            String itemRegion = item.getRegion().trim().toLowerCase().replaceAll(" ", "");

            if (region.equals(itemRegion)) {
                return regions[i];
            }
        }

        return null;
    }

    /**
     * Get opposite of the rate<br/>
     * Use cases example: Social Assistants Rate (SAR) influences negatively the Safety Rate.
     * As the Safety Rate is computed as a product of some indicators, we can't say that
     * a higher SAR is better, so, we need to compute the reversed rate in order  to use it
     * to compute the Safety Rate.
     * @param inputRate <b>double</b>: the original rate
     * @return <b>double</b>
     */
    public double getReversedRate(double inputRate) {
        return (100 - inputRate);
    }

    /**
     * Get the list with the processed data
     * @param filePath <b>String</b>: the path to the desired file
     * @param queryType <b>Constants.PARAMS</b>: a flag
     * @return <b>ArrayList<InsseObject></b>
     */
    public ArrayList<InsseObject> getInsseObject(String filePath, Constants.PARAMS queryType) {
        ArrayList<InsseObject> insseList = new ArrayList<>();
        String[] stringList = getStringList(filePath);

        short regionIndex = 0,
                yearIndex = 1,
                descriptionIndex = 2,
                valueIndex = 3;

        if (queryType == Constants.PARAMS.RATE ||
                queryType == Constants.PARAMS.SOCIAL_ACTIVITY ||
                queryType == Constants.PARAMS.SAFETY ||
                queryType == Constants.PARAMS.MEDICAL ||
                queryType == Constants.PARAMS.RESEARCHER) {
            regionIndex = 1;
            yearIndex = 2;
            descriptionIndex = 3;
            valueIndex = 4;
        }

        if (queryType == Constants.PARAMS.COUNT ||
                queryType == Constants.PARAMS.MATERIAL_LIFE_MONEY) {
            regionIndex = 2;
            yearIndex = 3;
            descriptionIndex = 4;
            valueIndex = 5;
        }

        if (queryType == Constants.PARAMS.RESIDENT_POPULATION) {
            regionIndex = 3;
            yearIndex = 4;
            descriptionIndex = 5;
            valueIndex = 6;
        }

        // The first element is the file description
        for (int i = 1; i < stringList.length; i++) {
            String[] elements = stringList[i].split(",");
            addInsseObject(insseList, elements, regionIndex, yearIndex, descriptionIndex, valueIndex);
        }

        return insseList;
    }

    /**
     * Get an array with every line of a file
     * @param filePath <b>String</b>: the path to the desired file
     * @return <b>String[]</b>
     */
    public String[] getStringList(String filePath) {
        StringBuilder sb = TextUtils.readFile(filePath);
        String str = sb.toString();
        return str.split("\\n");
    }

    /**
     * Add a new InsseObject
     * @param list <b>ArrayList<InsseObject></b>: the list where the InsseObject should be added
     * @param elements <b>String[]</b>: an array with one line of the read file
     * @param regionIndex <b>short</b>: the index of region from the "elements" array
     * @param yearIndex <b>short</b>: the index of year from the "elements" array
     * @param descriptionIndex <b>short</b>: the index of description from the "elements" array
     * @param valueIndex <b>short</b>: the index of value from the "elements" array
     */
    public void addInsseObject(ArrayList<InsseObject> list, String[] elements,
                                short regionIndex, short yearIndex, short descriptionIndex, short valueIndex) {
        String region = elements[regionIndex].trim();
        String year = elements[yearIndex].replaceAll("[^0-9]", "");
        String description = elements[descriptionIndex].trim();
        String value = elements[valueIndex].trim();

        list.add(new InsseObject(region, year, value, description));
    }
}
