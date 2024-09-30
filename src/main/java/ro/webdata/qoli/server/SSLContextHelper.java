package ro.webdata.qoli.server;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.security.KeyStore;

public class SSLContextHelper {
    // Load the keystore into the Java SSLContext
    public static SSLContext createSSLContext() throws Exception {
        // Load the KeyStore (assuming PKCS12 format)
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try (FileInputStream keyStoreFile = new FileInputStream("/etc/letsencrypt/live/qoli.webdata.ro/keystore.p12")) {
            keyStore.load(keyStoreFile, "SunnyDay".toCharArray()); // keystorePassword
        }

        // Create a KeyManagerFactory
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, "QuietNight".toCharArray()); // keyPassword

        // Create a TrustManagerFactory
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);

        // Create and initialize the SSLContext
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

        return sslContext;
    }
}
