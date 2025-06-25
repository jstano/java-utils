package com.stano.resourcelocator

import com.test.testjar1.TestJar1Package
import com.test.testjar2.TestJar2Package
import spock.lang.Specification

class ResourceLocatorServiceSpec extends Specification {
   def "local resources should be located"() {
      def resourceLocatorService = new ResourceLocatorService()
      def testLocator = new TestLocator()
      def resources = resourceLocatorService.getResourceNames(testLocator)

      expect:
      resources.size() == 6
      resources.find { it.endsWith('com/stano/resourcelocator/folder1/folder1a.xml') }
      resources.find { it.endsWith('com/stano/resourcelocator/folder1/folder1b.xml') }
      resources.find { it.endsWith('com/stano/resourcelocator/folder2/folder2a.xml') }
      resources.find { it.endsWith('com/stano/resourcelocator/folder2/folder2b.xml') }
      resources.find { it.endsWith('com/stano/resourcelocator/root1.xml') }
      resources.find { it.endsWith('com/stano/resourcelocator/root2.xml') }

      url(resources.find { it.endsWith('com/stano/resourcelocator/folder1/folder1a.xml') }).text.trim() == 'folder1/folder1a.xml'
      url(resources.find { it.endsWith('com/stano/resourcelocator/folder1/folder1b.xml') }).text.trim() == 'folder1/folder1b.xml'
      url(resources.find { it.endsWith('com/stano/resourcelocator/folder2/folder2a.xml') }).text.trim() == 'folder2/folder2a.xml'
      url(resources.find { it.endsWith('com/stano/resourcelocator/folder2/folder2b.xml') }).text.trim() == 'folder2/folder2b.xml'
      url(resources.find { it.endsWith('com/stano/resourcelocator/root1.xml') }).text.trim() == 'root1.xml'
      url(resources.find { it.endsWith('com/stano/resourcelocator/root2.xml') }).text.trim() == 'root2.xml'
   }

   def "resources in jar1 and jar2 should both be found"() {
      def resourceLocatorService = new ResourceLocatorService()
      def resourceJar1 = resourceLocatorService.getResourceAsStream('com/test/testjar1/folder1/folder1a.xml')
      def resourceJar2 = resourceLocatorService.getResourceAsStream('com/test/testjar2/folder1/folder1a.xml')

      expect:
      resourceJar1.text.trim() == 'com/test/testjar1/folder1/folder1a.xml'
      resourceJar2.text.trim() == 'com/test/testjar2/folder1/folder1a.xml'
   }

   def "resource names in jar1 should be found and resource names in jar2 should not be found"() {
      def resourceLocatorService = new ResourceLocatorService()
      def resources1 = resourceLocatorService.getResourceNames(TestJar1Package, '*.xml')
      def resources2 = resourceLocatorService.getResourceNames(TestJar2Package, '*.xml')

      expect:
      resources1.size() == 7
      resources1.find { it.endsWith('com/test/testjar1/folder1/folder1a.xml') }
      resources1.find { it.endsWith('com/test/testjar1/folder1/folder1b.xml') }
      resources1.find { it.endsWith('com/test/testjar1/folder2/folder2a.xml') }
      resources1.find { it.endsWith('com/test/testjar1/folder2/folder2b.xml') }
      resources1.find { it.endsWith('com/test/testjar1/root1.xml') }
      resources1.find { it.endsWith('com/test/testjar1/root2.xml') }
      resources1.find { it.endsWith('com/test/testjar1/test.xml') }

      resources2.size() == 7
      resources2.find { it.endsWith('com/test/testjar2/folder1/folder1a.xml') }
      resources2.find { it.endsWith('com/test/testjar2/folder1/folder1b.xml') }
      resources2.find { it.endsWith('com/test/testjar2/folder2/folder2a.xml') }
      resources2.find { it.endsWith('com/test/testjar2/folder2/folder2b.xml') }
      resources2.find { it.endsWith('com/test/testjar2/root1.xml') }
      resources2.find { it.endsWith('com/test/testjar2/root2.xml') }
      resources2.find { it.endsWith('com/test/testjar2/test.xml') }

      url(resources1.find { it.endsWith('com/test/testjar1/folder1/folder1a.xml') }).text.trim() == 'com/test/testjar1/folder1/folder1a.xml'
      url(resources1.find { it.endsWith('com/test/testjar1/folder1/folder1b.xml') }).text.trim() == 'com/test/testjar1/folder1/folder1b.xml'
      url(resources1.find { it.endsWith('com/test/testjar1/folder2/folder2a.xml') }).text.trim() == 'com/test/testjar1/folder2/folder2a.xml'
      url(resources1.find { it.endsWith('com/test/testjar1/folder2/folder2b.xml') }).text.trim() == 'com/test/testjar1/folder2/folder2b.xml'
      url(resources1.find { it.endsWith('com/test/testjar1/root1.xml') }).text.trim() == 'com/test/testjar1/root1.xml'
      url(resources1.find { it.endsWith('com/test/testjar1/root2.xml') }).text.trim() == 'com/test/testjar1/root2.xml'
      url(resources1.find { it.endsWith('com/test/testjar1/test.xml') }).text.trim() == 'com/test/testjar1/test.xml'

      url(resources2.find { it.endsWith('com/test/testjar2/folder1/folder1a.xml') }).text.trim() == 'com/test/testjar2/folder1/folder1a.xml'
      url(resources2.find { it.endsWith('com/test/testjar2/folder1/folder1b.xml') }).text.trim() == 'com/test/testjar2/folder1/folder1b.xml'
      url(resources2.find { it.endsWith('com/test/testjar2/folder2/folder2a.xml') }).text.trim() == 'com/test/testjar2/folder2/folder2a.xml'
      url(resources2.find { it.endsWith('com/test/testjar2/folder2/folder2b.xml') }).text.trim() == 'com/test/testjar2/folder2/folder2b.xml'
      url(resources2.find { it.endsWith('com/test/testjar2/root1.xml') }).text.trim() == 'com/test/testjar2/root1.xml'
      url(resources2.find { it.endsWith('com/test/testjar2/root2.xml') }).text.trim() == 'com/test/testjar2/root2.xml'
      url(resources2.find { it.endsWith('com/test/testjar2/test.xml') }).text.trim() == 'com/test/testjar2/test.xml'
   }

   private URL url(String resourceName) {
      return URI.create(resourceName).toURL()
   }
}
