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
    0.0   | 0.0d
    0.1   | 0.0d
    0.49  | 0.0d
    0.5   | 0.0d
    0.51  | 1.0d
    0.99  | 1.0d
    1.0   | 1.0d
    1.1   | 1.0d
    1.49  | 1.0d
    1.5   | 2.0d
    1.51  | 2.0d
  }

  def "double round(double,2) should work"() {
    expect:
    Numbers.round(value, 2) == expectedResult

    where:
    value  | expectedResult
    0.0    | 0.0d
    0.1    | 0.1d
    0.499  | 0.5d
    0.5    | 0.5d
    0.501  | 0.50d
    0.505  | 0.51d
    0.99   | 0.99d
    0.9901 | 0.99d
    0.9905 | 0.99d
    0.991  | 0.99d
    0.995  | 1.0d
  }

  def "double roundPercent(double) should work"() {
    expect:
    Numbers.roundPercent(value) == expectedResult

    where:
    value   | expectedResult
    0.0     | 0.0d
    0.1     | 0.1d
    0.499   | 0.499d
    0.49999 | 0.5d
    0.5     | 0.5d
    0.50001 | 0.5d
    0.501   | 0.501d
    0.505   | 0.505d
    0.99    | 0.99d
    0.9901  | 0.9901d
    0.9905  | 0.9905d
    0.991   | 0.991d
    0.995   | 0.995d
  }

  def "double roundCurrency(double) should work"() {
    expect:
    Numbers.roundCurrency(value) == expectedResult

    where:
    value   | expectedResult
    0.0     | 0.0d
    0.1     | 0.1d
    0.499   | 0.499d
    0.49999 | 0.5d
    0.5     | 0.5d
    0.50001 | 0.5d
    0.501   | 0.501d
    0.505   | 0.505d
    0.99    | 0.99d
    0.9901  | 0.9901d
    0.9905  | 0.9905d
    0.991   | 0.991d
    0.995   | 0.995d
  }

  def "double roundHours(double) should work"() {
    expect:
    Numbers.roundHours(value) == expectedResult

    where:
    value  | expectedResult
    0.0    | 0.0d
    0.1    | 0.1d
    0.499  | 0.5d
    0.5    | 0.5d
    0.501  | 0.50d
    0.505  | 0.51d
    0.99   | 0.99d
    0.9901 | 0.99d
    0.9905 | 0.99d
    0.991  | 0.99d
    0.995  | 1.0d
  }

  def "double roundRawHours(double) should work"() {
    expect:
    Numbers.roundRawHours(value) == expectedResult

    where:
    value   | expectedResult
    0.0     | 0.0d
    0.1     | 0.1d
    0.499   | 0.499d
    0.49999 | 0.5d
    0.5     | 0.5d
    0.50001 | 0.5d
    0.501   | 0.501d
    0.505   | 0.505d
    0.99    | 0.99d
    0.9901  | 0.9901d
    0.9905  | 0.9905d
    0.991   | 0.991d
    0.995   | 0.995d
  }

  def "int truncate(double) should work"() {
    expect:
    Numbers.truncate(value) == expectedResult

    where:
    value   | expectedResult
    0.0     | 0
    0.1     | 0
    1.499   | 1
    2.49999 | 2
    2.75    | 2
  }

  def "long truncateLong(double) should work"() {
    expect:
    Numbers.truncateLong(value) == expectedResult

    where:
    value   | expectedResult
    0.0     | 0l
    0.1     | 0l
    1.499   | 1l
    2.49999 | 2l
    2.75    | 2l
  }

  def "call private constructor so coverage is 100%"() {
    expect:
    new Numbers() != null
  }
}
