package ro.webdata.qoli.server.auth;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class AuthService {
    private static String password;
    private static String username;

    public static String getPassword() {
        return password;
    }

    public static String getUsername() {
        return username;
    }

    public static boolean isAuthorized(List<String> usernameList, List<String> passwordList) {
        return usernameList != null
                && passwordList != null
                && isAuthorized(usernameList, username)
                && isAuthorized(passwordList, password);
    }

    public static void init(String fileName) {
        BufferedReader br = null;
        StringBuilder sb = null;

        try {
            br = new BufferedReader(new FileReader(fileName));
            sb = new StringBuilder();
            String readLine;

            while ((readLine = br.readLine()) != null) {
                if (!readLine.isEmpty()) {
                    sb.append(readLine).append("\n");

                    if (readLine.startsWith("PASSWORD") || readLine.startsWith("USERNAME")) {
                        String[] data = readLine.split("=");
                        String value = data[1];

                        if (readLine.startsWith("PASSWORD")) {
                            password = value;
                        } else if (readLine.startsWith("USERNAME")) {
                            username = value;
                        }
                    }
                }
            }

            // Remove the last "Enter"
            sb.delete(sb.lastIndexOf("\n"), sb.length());
        } catch (FileNotFoundException e) {
            System.err.println("The file " + fileName + " have not been found."
                    + "\nError: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.err.println("The file 'BufferedReader' could not be closed."
                        + "\nError: " + e.getMessage());
            }
        }
    }

    private static boolean isAuthorized(List<String> list, String target) {
        for (String item : list) {
            if (item.equals(target)) {
                return true;
            }
        }

        return false;
    }
}
