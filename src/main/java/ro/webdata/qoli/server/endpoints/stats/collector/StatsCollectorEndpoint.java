package ro.webdata.qoli.server.endpoints.stats.collector;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import ro.webdata.qoli.EnvState;
import ro.webdata.qoli.aggr.data.fetch.DataCollector;
import ro.webdata.qoli.aggr.stats.constants.Constants;
import ro.webdata.qoli.aggr.stats.dimensions.QoLICsvStats;
import ro.webdata.qoli.aggr.stats.dimensions.QoLIJsonStats;
import ro.webdata.qoli.aggr.stats.dimensions.QoLIStats;

import java.util.List;
import java.util.Map;

@Path("/qoli")
public class StatsCollectorEndpoint {
    @GET
    @Path("/api/v2/stats/collect")
    public Response collectData(
            @QueryParam("aggr") List<String> aggrList,
            @QueryParam("calculateIndicators") boolean calculateIndicators
    ) {
        // E.g.: http://localhost:8080/qoli/api/v2/stats/collect?username=admin&password=admin1234
        try {
            DataCollector.collectData();

            int startYear = EnvState.MIN_YEAR;
            int endYear = EnvState.MAX_YEAR;
            Map<String, Map<String, Number>> dataByCountries = QoLIStats.prepareExtendedDimensions(aggrList, null, startYear, endYear);

            QoLICsvStats.writeDimensions(dataByCountries, Constants.DIRECTION_COLUMN, calculateIndicators, startYear, endYear);
            QoLIJsonStats.writeDimensions(dataByCountries, calculateIndicators, startYear, endYear);

            return Response.ok().entity("Download successful!").build();
        } catch (Error e) {
            e.printStackTrace();
            return Response.status(500).entity("Something went wrong getting data. Please contact the administrator.").build();
        }
    }
}
