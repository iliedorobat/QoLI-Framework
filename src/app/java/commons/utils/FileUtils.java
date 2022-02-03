package app.java.commons.utils;

import app.java.commons.constants.Constants;
import app.java.commons.constants.FilePathConst;

import java.io.*;
import java.util.Map;

public class FileUtils {
    /**
     * Read the data from disk
     * @param path The path to the desired file
     * @return <b>StringBuilder</b>
     */
    public static StringBuilder readFile(String path) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(path));
            String readLine;

            while((readLine = br.readLine()) != null) {
                if (readLine.trim().length() > 0)
                    sb.append(readLine.trim()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file " + path + " has not been found.");
        } catch (IOException e) {
            System.err.println("Error at reading the file " + path + " from the disk.");
        } finally {
            try {
                assert br != null : "The file " + path + " has already been closed.";
                br.close();
            } catch (IOException e) {
                System.err.println("The file " + path + " could not be closed.");
            }
        }

        // Remove the last new line
        sb = sb.deleteCharAt(sb.length() - 1);

        return sb;
    }

    /**
     * Write the data to disk
     * @param sb The data to be written
     * @param path The path to the desired file
     */
    public static void writeToFile(StringBuilder sb, String path) {
        FileWriter fw = null;

        try {
            fw = new FileWriter(path);
            fw.write(sb.toString());
            System.out.println("The file " + path + " was written on the disk.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fw != null : "The file " + path + " has already been closed.";
                fw.close();
            } catch (IOException e) {
                System.err.println("The file " + path + " could not be closed."
                        + "\n" + e.getMessage());
            }
        }
    }

    /**
     * Export the data to a JSON file
     * @param sb The data to be written
     * @param path The path to the desired file
     */
    public static void writeToJSONFile(StringBuilder sb, String path) {
        writeToFile(sb, path + Constants.JSON_EXTENSION);
    }

    /**
     * Export the prepared chart data to a CSV file
     * @param entries The map with target dimension data
     * @param dimensionName The name of the target dimension
     */
    public static void writeChartData(Map<String, Number> entries, String dimensionName) {
        StringBuilder sb = StatsUtils.generateChartData(entries, dimensionName);
        writeToFile(sb, FilePathConst.OUTPUT_PATH + dimensionName + Constants.CSV_EXTENSION);
    }
}
