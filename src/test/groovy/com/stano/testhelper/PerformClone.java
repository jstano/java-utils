package com.stano.testhelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PerformClone {
  public static Object performClone(Object object) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
    if (!(object instanceof Cloneable)) {
      throw new IllegalArgumentException("Object not Cloneable");
    }

    Class<?> clazz = object.getClass();
    Method cloneMethod = getCloneMethod(clazz);

    while (cloneMethod == null) {
      clazz = clazz.getSuperclass();

      if (clazz == null) {
        throw new NoSuchMethodException("No clone method found in class hierarchy");
      }

      cloneMethod = getCloneMethod(clazz);
    }

    cloneMethod.setAccessible(true);
    return cloneMethod.invoke(object);
  }

  private static Method getCloneMethod(Class<?> clazz) {
    try {
      return clazz.getMethod("clone");
    }
    catch (NoSuchMethodException e) {
      try {
        return clazz.getDeclaredMethod("clone");
      }
      catch (NoSuchMethodException ex) {
        return null;
      }
    }
  }
}
