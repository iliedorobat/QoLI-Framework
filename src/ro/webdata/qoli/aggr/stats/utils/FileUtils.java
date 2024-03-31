package ro.webdata.qoli.aggr.commons.utils;

import ro.webdata.qoli.aggr.commons.constants.Constants;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.net.URL;
import java.util.Iterator;

public class FileUtils {
    /**
     * Convert an XLS file to CSV
     * @param filePath The target/destination path
     * @param fileName The name of the target/destination file
     * @param extension The name of the target file
     */
    public static void convertXlsToCsv(String filePath, String fileName, String extension) {
        String path = filePath + File.separator + fileName + extension;
        Workbook workbook = null;
        Row row;
        Cell cell;
        StringBuilder sb = new StringBuilder();
        FileOutputStream outputStream = null;

        if (extension.equals(Constants.XLS_EXTENSION)) {
            try {
                new FileWriter(filePath + File.separator + fileName + Constants.CSV_EXTENSION, false).close();
                outputStream = new FileOutputStream(filePath + File.separator + fileName + Constants.CSV_EXTENSION, true);

                boolean isHeader = true;
                workbook = WorkbookFactory.create(new File(path));
                Sheet firstSheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = firstSheet.iterator();

                while (rowIterator.hasNext()) {
                    row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();

                    if (!isHeader)
                        sb.append("\n");
                    while (cellIterator.hasNext()) {
                        cell = cellIterator.next();
                        sb.append(cell);
                        sb.append(Constants.CSV_SEPARATOR);
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    outputStream.write(sb.toString().getBytes());

                    isHeader = false;
                    sb = new StringBuilder();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (workbook != null) {
                        workbook.close();
                        outputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Download an Excel file
     * @param httpLink The http address
     * @param filePath The destination path
     * @param fileName The name of the destination file
     * @param extension The extension of the destination file
     */
    public static void downloadExcelFile(String httpLink, String filePath, String fileName, String extension) {
        BufferedInputStream in = null;
        FileOutputStream out = null;

        File directory = new File(filePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            URL url = new URL(httpLink);
            in = new BufferedInputStream(url.openStream());
            out = new FileOutputStream(filePath + File.separator + fileName + extension);

            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                out.write(dataBuffer, 0, bytesRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

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
     * @param fileName The name of the output file
     * @param extension The extension of the output file
     */
    public static void writeToFile(StringBuilder sb, String path, String fileName, String extension) {
        String fullPath = path + File.separator + fileName + extension;
        FileWriter fw = null;

        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            fw = new FileWriter(fullPath);
            fw.write(sb.toString());
            System.out.println("The file " + fullPath + " was written on the disk.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fw != null : "The file " + fullPath +" has already been closed.";
                fw.close();
            } catch (IOException e) {
                System.err.println("The file " + fullPath +" could not be closed."
                        + "\n" + e.getMessage());
            }
        }
    }

    /**
     * Export the data to a JSON file
     * @param sb The data to be written
     * @param fileName The name of the output file
     * @param path The path to the desired file
     */
    public static void writeToJSONFile(StringBuilder sb, String path, String fileName) {
        writeToFile(sb, path, fileName, Constants.JSON_EXTENSION);
    }
}
