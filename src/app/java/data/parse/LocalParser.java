package app.java.data.parse;

import app.java.commons.utils.FileUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import no.ssb.jsonstat.JsonStatModule;
import no.ssb.jsonstat.v2.Dataset;
import no.ssb.jsonstat.v2.DatasetBuildable;
import no.ssb.jsonstat.v2.Dimension;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class LocalParser {
    public static Map<List<String>, Number> readJSONFile(String filePath) {
        Dataset build = getDataset(filePath);
        return build.asMap();
    }

    public static void printJSONEntries(Map<List<String>, Number> listListMap) {
        for (Map.Entry<List<String>, Number> listListEntry : listListMap.entrySet()) {
            System.out.println(listListEntry);
        }
    }

    public static Set<String> getDimensionsOrder(String filePath) {
        Dataset build = getDataset(filePath);
        Map<String, Dimension> dimension = build.getDimension();
        return dimension.keySet();
    }

    private static Dataset getDataset(String filePath) {
        StringBuilder sb = FileUtils.readFile(filePath);
        InputStream is = new ByteArrayInputStream(sb.toString().getBytes());
        Dataset build = null;

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JsonStatModule());
        mapper.registerModule(new Jdk8Module().configureAbsentsAsNulls(true));
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.registerModule(new GuavaModule().configureAbsentsAsNulls(false));

        try {
            JsonParser parser = mapper.getFactory().createParser(is);
            TypeReference<Map<String, DatasetBuildable>> ref = new TypeReference<>() {}; // Just a ref
            Map<String, DatasetBuildable> o = mapper.readValue(parser, ref);
            DatasetBuildable next = o.values().iterator().next();
            build = next.build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return build;
    }
}
