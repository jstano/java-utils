package com.stano.resourcelocator;

import com.stano.resourceresolver.PathMatchingResourcePatternResolver;
import com.stano.resourceresolver.resource.Resource;
import com.stano.resourceresolver.util.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class ResourceLocatorService {
  public InputStream getResourceAsStream(String resourceName) {
    try {
      return new PathMatchingResourcePatternResolver().getResource(resourceName).getInputStream();
    }
    catch (IOException x) {
      throw new IllegalStateException(x);
    }
  }

  public InputStream getResourceAsStream(Class sourceClass, String resourceName) {
    try {
      return new PathMatchingResourcePatternResolver(sourceClass.getClassLoader()).getResource(resourceName).getInputStream();
    }
    catch (IOException x) {
      throw new IllegalStateException(x);
    }
  }

  public InputStream getResourceAsStream(ResourceLocator resourceLocator, String resourceName) {
    try {
      return new PathMatchingResourcePatternResolver(resourceLocator.getClass().getClassLoader()).getResource(resourceName).getInputStream();
    }
    catch (IOException x) {
      throw new IllegalStateException(x);
    }
  }

  public Collection<String> getResourceNames(String resourcePattern) {
    return findResourceNames(null, null, resourcePattern);
  }

  public Collection<String> getResourceNames(Class sourceClass, String resourcePattern) {
    return getResourceNames(sourceClass, sourceClass.getPackage().getName().replace(".", "/"), resourcePattern);
  }

  public Collection<String> getResourceNames(ResourceLocator resourceLocator) {
    return getResourceNames(resourceLocator.getClass(), resourceLocator.getResourcePath(), resourceLocator.getResourcePattern());
  }

  private Collection<String> getResourceNames(Class sourceClass, String resourcePath, String resourcePattern) {
    return findResourceNames(sourceClass, resourcePath, resourcePattern);
  }

  private Collection<String> findResourceNames(Class sourceClass, String resourcePath, String resourcePattern) {
    try {
      ClassLoader classLoader = sourceClass != null ? sourceClass.getClassLoader() : ClassUtils.getDefaultClassLoader();
      PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(classLoader);
      Resource[] resources = resolver.getResources(getLocationPattern(resourcePath, resourcePattern));

      return Arrays.stream(resources).map(it -> {
        try {
          return it.getURL().toString();
        }
        catch (IOException x) {
          throw new IllegalStateException(x);
        }
      }).collect(Collectors.toList());
    }
    catch (IOException x) {
      throw new IllegalStateException(x);
    }
  }

  private String getLocationPattern(String resourcePath, String resourcePattern) {
    return StringUtils.isNotBlank(resourcePath)
           ? String.format("classpath*:%s/**/%s", resourcePath, resourcePattern)
           : String.format("classpath*:**/%s", resourcePattern);
  }
}
