package ro.webdata.qoli.aggr.stats;

import java.util.Comparator;

public class MapOrder implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }
}
