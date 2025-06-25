package com.stano.versions;

public final class Version implements Comparable<Version> {

   private final int majorVersion;
   private final int minorVersion;
   private final int revisionNumber;
   private final boolean snapshot;

   public Version(int majorVersion, int minorVersion) {

      this.majorVersion = majorVersion;
      this.minorVersion = minorVersion;
      this.revisionNumber = 0;
      this.snapshot = false;
   }

   public Version(int majorVersion, int minorVersion, int revisionNumber) {

      this.majorVersion = majorVersion;
      this.minorVersion = minorVersion;
      this.revisionNumber = revisionNumber;
      this.snapshot = false;
   }

   public Version(int majorVersion, int minorVersion, int revisionNumber, boolean snapshot) {

      this.majorVersion = majorVersion;
      this.minorVersion = minorVersion;
      this.revisionNumber = revisionNumber;
      this.snapshot = snapshot;
   }

   public Version(String versionStr) {

      this.snapshot = versionStr.contains("-SNAPSHOT");

      if (this.snapshot) {
         versionStr = versionStr.substring(0, versionStr.indexOf("-SNAPSHOT"));
      }

      String[] parts = versionStr.split("\\.");

      this.majorVersion = Integer.parseInt(parts[0]);
      this.minorVersion = Integer.parseInt(parts[1]);

      if (parts.length == 3) {
         this.revisionNumber = Integer.parseInt(parts[2]);
      }
      else {
         this.revisionNumber = 0;
      }
   }

   public int getMajorVersion() {

      return majorVersion;
   }

   public int getMinorVersion() {

      return minorVersion;
   }

   public int getRevisionNumber() {

      return revisionNumber;
   }

   public boolean isSnapshot() {

      return snapshot;
   }

   @Override
   public boolean equals(Object o) {

      if (this == o) {
         return true;
      }

      if (o == null || getClass() != o.getClass()) {
         return false;
      }

      Version that = (Version)o;

      if (majorVersion != that.majorVersion) {
         return false;
      }

      if (minorVersion != that.minorVersion) {
         return false;
      }

      if (revisionNumber != that.revisionNumber) {
         return false;
      }

      if (snapshot != that.snapshot) {
         return false;
      }

      return true;
   }

   @Override
   public int hashCode() {

      int result = majorVersion;
      result = 31 * result + minorVersion;
      result = 31 * result + revisionNumber;
      result = 31 * result + (snapshot ? 1 : 0);
      return result;
   }

   @Override
   public String toString() {

      if (snapshot) {
         if (revisionNumber < 1) {
            return String.format("%02d.%02d-SNAPSHOT", majorVersion, minorVersion);
         }

         return String.format("%02d.%02d.%02d-SNAPSHOT", majorVersion, minorVersion, revisionNumber);
      }

      if (revisionNumber < 1) {
         return String.format("%02d.%02d", majorVersion, minorVersion);
      }

      return String.format("%02d.%02d.%02d", majorVersion, minorVersion, revisionNumber);
   }

   @Override
   public int compareTo(Version version) {

      if (this == version) {
         return 0;
      }

      if (majorVersion < version.majorVersion) {
         return -1;
      }

      if (majorVersion > version.majorVersion) {
         return 1;
      }

      if (minorVersion < version.minorVersion) {
         return -1;
      }

      if (minorVersion > version.minorVersion) {
         return 1;
      }

      if (revisionNumber < version.revisionNumber) {
         return -1;
      }

      if (revisionNumber > version.revisionNumber) {
         return 1;
      }

      if (snapshot && !version.snapshot) {
         return 1;
      }

      if (!snapshot && version.snapshot) {
         return -1;
      }

      return 0;
   }
}
