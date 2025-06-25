package com.stano.beans;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;

public final class BeanUtil {

   public static boolean classExists(String className) {

      try {
         Class.forName(className);
         return true;
      }
      catch (ClassNotFoundException ignored) {
         return false;
      }
   }

   public static Method getMethod(Class clazz, String methodName, Class[] parameterTypes) {

      if (parameterTypes == null) {
         parameterTypes = new Class[0];
      }

      for (Method method : clazz.getMethods()) {
         if (!method.getName().equals(methodName)) {
            continue;
         }

         if (method.getParameterTypes().length != parameterTypes.length) {
            continue;
         }

         return method;
      }

      throw new ReflectionException("Unable to locate a matching method named '" + methodName + "' for class '" + clazz.getName() + "'");
   }

   public static Class[] getClassesForObjects(Object[] parameters) {

      if (ArrayUtils.isEmpty(parameters)) {
         return null;
      }

      Class[] parameterTypes = new Class[parameters.length];

      for (int i = 0; i < parameters.length; i++) {
         if (parameters[i] != null) {
            parameterTypes[i] = parameters[i].getClass();
         }
         else {
            parameterTypes[i] = Object.class;
         }
      }

      return parameterTypes;
   }

   private BeanUtil() {

   }
}
