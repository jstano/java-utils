package com.stano.versions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class VersionList {

   public VersionList(List<Version> versionList) {

      this.versionList = versionList;

      Collections.sort(versionList);
   }

   public List<Version> getVersionsOnOrAfter(Version version) {

      List<Version> versions = new ArrayList<Version>();

      if (version != null) {
         for (int i = 0; i < versionList.size(); i++) {
            if (versionList.get(i).compareTo(version) >= 0) {
               versions.add(versionList.get(i));
            }
         }
      }
      else {
         versions.addAll(versionList);
      }

      Collections.sort(versions);

      return versions;
   }

   public Version getHighestVersion() {

      Version version = null;

      for (int i = 0; i < versionList.size(); i++) {
         if (version == null || versionList.get(i).compareTo(version) > 0) {
            version = versionList.get(i);
         }
      }

      return version;
   }

   private final List<Version> versionList;
}
