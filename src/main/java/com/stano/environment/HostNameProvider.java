package com.stano.environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class HostNameProvider {

   public static String getHostName() {

      try {
         return InetAddress.getLocalHost().getHostName();
      }
      catch (UnknownHostException x) {
         return "UNKNOWN_HOST";
      }
   }

   private HostNameProvider() {

   }
}
