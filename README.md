SSLServer CertStore
===================

A CertStore that downloads the certificates from a TLS handshake.


```xml
<dependency>
    <groupId>com.github.marschall</groupId>
    <artifactId>sslserver-certstore</artifactId>
    <version>1.0.0</version>
</dependency>
```

```java
CertStore certStore = CertStore.getInstance("SSLServer", new URICertStoreParameters(new URI("https://example.com")));
CertSelector allCertificates = null;
Collection<? extends Certificate> certificates = certStore.getCertificates(allCertificates);
```

The class is extracted from OpenJDK (`sun.security.provider.certpath.ssl.SSLServerCertStore`). The OpenJDK code is used by [keytool -printcert](https://docs.oracle.com/en/java/javase/21/docs/specs/man/keytool.html#commands-for-displaying-data) when you invoke the following command

```
${JAVA_HOME}/bin/keytool -printcert -sslserver example.com
```


## Installation

The provider can be installed in two different ways

1. programmatic installation
1. static installation

### Programmatic Installation

The provider can be installed programmatically using

```java
Security.addProvider(new SSLServerProvider());
```

### Static Installation

The provider can be installed statically in the `java.security` file by adding the provider at the end

```
security.provider.N=SSLServer
```

`N` should be the value of the last provider incremented by 1. For OpenJDK 17 on Linux `N` should likely be 13.

This can be done
 * [per JVM installation](https://docs.oracle.com/en/java/javase/17/security/howtoimplaprovider.html#GUID-831AA25F-F702-442D-A2E4-8DA6DEA16F33)
 * [per JVM Instance](https://docs.oracle.com/en/java/javase/17/security/java-authentication-and-authorization-service-jaas-reference-guide.html#GUID-106F4B32-B9A3-4B75-BDBF-29B252BB3F53).
   * by appending to the `java.security` file using `-Djava.security.properties=/path/to/custom.java.security`
   * by replacing to the `java.security` file using `-Djava.security.properties==/path/to/custom.java.security`
   
The provider uses the ServiceLoader mechanism therefore using the `SSLServer` string is enough, there is no need to use the fully qualified class name.

Note that for this to work the provider JAR needs to be in the class path or module path.

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