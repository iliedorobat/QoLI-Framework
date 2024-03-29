package ro.webdata.qoli.aggr.data;

import ro.webdata.qoli.aggr.commons.utils.FileUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import no.ssb.jsonstat.JsonStatModule;
import no.ssb.jsonstat.v2.Dataset;
import no.ssb.jsonstat.v2.DatasetBuildable;
import no.ssb.jsonstat.v2.Dimension;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LocalParser {
    // TODO: documentation:

    public static Map<List<String>, Number> readJSONFile(String filePath) {
        Dataset build = getDataset(filePath);
        return build.asMap();
    }

    public static void printJSONEntries(Map<List<String>, Number> listListMap) {
        for (Map.Entry<List<String>, Number> listListEntry : listListMap.entrySet()) {
            System.out.println(listListEntry);
        }
    }

    public static ArrayList<String> getDimensionKeys(String filePath) {
        Dataset build = getDataset(filePath);
        Map<String, Dimension> dimension = build.getDimension();
        Set<String> keys = dimension.keySet();
        return new ArrayList<>(keys);
    }

    private static Dataset getDataset(String filePath) {
        StringBuilder sb = FileUtils.readFile(filePath);
        InputStream is = new ByteArrayInputStream(sb.toString().getBytes());
        Dataset build = null;

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.registerModule(new GuavaModule().configureAbsentsAsNulls(false));
        mapper.registerModule(new Jdk8Module().configureAbsentsAsNulls(true));
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new JsonStatModule());

        try {
            JsonParser parser = mapper.getFactory().createParser(is);
            TypeReference<Map<String, DatasetBuildable>> ref = new TypeReference<>() {}; // Just a ref
            Map<String, DatasetBuildable> o = mapper.readValue(parser, ref);
            DatasetBuildable next = o.values().iterator().next();
            build = next.build();
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return build;
    }
}
