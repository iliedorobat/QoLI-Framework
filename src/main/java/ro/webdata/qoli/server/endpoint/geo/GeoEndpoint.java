package ro.webdata.qoli.server.endpoint.geo;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import static ro.webdata.qoli.aggr.stats.constants.Constants.EU28_MEMBERS_NAME;
import static ro.webdata.qoli.aggr.stats.constants.Constants.NON_EU_MEMBERS_NAME;

@Path("/qoli/api/v2/geo")
public class GeoEndpoint {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGeo(
            @QueryParam("entityType") String entityType,
            @QueryParam("servedType") String servedType
    ) {
        if (entityType.equals("EU")) {
            return Response.ok().entity(EU28_MEMBERS_NAME).build();
        } else if (entityType.equals("NON_EU")) {
            return Response.ok().entity(NON_EU_MEMBERS_NAME).build();
        }

        return Response.status(500).entity("entityType = " + entityType + " and/or servedType = " + servedType + " is not allowed.").build();
    }
}
