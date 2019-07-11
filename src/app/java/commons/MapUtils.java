package app.java.commons;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.MultiValuedMap;

import java.util.*;

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
}
