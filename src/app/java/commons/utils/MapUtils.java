package app.java.commons.utils;

import app.java.commons.constants.Constants;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.MultiValuedMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapUtils {
    /**
     * Get the unique keys of a multi-dimensional value map
     *
     * @param params The multi-dimensional value map
     * @return The list of unique keys
     */
    public static ArrayList<String> getUniqueKeys(MultiValuedMap<String, String> params) {
        ArrayList<String> keysList = new ArrayList<>();

        MultiSet<String> multiSet = params.keys();
        Set<String> keys = multiSet.uniqueSet();
        Iterator iterator = keys.iterator();

        while (iterator.hasNext()) {
            keysList.add(iterator.next().toString());
        }

        return keysList;
    }

    public static String generateKey(String code, Number year) {
        return code + Constants.KEY_SEPARATOR + year;
    }

    public static String getEntryCode(Map.Entry<String, Number> entry) {
        String[] keyList = entry.getKey()
                .split(Constants.KEY_SEPARATOR);

        if (keyList.length == 2)
            return keyList[0];

        return null;
    }

    public static Integer getEntryYear(Map.Entry<String, Number> entry) {
        String[] keyList = entry.getKey()
                .split(Constants.KEY_SEPARATOR);

        if (keyList.length == 2)
            return Integer.parseInt(keyList[1]);

        return null;
    }
}
