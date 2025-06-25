package com.stano.resourcelocator;

/**
 * This is a default instance of ResourceLocator that takes in a resourcePath and a fileExtension.
 * <p>
 * You must create an instance of this class in the module in which the resources are located. This is
 * required so the proper classLoader will be used to retrieve the resources.
 */
public abstract class AbstractResourceLocator implements ResourceLocator {
  private final String resourcePath;
  private final String fileExtension;

  public AbstractResourceLocator(String resourcePath, String fileExtension) {
    this.resourcePath = resourcePath;
    this.fileExtension = fileExtension;
  }

  @Override
  public String getResourcePath() {
    return resourcePath;
  }

  @Override
  public String getResourcePattern() {
    if (fileExtension.startsWith(".")) {
      return "*" + fileExtension;
    }

    return "*." + fileExtension;
  }
}
