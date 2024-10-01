package ro.webdata.qoli.server.auth;

import ro.webdata.qoli.EnvState;

import java.util.List;

public class AuthService {
    public static boolean isAuthorized(List<String> usernameList, List<String> passwordList) {
        return usernameList != null
                && passwordList != null
                && isAuthorized(usernameList, EnvState.AUTH_USER)
                && isAuthorized(passwordList, EnvState.AUTH_PASSWORD);
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
