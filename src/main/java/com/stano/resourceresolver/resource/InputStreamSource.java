package com.stano.resourceresolver.resource;

import java.io.IOException;
import java.io.InputStream;

public interface InputStreamSource {
  InputStream getInputStream() throws IOException;
}
