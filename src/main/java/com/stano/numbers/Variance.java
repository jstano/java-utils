package com.stano.numbers;

public class Variance {

   private final double value1;
   private final double value2;

   public static Variance between(double value1, double value2) {

      return new Variance(value1, value2);
   }

   public double asAbsolute() {

      return value1 - value2;
   }

   public double asPercent() {

      if (value1 != 0.0 && value2 != 0.0 && value1 != value2) {
         return (value1 - value2) / value2 * 100.0;
      }

      if (value1 == 0.0 && value2 == 0.0) {
         return 0.0;
      }

      if (value1 == 0.0) {
         return -100.0;
      }

      if (value2 == 0.0) {
         return 100.0;
      }

      return 0.0;
   }

   public boolean isOutsideAllowedVariancePercentages(double allowedFromVariancePercentage, double allowedToVariancePercentage) {

      double percentVariance = asPercent();

      return percentVariance < -allowedFromVariancePercentage || percentVariance > allowedToVariancePercentage;
   }

   private Variance(double value1, double value2) {

      this.value1 = value1;
      this.value2 = value2;
   }
}
