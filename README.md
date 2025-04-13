SSLServer CertStore
===================

A CertStore that downloads the certificates from a TLS handshake.

```java
CertStore certStore = CertStore.getInstance("SSLServer", new URICertStoreParameters(new URI("https://example.com")));
CertSelector allCertificates = null;
Collection<? extends Certificate> certificates = certStore.getCertificates(allCertificates);
```

The class is extracted from OpenJDK (`sun.security.provider.certpath.ssl.SSLServerCertStore`). The OpenJDK code is used by [keytool -printcert](https://docs.oracle.com/en/java/javase/21/docs/specs/man/keytool.html#commands-for-displaying-data) when you invoke the following command

```
${JAVA_HOME}/bin/keytool -printcert -sslserver example.com
```

Java Requirements
-----------------

The code in the JDK requires JDK 17 to compile so this is our minimum verison.

Changes to the JDK code
-----------------------

We tried to keep the changes to the OpenJDK to a minimum nevertheless we had 

- Change the constructor from accepting `CertStoreParameters` instead of `URI`. This is required to make the lookup through `CertStore.getInstance` work.
- Removal of the `CertStore` subclass, the idea is to go through the `CertStore.getInstance` API so there is no need for this.
- Removal of the `static` `#getInstance(URI)` method, the idea is to go through the `CertStore.getInstance` API so there is no need for this.

Limitations
-----------

As we tried to limit the changes to the OpenJDK code we inherit some of the limitations of that code:

- There is `static` `synchronized` block around downloading the certificates.
- The code only works for HTTPS conncections, not generic TLS connections.


License
-------

Since the code is based on JDK it uses the same code as the JDK itself, [GNU General Public License, version 2, with the Classpath Exception](https://openjdk.org/legal/gplv2+ce.html).