package com.stano.resourceresolver;

import com.stano.resourceresolver.resource.Resource;
import com.stano.resourceresolver.util.ResourceUtils;

public interface ResourceLoader {
  String CLASSPATH_URL_PREFIX = ResourceUtils.CLASSPATH_URL_PREFIX;

  Resource getResource(String location);

  ClassLoader getClassLoader();
}
