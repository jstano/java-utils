package com.stano.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class NetworkUtils {

   public static boolean isLocalHost(String address) {

      try {
         InetAddress inetAddress = InetAddress.getByName(address);

         return inetAddress.isAnyLocalAddress() || inetAddress.isLoopbackAddress();
      }
      catch (UnknownHostException x) {
         throw new IllegalStateException(x);
      }
   }

   private NetworkUtils() {

   }
}
