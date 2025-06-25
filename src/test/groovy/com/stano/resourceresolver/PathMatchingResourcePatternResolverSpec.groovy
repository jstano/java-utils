package com.stano.resourceresolver

import com.stano.resourceresolver.resource.Resource
import spock.lang.Specification

class PathMatchingResourcePatternResolverSpec extends Specification {
   def "should be able to locate a single resource"() {
      def resolver = new PathMatchingResourcePatternResolver()
      def testXmlResource = resolver.getResource('test.xml')

      expect:
      testXmlResource.filename == 'test.xml'
   }

   def "should be able to locate a set of resources that match a pattern"() {
      def resolver = new PathMatchingResourcePatternResolver()
      def resources = resolver.getResources('classpath*:com/stano/resourceresolver/**/*.xml')
      Arrays.sort(resources, new Comparator<Resource>() {

         @Override
         int compare(Resource resource1, Resource resource2) {

            return resource1.filename.compareTo(resource2.filename)
         }
      })

      expect:
      resources.size() == 6
      resources[0].filename == 'folder1a.xml'
      resources[1].filename == 'folder1b.xml'
      resources[2].filename == 'folder2a.xml'
      resources[3].filename == 'folder2b.xml'
      resources[4].filename == 'root1.xml'
      resources[5].filename == 'root2.xml'
   }
}
