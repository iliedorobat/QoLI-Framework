package ro.webdata.qoli.server.commons;

public class Endpoint {
    public static final String URI_PATTERN = "%1$s://%2$s:%3$s/";
    public static final String DOMAIN = "localhost";
    public static final String PORT = "8080";
    public static final String PROTOCOL = "http";
    public static final String BASE_URI = String.format(URI_PATTERN, PROTOCOL, DOMAIN, PORT);
}
