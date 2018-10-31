package app.java.commons;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextUtils {
    /**
     * Read data from file
     * @param path <b>String</b>: the path to the desired file
     * @return <b>StringBuilder</b>
     */
    public StringBuilder readFile(String path) {
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
                br.close();
            } catch (IOException e) {
                System.err.println("The file 'Buffered Reader' could not be closed.");
            }
        }

        // Remove the last new line
        sb = sb.deleteCharAt(sb.length()-1);

        return sb;
    }
}
