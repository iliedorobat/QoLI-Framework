package ro.webdata.qoli.server;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.grizzly.Grizzly;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;

public class Server {
    private static final Logger LOGGER = Grizzly.logger(Server.class);

    public static void main(String[] args) {
        final HttpServer server = new HttpServer();
        final ServerConfiguration config = server.getServerConfiguration();

        // Register simple HttpHandler
        config.addHttpHandler(new SimpleHttpHandler(), "/");

        // create a network listener that listens on port 8080.
        final NetworkListener networkListener = new NetworkListener("secured-listener", NetworkListener.DEFAULT_NETWORK_HOST,
                NetworkListener.DEFAULT_NETWORK_PORT);

        // Enable SSL on the listener
        networkListener.setSecure(true);
        networkListener.setSSLEngineConfig(createSslConfiguration());

        server.addListener(networkListener);
        try {
            // Start the server
            server.start();
            System.out.println("The secured server is running.\nhttps://localhost:" + NetworkListener.DEFAULT_NETWORK_PORT + "\nPress enter to stop...");
            System.in.read();
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, ioe.toString(), ioe);
        } finally {
            server.shutdownNow();
        }
    }

    /**
     * Initialize server side SSL configuration.
     *
     * @return server side {@link SSLEngineConfigurator}.
     */
    private static SSLEngineConfigurator createSslConfiguration() {
        // Initialize SSLContext configuration
        SSLContextConfigurator sslContextConfig = new SSLContextConfigurator();

        ClassLoader cl = Server.class.getClassLoader();
        // Set key store
        URL keystoreUrl = cl.getResource("/home/idorobat/workspace/keystore.jks");
        if (keystoreUrl != null) {
            sslContextConfig.setKeyStoreFile(keystoreUrl.getFile());
            sslContextConfig.setKeyStorePass("SunnyDay");
        }

        // Create SSLEngine configurator
        return new SSLEngineConfigurator(sslContextConfig.createSSLContext(), false, false, false);
    }
}
