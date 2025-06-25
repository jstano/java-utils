package com.stano.resourceresolver.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

public abstract class ResourceUtils {
  /**
   * Pseudo URL prefix for loading from the class path: "classpath:"
   */
  public static final String CLASSPATH_URL_PREFIX = "classpath:";

  /**
   * URL prefix for loading from the file system: "file:"
   */
  public static final String FILE_URL_PREFIX = "file:";

  /**
   * URL prefix for loading from the file system: "jar:"
   */
  public static final String JAR_URL_PREFIX = "jar:";

  /**
   * URL protocol for a file in the file system: "file"
   */
  public static final String URL_PROTOCOL_FILE = "file";

  /**
   * URL protocol for an entry from a jar file: "jar"
   */
  public static final String URL_PROTOCOL_JAR = "jar";

  /**
   * URL protocol for an entry from a zip file: "zip"
   */
  public static final String URL_PROTOCOL_ZIP = "zip";

  /**
   * URL protocol for an entry from a WebSphere jar file: "wsjar"
   */
  public static final String URL_PROTOCOL_WSJAR = "wsjar";

  /**
   * URL protocol for an entry from a JBoss jar file: "vfszip"
   */
  public static final String URL_PROTOCOL_VFSZIP = "vfszip";

  /**
   * URL protocol for a JBoss file system resource: "vfsfile"
   */
  public static final String URL_PROTOCOL_VFSFILE = "vfsfile";

  /**
   * URL protocol for a general JBoss VFS resource: "vfs"
   */
  public static final String URL_PROTOCOL_VFS = "vfs";

  /**
   * File extension for a regular jar file: ".jar"
   */
  public static final String JAR_FILE_EXTENSION = ".jar";

  /**
   * Separator between JAR URL and file path within the JAR: "!/"
   */
  public static final String JAR_URL_SEPARATOR = "!/";

  public static File getFile(URL resourceUrl, String description) throws FileNotFoundException {
    Assert.notNull(resourceUrl, "Resource URL must not be null");
    if (!URL_PROTOCOL_FILE.equals(resourceUrl.getProtocol())) {
      throw new FileNotFoundException(
        description + " cannot be resolved to absolute file path " +
        "because it does not reside in the file system: " + resourceUrl);
    }
    try {
      return new File(toURI(resourceUrl).getSchemeSpecificPart());
    }
    catch (URISyntaxException ex) {
      // Fallback for URLs that are not valid URIs (should hardly ever happen).
      return new File(resourceUrl.getFile());
    }
  }

  public static File getFile(URI resourceUri, String description) throws FileNotFoundException {
    Assert.notNull(resourceUri, "Resource URI must not be null");
    if (!URL_PROTOCOL_FILE.equals(resourceUri.getScheme())) {
      throw new FileNotFoundException(
        description + " cannot be resolved to absolute file path " +
        "because it does not reside in the file system: " + resourceUri);
    }
    return new File(resourceUri.getSchemeSpecificPart());
  }

  public static boolean isFileURL(URL url) {
    String protocol = url.getProtocol();
    return (URL_PROTOCOL_FILE.equals(protocol) || URL_PROTOCOL_VFSFILE.equals(protocol) ||
            URL_PROTOCOL_VFS.equals(protocol));
  }

  public static boolean isJarURL(URL url) {
    String protocol = url.getProtocol();
    return (URL_PROTOCOL_JAR.equals(protocol) || URL_PROTOCOL_ZIP.equals(protocol) ||
            URL_PROTOCOL_VFSZIP.equals(protocol) || URL_PROTOCOL_WSJAR.equals(protocol));
  }

  public static boolean isJarFileURL(URL url) {
    return (URL_PROTOCOL_FILE.equals(url.getProtocol()) &&
            url.getPath().toLowerCase().endsWith(JAR_FILE_EXTENSION));
  }

  public static URL extractJarFileURL(URL jarUrl) throws MalformedURLException {
    String urlFile = jarUrl.getFile();
    int separatorIndex = urlFile.indexOf(JAR_URL_SEPARATOR);
    if (separatorIndex != -1) {
      String jarFile = urlFile.substring(0, separatorIndex);
      try {
        return new URL(jarFile);
      }
      catch (MalformedURLException ex) {
        // Probably no protocol in original jar URL, like "jar:C:/mypath/myjar.jar".
        // This usually indicates that the jar file resides in the file system.
        if (!jarFile.startsWith("/")) {
          jarFile = "/" + jarFile;
        }
        return new URL(FILE_URL_PREFIX + jarFile);
      }
    }
    else {
      return jarUrl;
    }
  }

  public static URI toURI(URL url) throws URISyntaxException {
    return toURI(url.toString());
  }

  public static URI toURI(String location) throws URISyntaxException {
    return new URI(StringUtils.replace(location, " ", "%20"));
  }

  public static void useCachesIfNecessary(URLConnection con) {
    con.setUseCaches(con.getClass().getSimpleName().startsWith("JNLP"));
  }
}
