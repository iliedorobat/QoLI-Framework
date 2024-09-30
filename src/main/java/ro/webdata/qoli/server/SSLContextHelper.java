package ro.webdata.qoli.server;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.security.KeyStore;

public class SSLContextHelper {
    private static final String KEYSTORE_PASSWORD = "SunnyDay"; // keystorePassword
    private static final String KEYSTORE_FILE = "/home/idorobat/workspace/keystore.p12"; // keystorePassword

    // Load the keystore into the Java SSLContext
    public static SSLContext createSSLContext() throws Exception {
        // Load the KeyStore (assuming PKCS12 format)
        KeyStore keyStore = KeyStore.getInstance("PKCS12");

        try (FileInputStream keystoreStream = new FileInputStream(KEYSTORE_FILE)) {
            keyStore.load(keystoreStream, KEYSTORE_PASSWORD.toCharArray());
        }

        // Create a KeyManagerFactory
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(keyStore, KEYSTORE_PASSWORD.toCharArray());

        // Create a TrustManagerFactory
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(keyStore);

        // Create and initialize the SSLContext
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        return sslContext;
    }
}
