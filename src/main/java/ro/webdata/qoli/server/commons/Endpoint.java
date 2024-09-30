package ro.webdata.qoli.server.commons;

import ro.webdata.qoli.aggr.stats.constants.EnvConst;

public class Endpoint {
    public static final String URI_PATTERN = "%1$s://%2$s:%3$s/";
    public static final String DOMAIN = "webdata.ro";
    public static final String PORT = EnvConst.USE_TOMCAT_SERVER ? "8080" : "8443";
    public static final String PROTOCOL = EnvConst.IS_PRODUCTION ? "https" : "http";
    public static final String BASE_URI = String.format(URI_PATTERN, PROTOCOL, DOMAIN, PORT);
}
