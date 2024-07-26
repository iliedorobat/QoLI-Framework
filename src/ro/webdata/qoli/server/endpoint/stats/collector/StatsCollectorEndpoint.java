package ro.webdata.qoli.server.endpoint.stats.collector;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import ro.webdata.qoli.aggr.data.fetch.DataCollector;
import ro.webdata.qoli.aggr.stats.constants.Constants;
import ro.webdata.qoli.aggr.stats.constants.EnvConst;
import ro.webdata.qoli.aggr.stats.dimensions.QoLICsvStats;
import ro.webdata.qoli.aggr.stats.dimensions.QoLIJsonStats;
import ro.webdata.qoli.aggr.stats.dimensions.QoLIStats;

import java.util.List;
import java.util.Map;

@Path("/api/v2/stats/collect")
public class StatsCollectorEndpoint {
    @GET
    public Response collectData(
            @QueryParam("aggr") List<String> aggrList,
            @QueryParam("calculateIndicators") boolean calculateIndicators
    ) {
        // http://localhost:3070/stats/collect
        try {
            DataCollector.collectData();

            int startYear = EnvConst.MIN_YEAR;
            int endYear = EnvConst.MAX_YEAR;
            Map<String, Map<String, Number>> dataByCountries = QoLIStats.prepareExtendedDimensions(aggrList, null, startYear, endYear);

            QoLICsvStats.writeDimensions(dataByCountries, Constants.DIRECTION_COLUMN, calculateIndicators, startYear, endYear);
            QoLIJsonStats.writeDimensions(dataByCountries, calculateIndicators, startYear, endYear);

            return Response.ok().entity("Download successful!").build();
        } catch (Error e) {
            return Response.status(500).entity("Something went wrong getting data. Please contact the administrator.").build();
        }
    }
}
