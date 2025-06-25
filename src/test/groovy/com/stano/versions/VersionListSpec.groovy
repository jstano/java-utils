package com.stano.versions

import spock.lang.Specification

public class VersionListSpec extends Specification {

   void testGetVersionsAfter() {

      def versions = [
         new Version("08.11"),
         new Version("10.04"),
         new Version("08.12"),
         new Version("09.17")
      ]

      def versionList = new VersionList(versions)

      when:
      def versionsAfter = versionList.getVersionsOnOrAfter(new Version("08.12"))

      then:
      versionsAfter.size() == 3
      versionsAfter.get(0) == new Version("08.12")
      versionsAfter.get(1) == new Version("09.17")
      versionsAfter.get(2) == new Version("10.04")
   }

   void testGetVersionsAfterNull() {

      def versions = [
         new Version("10.04"),
         new Version("08.12"),
         new Version("09.17")
      ]

      def versionList = new VersionList(versions)

      when:
      def versionsAfter = versionList.getVersionsOnOrAfter(null)

      then:
      versionsAfter.size() == 3
      versionsAfter.get(0) == new Version("08.12")
      versionsAfter.get(1) == new Version("09.17")
      versionsAfter.get(2) == new Version("10.04")
   }

   void testGetHighestVersion() {

      def versions = [
         new Version("10.04"),
         new Version("08.12"),
         new Version("09.17")
      ]

      def versionList = new VersionList(versions)

      when:
      def highestVersion = versionList.highestVersion

      then:
      highestVersion == new Version("10.04")
   }
}
