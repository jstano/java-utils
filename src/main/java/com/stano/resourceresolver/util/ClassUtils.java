package com.stano.resourceresolver.util;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public abstract class ClassUtils {
  public static final String ARRAY_SUFFIX = "[]";
  private static final String INTERNAL_ARRAY_PREFIX = "[";
  private static final String NON_PRIMITIVE_ARRAY_PREFIX = "[L";
  private static final char PACKAGE_SEPARATOR = '.';
  private static final char PATH_SEPARATOR = '/';
  private static final char INNER_CLASS_SEPARATOR = '$';
  private static final Map<String, Class<?>> primitiveTypeNameMap = new HashMap<String, Class<?>>(32);
  private static final Map<String, Class<?>> commonClassCache = new HashMap<String, Class<?>>(32);

  public static ClassLoader getDefaultClassLoader() {
    ClassLoader cl = null;
    try {
      cl = Thread.currentThread().getContextClassLoader();
    }
    catch (Throwable ex) {
      // Cannot access thread context ClassLoader - falling back...
    }
    if (cl == null) {
      // No thread context class loader -> use class loader of this class.
      cl = ClassUtils.class.getClassLoader();
      if (cl == null) {
        // getClassLoader() returning null indicates the bootstrap ClassLoader
        try {
          cl = ClassLoader.getSystemClassLoader();
        }
        catch (Throwable ex) {
          // Cannot access system ClassLoader - oh well, maybe the caller can live with null...
        }
      }
    }
    return cl;
  }

  public static Class<?> forName(String name, ClassLoader classLoader) throws ClassNotFoundException, LinkageError {
    Assert.notNull(name, "Name must not be null");

    Class<?> clazz = resolvePrimitiveClassName(name);
    if (clazz == null) {
      clazz = commonClassCache.get(name);
    }
    if (clazz != null) {
      return clazz;
    }

    // "java.lang.String[]" style arrays
    if (name.endsWith(ARRAY_SUFFIX)) {
      String elementClassName = name.substring(0, name.length() - ARRAY_SUFFIX.length());
      Class<?> elementClass = forName(elementClassName, classLoader);
      return Array.newInstance(elementClass, 0).getClass();
    }

    // "[Ljava.lang.String;" style arrays
    if (name.startsWith(NON_PRIMITIVE_ARRAY_PREFIX) && name.endsWith(";")) {
      String elementName = name.substring(NON_PRIMITIVE_ARRAY_PREFIX.length(), name.length() - 1);
      Class<?> elementClass = forName(elementName, classLoader);
      return Array.newInstance(elementClass, 0).getClass();
    }

    // "[[I" or "[[Ljava.lang.String;" style arrays
    if (name.startsWith(INTERNAL_ARRAY_PREFIX)) {
      String elementName = name.substring(INTERNAL_ARRAY_PREFIX.length());
      Class<?> elementClass = forName(elementName, classLoader);
      return Array.newInstance(elementClass, 0).getClass();
    }

    ClassLoader clToUse = classLoader;
    if (clToUse == null) {
      clToUse = getDefaultClassLoader();
    }
    try {
      return (clToUse != null ? clToUse.loadClass(name) : Class.forName(name));
    }
    catch (ClassNotFoundException ex) {
      int lastDotIndex = name.lastIndexOf(PACKAGE_SEPARATOR);
      if (lastDotIndex != -1) {
        String innerClassName =
          name.substring(0, lastDotIndex) + INNER_CLASS_SEPARATOR + name.substring(lastDotIndex + 1);
        try {
          return (clToUse != null ? clToUse.loadClass(innerClassName) : Class.forName(innerClassName));
        }
        catch (ClassNotFoundException ex2) {
          // Swallow - let original exception get through
        }
      }
      throw ex;
    }
  }

  public static Class<?> resolvePrimitiveClassName(String name) {
    Class<?> result = null;
    // Most class names will be quite long, considering that they
    // SHOULD sit in a package, so a length check is worthwhile.
    if (name != null && name.length() <= 8) {
      // Could be a primitive - likely.
      result = primitiveTypeNameMap.get(name);
    }
    return result;
  }

  public static String classPackageAsResourcePath(Class<?> clazz) {
    if (clazz == null) {
      return "";
    }
    String className = clazz.getName();
    int packageEndIndex = className.lastIndexOf(PACKAGE_SEPARATOR);
    if (packageEndIndex == -1) {
      return "";
    }
    String packageName = className.substring(0, packageEndIndex);
    return packageName.replace(PACKAGE_SEPARATOR, PATH_SEPARATOR);
  }
}
