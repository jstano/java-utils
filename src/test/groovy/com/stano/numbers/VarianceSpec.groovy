package com.stano.numbers

import spock.lang.Specification

class VarianceSpec extends Specification {

   def "should be able to compute a variance"() {

      def variance = Variance.between(value1, value2)

      expect:
      variance.asAbsolute() == absoluteVariance
      variance.asPercent() == percentVariance
      variance.isOutsideAllowedVariancePercentages(fromVariance, toVariance) == expectedOutsideVariance

      where:
      value1 | value2 | absoluteVariance | percentVariance | fromVariance | toVariance | expectedOutsideVariance
      10.0   | 20.0   | -10.0            | -50.0           | 25.0         | 25.0       | true
      10.0   | 20.0   | -10.0            | -50.0           | 49.9         | 25.0       | true
      10.0   | 20.0   | -10.0            | -50.0           | 50.0         | 25.0       | false
      10.0   | 20.0   | -10.0            | -50.0           | 50.1         | 25.0       | false

      20.0   | 10.0   | 10.0             | 100.0           | 25.0         | 25.0       | true
      20.0   | 10.0   | 10.0             | 100.0           | 25.0         | 99.9       | true
      20.0   | 10.0   | 10.0             | 100.0           | 25.0         | 100.0      | false
      20.0   | 10.0   | 10.0             | 100.0           | 25.0         | 100.1      | false

      0.0    | 0.0    | 0.0              | 0.0             | 25.0         | 25.0       | false

      0.0    | 100.0  | -100.0           | -100.0          | 25.0         | 25.0       | true
      100.0  | 0.0    | 100.0            | 100.0           | 25.0         | 25.0       | true

      100.0  | 100.0  | 0.0              | 0.0             | 25.0         | 25.0       | false
   }
}
