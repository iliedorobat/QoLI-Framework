package ro.webdata.qoli.server.endpoints.stats;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ro.webdata.qoli.aggr.stats.constants.Constants;
import ro.webdata.qoli.aggr.stats.dimensions.QoLIStats;
import ro.webdata.qoli.aggr.stats.utils.StatsUtils;
import ro.webdata.qoli.server.commons.ParamsValues;

import java.util.*;

@Path("/qoli")
public class StatsEndpoint {
    @GET
    @Path("/api/v2/stats")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStats(
            @QueryParam("aggr") List<String> aggrList,
            @QueryParam("analysisType") String analysisType,
            @QueryParam("countryCode") List<String> countryCodes,
            @QueryParam("startYear") int startYear,
            @QueryParam("endYear") int endYear
    ) {
        TreeMap<String, TreeMap<Integer, Number>> stats;

        switch (analysisType) {
            // E.g.: http://localhost:8080/qoli/api/v2/stats?analysisType=aggregate&aggr=education:dropoutRatio&aggr=health:health:bodyMassIndex&startYear=2020&endYear=2022
            case ParamsValues.AGGREGATED_ANALYSIS:
                stats = StatsUtils.filterStats(
                        QoLIStats.generateAggrStats(aggrList, countryCodes, startYear, endYear),
                        null,
                        Constants.EU28_MEMBERS,
                        Constants.SERIES_TYPE_COUNTRY,
                        startYear,
                        endYear
                );
                return Response.ok().entity(stats).build();

            // E.g.: http://localhost:8080/qoli/api/v2/stats?analysisType=individually&aggr=education:dropoutRatio&startYear=2020&endYear=2022
            case ParamsValues.INDIVIDUALLY_ANALYSIS:
                if (aggrList.size() > 1) {
                    return Response.status(500).entity("aggr parameter should be unique.").build();
                }

                String aggr = aggrList.get(0);
                if (aggr == null) {
                    return Response.status(500).entity("aggr parameter is mandatory.").build();
                }

                Set<String> keys = QoLIStats.baseIndicators.keySet();
                if (!keys.contains(aggr)) {
                    return Response.status(500).entity("aggr=" + aggr + " is not allowed.").build();
                }

                List<String> countryList = QoLIStats.getCountryList(countryCodes);
                stats = StatsUtils.filterStats(
                        QoLIStats.baseIndicators.get(aggr),
                        QoLIStats.rawIndicatorsTimeRange.get(aggr),
                        countryList.toArray(new String[0]),
                        Constants.SERIES_TYPE_COUNTRY,
                        startYear,
                        endYear
                );

                return Response.ok().entity(stats).build();

            default:
                return Response.status(500).entity("analysisType=" + analysisType + " is not allowed.").build();
        }
    }
}
