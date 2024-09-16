package ro.webdata.qoli.server.endpoint.stats.config;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ro.webdata.qoli.server.commons.ParamsValues;
import ro.webdata.qoli.server.endpoint.stats.config.aggr.AggrQoLIConfig;
import ro.webdata.qoli.server.endpoint.stats.config.base.BaseQoLIConfig;

@Path("/api/v2/stats/config")
public class StatsConfigEndpoint {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCHO(
            @QueryParam("analysisType") String analysisType
    ) {
        switch (analysisType) {
            // E.g.: http://localhost:3070/stats/config?analysisType=aggregate
            case ParamsValues.AGGREGATED_ANALYSIS:
                AggrQoLIConfig config = new AggrQoLIConfig();
                return Response.ok().entity(config).build();

            // E.g.: http://localhost:3070/stats/config?analysisType=individually
            case ParamsValues.INDIVIDUALLY_ANALYSIS:
                BaseQoLIConfig baseConfig = new BaseQoLIConfig();
                return Response.ok().entity(baseConfig).build();

            default:
                return Response.status(500).entity("analysisType=" + analysisType + " is not allowed.").build();
        }
    }
}
