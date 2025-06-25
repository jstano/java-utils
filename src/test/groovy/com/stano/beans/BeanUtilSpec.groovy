package com.stano.beans

import spock.lang.Specification

class BeanUtilSpec extends Specification {

   def "classExists should return true if the class exists"() {

      expect:
      BeanUtil.classExists("java.lang.String")
   }

   def "classExists should return false if the class doesn't exist"() {

      expect:
      !BeanUtil.classExists("DummyClass")
   }

   def "getMethod should work for methodWithNoParameters if null is passed in"() {

      expect:
      BeanUtil.getMethod(TestClass, "methodWithNoParameters", null)
   }

   def "getMethod should work for methodWithNoParameters if an empty parameter array is passed in"() {

      expect:
      BeanUtil.getMethod(TestClass, "methodWithNoParameters", new Class[0])
   }

   def "getMethod should NOT work for methodWithNoParameters if an array of parameters is passed in"() {

      when:
      BeanUtil.getMethod(TestClass, "methodWithNoParameters", [String.class, int.class] as Class[])

      then:
      thrown ReflectionException
   }

   def "getMethod should work for methodWithParameters if the correct array of parameters is passed in"() {

      expect:
      BeanUtil.getMethod(TestClass, "methodWithParameters", [String.class, int.class] as Class[])
   }

   def "getMethod should NOT work for methodWithParameters if an incorrect array of parameters is passed in"() {

      when:
      BeanUtil.getMethod(TestClass, "methodWithParameters", [String.class, int.class, double.class] as Class[])

      then:
      thrown ReflectionException
   }

   def "getClassesForObjects should return null if null is passed in"() {

      expect:
      BeanUtil.getClassesForObjects(null) == null
   }

   def "getClassesForObjects should return null if an empty array is passed in"() {

      expect:
      BeanUtil.getClassesForObjects([] as Object[]) == null
   }

   def "getClassesForObjects should return the array of classes for each parameter"() {

      when:
      def classes = BeanUtil.getClassesForObjects(["abc", 1, null] as Object[])

      then:
      classes.length == 3
      classes[0] == String.class
      classes[1] == Integer.class
      classes[2] == Object.class
   }

   def "call private constructor so coverage is accurate"() {

      expect:
      new BeanUtil() != null
   }
}

class TestClass {

   void methodWithNoParameters() {

   }

   void methodWithParameters(String x, int y) {

   }
}
