
/**
 * Module for the {@code "SSLServer"} security provider.
 * 
 * @provides java.security.Provider
 */
module com.github.marschall.directorykeystore {

  exports com.github.marschall.sslservercertstore;

  provides java.security.Provider
      with com.github.marschall.sslservercertstore.SSLServerProvider;

}