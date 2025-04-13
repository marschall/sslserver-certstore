package com.github.marschall.sslservercertstore;

import java.security.Provider;

/**
 * Security provider for {@value #NAME}.
 */
public final class SSLServerProvider extends Provider {

    /**
     * The name of this security provider.
     */
    public static final String NAME = "SSLServer";

    /**
     * The type of keystore that uses directories to store certificates.
     */
    public static final String TYPE = "SSLServer";
    
    /**
     * Default constructor, either called directly by programmatic registration or
     * by JCA.
     */
    public SSLServerProvider() {
      super(NAME, "1.0.1", "SSLServer (CertStore)");
      this.put("CertStore." + TYPE, SSLServerCertStore.class.getName());
    }

}
