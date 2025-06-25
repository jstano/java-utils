package com.stano.resourceresolver;

import com.stano.resourceresolver.resource.ClassPathResource;
import com.stano.resourceresolver.resource.ContextResource;
import com.stano.resourceresolver.resource.Resource;
import com.stano.resourceresolver.resource.UrlResource;
import com.stano.resourceresolver.util.Assert;
import com.stano.resourceresolver.util.ClassUtils;
import com.stano.resourceresolver.util.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;

public class DefaultResourceLoader implements ResourceLoader {
  private ClassLoader classLoader;

  public DefaultResourceLoader() {
    this.classLoader = ClassUtils.getDefaultClassLoader();
  }

  public DefaultResourceLoader(ClassLoader classLoader) {
    this.classLoader = classLoader;
  }

  public void setClassLoader(ClassLoader classLoader) {
    this.classLoader = classLoader;
  }

  @Override
  public ClassLoader getClassLoader() {
    return (this.classLoader != null ? this.classLoader : ClassUtils.getDefaultClassLoader());
  }

  @Override
  public Resource getResource(String location) {
    Assert.notNull(location, "Location must not be null");
    if (location.startsWith("/")) {
      return getResourceByPath(location);
    }
    else if (location.startsWith(CLASSPATH_URL_PREFIX)) {
      return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()), getClassLoader());
    }
    else {
      try {
        // Try to parse the location as a URL...
        URL url = new URL(location);
        return new UrlResource(url);
      }
      catch (MalformedURLException ex) {
        // No URL -> resolve as resource path.
        return getResourceByPath(location);
      }
    }
  }

  protected Resource getResourceByPath(String path) {
    return new ClassPathContextResource(path, getClassLoader());
  }

  protected static class ClassPathContextResource extends ClassPathResource implements ContextResource {
    public ClassPathContextResource(String path, ClassLoader classLoader) {
      super(path, classLoader);
    }

    @Override
    public String getPathWithinContext() {
      return getPath();
    }

    @Override
    public Resource createRelative(String relativePath) {
      String pathToUse = StringUtils.applyRelativePath(getPath(), relativePath);
      return new ClassPathContextResource(pathToUse, getClassLoader());
    }
  }
}
