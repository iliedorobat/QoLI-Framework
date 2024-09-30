package ro.webdata.qoli.server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ro.webdata.qoli.aggr.stats.constants.EnvConst;
import ro.webdata.qoli.server.auth.AuthFilter;
import ro.webdata.qoli.server.commons.Endpoint;
import ro.webdata.qoli.server.endpoint.geo.GeoEndpoint;
import ro.webdata.qoli.server.endpoint.stats.StatsEndpoint;
import ro.webdata.qoli.server.endpoint.stats.collector.StatsCollectorEndpoint;
import ro.webdata.qoli.server.endpoint.stats.config.StatsConfigEndpoint;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

// https://mkyong.com/webservices/jax-rs/jersey-hello-world-example/
public class Server {
    // Base URI the Grizzly HTTP server will listen on
    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());

    public static HttpServer startServer(boolean local) throws Exception {
        // Define the server URI and configure Grizzly with SSL
        final URI BASE_URI = URI.create(Endpoint.BASE_URI);

        // Create a resource config that registers the QoLIEndpoint JAX-RS resource
        final ResourceConfig config = new ResourceConfig();

        // Registering like this will give warnings like:
        // WARNING: A provider ro.webdata.qoli.server.endpoint.stats.StatsEndpoint registered in SERVER runtime does not implement any provider interfaces applicable in the SERVER runtime.
        // Due to constraint configuration problems the provider ro.webdata.qoli.server.endpoint.stats.StatsEndpoint will be ignored.
        // But it just works and according to stackoverflow this is a bug:
        // https://github.com/eclipse-ee4j/jersey/issues/3700
        config.register(AuthFilter.class);
        config.register(GeoEndpoint.class);
        config.register(StatsEndpoint.class);
        config.register(StatsCollectorEndpoint.class);
        config.register(StatsConfigEndpoint.class);

        if (local) {
            config.register(new CORSFilter());
        }

        // Disable wadl because I never asked for this.
        config.property("jersey.config.server.wadl.disableWadl", true);

        LOGGER.info("Starting server...");

        if (EnvConst.IS_PRODUCTION) {
//            // Create the SSLContext
//            SSLContext sslContext = SSLContextHelper.createSSLContext();
//
//            HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, config, false); // false to disable auto-start
//
//            // Configure SSL for the network listener
//            NetworkListener listener = new NetworkListener("secure-listener", BASE_URI.getHost(), BASE_URI.getPort());
//            // Create an SSLEngineConfigurator using the SSLContext
//            SSLEngineConfigurator sslEngineConfigurator = new SSLEngineConfigurator(sslContext)
//                    .setClientMode(false)    // Server mode
//                    .setNeedClientAuth(false);  // Set to true if mutual TLS is required
//            // Apply the SSLEngineConfigurator to the NetworkListener
//            listener.setSecure(true);
//            listener.setSSLEngineConfig(sslEngineConfigurator);
//
//            // Add the listener to the server
//            server.addListener(listener);
//
//            System.out.println("Secure server started at: " + BASE_URI);
//
//            return server;

            return GrizzlyHttpServerFactory.createHttpServer(
                    BASE_URI,
                    config,
                    true,
                    getSSLEngine()
            );
        }

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, config);
    }

    public static void main(String[] args) {
        try {
            final HttpServer httpServer = startServer(true);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    System.out.println("Shutting down the application...");
                    httpServer.shutdown();
                    System.out.println("Done, exit.");
                } catch (Exception e) {
                    Logger.getLogger(AuthFilter.class.getName()).log(Level.SEVERE, null, e);
                    Logger.getLogger(GeoEndpoint.class.getName()).log(Level.SEVERE, null, e);
                    Logger.getLogger(StatsEndpoint.class.getName()).log(Level.SEVERE, null, e);
                    Logger.getLogger(StatsCollectorEndpoint.class.getName()).log(Level.SEVERE, null, e);
                    Logger.getLogger(StatsConfigEndpoint.class.getName()).log(Level.SEVERE, null, e);
                }
            }));

            System.out.println(String.format("Application started.%nStop the application using CTRL+C"));

            // block and wait shut down signal, like CTRL+C
            Thread.currentThread().join();
        } catch (Exception e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // FIXME:
    private static SSLEngineConfigurator getSSLEngine() {
        SSLContextConfigurator sslConfig = new SSLContextConfigurator();
        sslConfig.setKeyStoreFile("/home/idorobat/workspace/keystore.p12");
        sslConfig.setKeyStorePass("SunnyDay");

        SSLEngineConfigurator engine = new SSLEngineConfigurator(sslConfig);
        engine.setNeedClientAuth(false);
        engine.setClientMode(false);

        return engine;
    }
}
