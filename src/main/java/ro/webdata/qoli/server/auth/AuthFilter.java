package ro.webdata.qoli.server.auth;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
public class AuthFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        String method = context.getMethod();
        String path = context.getUriInfo().getPath();
        MultivaluedMap<String, String> queryParams = context.getUriInfo().getQueryParameters();

        if (method.equals("GET") && path.equals("qoli/api/v2/stats/collect")) {
            boolean isAuthorized = AuthService.isAuthorized(
                    queryParams.get("username"),
                    queryParams.get("password")
            );

            if (!isAuthorized) {
                context.abortWith(
                        Response.status(Response.Status.UNAUTHORIZED)
                                .entity("User cannot access the resource.")
                                .build()
                );
            }
        }
    }
}