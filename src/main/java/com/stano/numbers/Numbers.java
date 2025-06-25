package com.stano.numbers;

public final class Numbers {

   private static final double[] roundMultipliers = {1.0, 10.0, 100.0, 1000.0, 10000.0, 100000.0};

   private static final int CURRENCY_ROUND_PRECISION = 4;
   private static final int HOURS_ROUND_PRECISION = 2;
   private static final int ROUND_PERCENT_PRECISION = 4;

   public static int round(double value) {

      int result = 0;

      if (value >= 0.0) {
         if (value - (int)value >= 0.5) {
            result = (int)value + 1;
         }
         else {
            result = (int)value;
         }
      }
      else if (value < 0.0) {
         double tempValue = -value;

         if (tempValue - (int)tempValue >= 0.5) {
            result = (int)tempValue + 1;
         }
         else {
            result = (int)tempValue;
         }

         result = -result;
      }

      return result;
   }

   public static long roundLong(double value) {

      long result = 0;

      if (value >= 0) {
         if (value - (long)value >= 0.5) {
            result = (long)value + 1;
         }
         else {
            result = (long)value;
         }
      }
      else if (value < 0) {
         double tempValue = -value;

         if (tempValue - (long)tempValue >= 0.5) {
            result = (long)tempValue + 1;
         }
         else {
            result = (long)tempValue;
         }

         result = -result;
      }

      return result;
   }

   public static double round(double value, int numberDecimals) {

      if (numberDecimals == 0) {
         return Math.rint(value);
      }

      // It may look hokie, but don't change. This takes care of issues like 1.175 being
      // converted to 1.1749999999 and being rounded down to 1.17 incorrectly.  The signum call is necessary so that negative numbers get rounded correctly too.
      double rounded = Math.rint(value * roundMultipliers[numberDecimals] + Math.signum(value) * .01 / roundMultipliers[numberDecimals]) / roundMultipliers[numberDecimals];

      // This last part may seem unnecessary but is necessary because of the pitfalls of floating point arithmetic.  Example zero may come in as -8.56354...E-16 which becomes "-0.00"
      // after rounding and then the comparison to zero actually fails.  Defining zero within the tolerance of +/- 10^-number of decimal precision ensures the test always passes or fails as expected.
      double epsilon = Math.pow(10.0, -numberDecimals);

      if (rounded <= (0.0 - epsilon) || rounded >= (0.0 + epsilon)) {
         return rounded;
      }

      return 0.0;
   }

   public static double roundPercent(double value) {

      return round(value, ROUND_PERCENT_PRECISION);
   }

   public static double roundCurrency(double value) {

      return round(value, CURRENCY_ROUND_PRECISION);
   }

   public static double roundHours(double value) {

      return round(value, HOURS_ROUND_PRECISION);
   }

   private Numbers() {

   }
}
