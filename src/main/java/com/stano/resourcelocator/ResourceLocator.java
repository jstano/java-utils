package com.stano.resourcelocator;

/**
 * You must create an instance of this interface in the module in which the resources are located. This is
 * required so the proper classLoader will be used to retrieve the resources.
 */
public interface ResourceLocator {
  String getResourcePath();

  String getResourcePattern();
}
