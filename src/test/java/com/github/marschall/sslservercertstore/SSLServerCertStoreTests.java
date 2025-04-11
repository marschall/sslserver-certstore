package com.github.marschall.sslservercertstore;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.security.cert.CertStore;
import java.security.cert.Certificate;
import java.security.cert.URICertStoreParameters;
import java.util.Collection;

import org.junit.jupiter.api.Test;

class SSLServerCertStoreTests {

    @Test
    void getCertificates() throws GeneralSecurityException, URISyntaxException {
        CertStore certStore = CertStore.getInstance("SSLServer", new URICertStoreParameters(new URI("https://example.com")));
        Collection<? extends Certificate> certificates = certStore.getCertificates(null);
        assertNotNull(certificates);
        assertEquals(2, certificates.size());
    }

}
