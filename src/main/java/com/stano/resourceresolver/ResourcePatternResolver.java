package com.stano.resourceresolver;

import com.stano.resourceresolver.resource.Resource;

import java.io.IOException;

public interface ResourcePatternResolver extends ResourceLoader {
  String CLASSPATH_ALL_URL_PREFIX = "classpath*:";

  Resource[] getResources(String locationPattern) throws IOException;
}
