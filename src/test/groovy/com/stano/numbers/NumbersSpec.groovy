package com.stano.numbers

import spock.lang.Specification

class NumbersSpec extends Specification {

   def "int round(double) should work"() {

      expect:
      Numbers.round(value) == expectedResult

      where:
      value | expectedResult
      0.0   | 0
      0.1   | 0
      0.49  | 0
      0.5   | 1
      0.51  | 1
      0.99  | 1
      1.0   | 1
      1.1   | 1
      1.49  | 1
      1.5   | 2
      1.51  | 2
   }

   def "long roundLong(double) should work"() {

      expect:
      Numbers.roundLong(value) == expectedResult

      where:
      value | expectedResult
      0.0   | 0
      0.1   | 0
      0.49  | 0
      0.5   | 1
      0.51  | 1
      0.99  | 1
      1.0   | 1
      1.1   | 1
      1.49  | 1
      1.5   | 2
      1.51  | 2
   }

   def "double round(double,0) should work"() {

      expect:
      Numbers.round(value, 0) == expectedResult

      where:
      value | expectedResult
      0.0   | 0.0
      0.1   | 0.0
      0.49  | 0.0
      0.5   | 0.0
      0.51  | 1.0
      0.99  | 1.0
      1.0   | 1.0
      1.1   | 1.0
      1.49  | 1.0
      1.5   | 2.0
      1.51  | 2.0
   }

   def "double round(double,2) should work"() {

      expect:
      Numbers.round(value, 2) == expectedResult

      where:
      value  | expectedResult
      0.0    | 0.0
      0.1    | 0.1
      0.499  | 0.5
      0.5    | 0.5
      0.501  | 0.50
      0.505  | 0.51
      0.99   | 0.99
      0.9901 | 0.99
      0.9905 | 0.99
      0.991  | 0.99
      0.995  | 1.0
   }

   def "double roundHours(double) should work"() {

      expect:
      Numbers.roundHours(value) == expectedResult

      where:
      value  | expectedResult
      0.0    | 0.0
      0.1    | 0.1
      0.499  | 0.5
      0.5    | 0.5
      0.501  | 0.50
      0.505  | 0.51
      0.99   | 0.99
      0.9901 | 0.99
      0.9905 | 0.99
      0.991  | 0.99
      0.995  | 1.0
   }

   def "double roundPercent(double) should work"() {

      expect:
      Numbers.roundHours(value) == expectedResult

      where:
      value   | expectedResult
      0.0     | 0.0
      0.1     | 0.1
      0.499   | 0.5
      0.49999 | 0.5
      0.5     | 0.5
      0.50001 | 0.5
      0.501   | 0.50
      0.505   | 0.51
      0.99    | 0.99
      0.9901  | 0.99
      0.9905  | 0.99
      0.991   | 0.99
      0.995   | 1.0
   }

   def "double roundCurrency(double) should work"() {

      expect:
      Numbers.roundHours(value) == expectedResult

      where:
      value   | expectedResult
      0.0     | 0.0
      0.1     | 0.1
      0.499   | 0.5
      0.49999 | 0.5
      0.5     | 0.5
      0.50001 | 0.5
      0.501   | 0.50
      0.505   | 0.51
      0.99    | 0.99
      0.9901  | 0.99
      0.9905  | 0.99
      0.991   | 0.99
      0.995   | 1.0
   }

   def "call private constructor so coverage is 100%"() {

      expect:
      new Numbers() != null
   }
}
