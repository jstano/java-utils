package com.stano.resourcelocator;

public class TestLocator extends AbstractResourceLocator {

   public TestLocator() {

      super(TestLocator.class.getPackage().getName().replace(".", "/"), "xml");
   }
}
