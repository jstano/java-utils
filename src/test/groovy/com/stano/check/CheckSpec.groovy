package com.stano.check

import spock.lang.Specification

import java.time.LocalDate

class CheckSpec extends Specification {
  def "isTrue(expression) should throw a CheckFailureException with a default message if the expression is false"() {
    when:
    Check.isTrue(false)

    then:
    def x = thrown(CheckFailureException)
    x.message == "The validated expression is false"
  }

  def "isTrue(expression) should not throw a CheckFailureException if the expression is true"() {
    expect:
    Check.isTrue(true)
  }

  def "isTrue(expression,message) should throw a CheckFailureException if the expression is false"() {
    when:
    Check.isTrue(false, "The %s is false", "expression")

    then:
    def x = thrown(CheckFailureException)
    x.message == "The expression is false"
  }

  def "isTrue(expression,message) should not throw a CheckFailureException if the expression is true"() {
    expect:
    Check.isTrue(true, "The %s is false", "expression")
  }

  def "isTrue(expression,failureHandler) should execute the failureHandler if the expression is false"() {
    when:
    Check.isTrue(false) {
      throw new CheckFailureException("failureHandler worked")
    }

    then:
    def x = thrown(CheckFailureException)
    x.message == "failureHandler worked"
  }

  def "isTrue(expression,failureHandler) should not execute the failureHandler if the expression is true"() {
    expect:
    Check.isTrue(true) {
      throw new CheckFailureException("failureHandler worked")
    }
  }

  def "notNull(object) should throw a CheckFailureException with a default message if the value is null"() {
    when:
    Check.notNull(null)

    then:
    def x = thrown(CheckFailureException)
    x.message == "The validated object is null"
  }

  def "notNull(object) should not throw a CheckFailureException if the value is not null"() {
    expect:
    Check.notNull("abc")
  }

  def "notNull(object,message) should throw a CheckFailureException if the value is null"() {
    when:
    Check.notNull(null, "The %s is null", "value")

    then:
    def x = thrown(CheckFailureException)
    x.message == "The value is null"
  }

  def "notNull(object,message) should not throw a CheckFailureException if the value is not null"() {
    expect:
    Check.notNull("abc", "The %s is null", "value")
  }

  def "notNull(object,failureHandler) should execute the failureHandler if the value is null"() {
    when:
    Check.notNull(null) {
      throw new CheckFailureException("failureHandler worked")
    }

    then:
    def x = thrown(CheckFailureException)
    x.message == "failureHandler worked"
  }

  def "notNull(object,failureHandler) should not execute the failureHandler if the value is not null"() {
    expect:
    Check.notNull("abc") {
      throw new CheckFailureException("failureHandler worked")
    }
  }

  def "notInstanceOf(object) should not throw a CheckFailureException if the value is null"() {
    expect:
    Check.notInstanceOf(null, TypeA.class)
  }

  def "notInstanceOf(object) should not throw a CheckFailureException if the value is not an instance of the specified class"() {
    expect:
    Check.notInstanceOf(new TypeB(), TypeA.class)
  }

  def "notInstanceOf(object) should throw a CheckFailureException if the value is an instance of the specified class"() {
    when:
    Check.notInstanceOf(new TypeB(), TypeB.class)

    then:
    def x = thrown(CheckFailureException)
    x.message == "The validated object is an instance of the class 'com.stano.check.TypeB'"
  }

  def "notInstanceOf(object) should throw a CheckFailureException if the value is an instance of the specified class through extension"() {
    when:
    Check.notInstanceOf(new TypeExtendsB(), TypeB.class)

    then:
    def x = thrown(CheckFailureException)
    x.message == "The validated object is an instance of the class 'com.stano.check.TypeB'"
  }

  def "notInstanceOf(object) should not throw a CheckFailureException if the value is the base class of the specified type"() {
    expect:
    Check.notInstanceOf(new TypeB(), TypeExtendsB.class)
  }

  def "notInstanceOf(object,message) should not throw a CheckFailureException if the value is null"() {
    expect:
    Check.notInstanceOf(null, TypeA.class, "The %s is an instance of the class", "value")
  }

  def "notInstanceOf(object,message) should not throw a CheckFailureException if the value is not an instance of the specified class"() {
    expect:
    Check.notInstanceOf(new TypeB(), TypeA.class, "The %s is an instance of the class", "value")
  }

  def "notInstanceOf(object,message) should throw a CheckFailureException if the value is an instance of the specified class"() {
    when:
    Check.notInstanceOf(new TypeB(), TypeB.class, "The %s is an instance of the class", "value")

    then:
    def x = thrown(CheckFailureException)
    x.message == "The value is an instance of the class"
  }

  def "notInstanceOf(object,message) should throw a CheckFailureException if the value is an instance of the specified class through extension"() {
    when:
    Check.notInstanceOf(new TypeExtendsB(), TypeB.class, "The %s is an instance of the class", "value")

    then:
    def x = thrown(CheckFailureException)
    x.message == "The value is an instance of the class"
  }

  def "notInstanceOf(object,message) should not throw a CheckFailureException if the value is the base class of the specified type"() {
    expect:
    Check.notInstanceOf(new TypeB(), TypeExtendsB.class, "The %s is an instance of the class", "value")
  }

  def "notInstanceOf(object,failureHandler) should not execute the failureHandler if the value is null"() {
    expect:
    Check.notInstanceOf(null, TypeA.class) {
      throw new CheckFailureException("failureHandler worked")
    }
  }

  def "notInstanceOf(object,failureHandler) should not execute the failureHandler if the value is not an instance of the specified class"() {
    expect:
    Check.notInstanceOf(new TypeB(), TypeA.class) {
      throw new CheckFailureException("failureHandler worked")
    }
  }

  def "notInstanceOf(object,failureHandler) should execute the failureHandler if the value is an instance of the specified class"() {
    when:
    Check.notInstanceOf(new TypeB(), TypeB.class) {
      throw new CheckFailureException("failureHandler worked")
    }

    then:
    def x = thrown(CheckFailureException)
    x.message == "failureHandler worked"
  }

  def "notInstanceOf(object,failureHandler) should execute the failureHandler if the value is an instance of the specified class through extension"() {
    when:
    Check.notInstanceOf(new TypeExtendsB(), TypeB.class) {
      throw new CheckFailureException("failureHandler worked")
    }

    then:
    def x = thrown(CheckFailureException)
    x.message == "failureHandler worked"
  }

  def "notInstanceOf(object,failureHandler) should not execute the failureHandler if the value is the base class of the specified type"() {
    expect:
    Check.notInstanceOf(new TypeB(), TypeExtendsB.class) {
      throw new CheckFailureException("failureHandler worked")
    }
  }

  def "notEmpty(Object[]) should throw a CheckFailureException with a default message if the array is null or empty"() {
    when:
    Check.notEmpty((Object[])value)

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    value          | result
    null           | "The validated array is null"
    [] as Object[] | "The validated array is empty"
  }

  def "notEmpty(Object[],message) should throw a CheckFailureException if the array is null or empty"() {
    when:
    Check.notEmpty((Object[])value, "The %s is empty", "array")

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    value          | result
    null           | "The array is empty"
    [] as Object[] | "The array is empty"
  }

  def "notEmpty(byte[]) should throw a CheckFailureException with a default message if the array is null or empty"() {
    when:
    Check.notEmpty((byte[])value)

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    value        | result
    null         | "The validated array is null"
    [] as byte[] | "The validated array is empty"
  }

  def "notEmpty(byte[],message) should throw a CheckFailureException if the array is null or empty"() {
    when:
    Check.notEmpty((byte[])value, "The %s is empty", "array")

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    value        | result
    null         | "The array is empty"
    [] as byte[] | "The array is empty"
  }

  def "notEmpty(short[]) should throw a CheckFailureException with a default message if the array is null or empty"() {
    when:
    Check.notEmpty((short[])value)

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    value         | result
    null          | "The validated array is null"
    [] as short[] | "The validated array is empty"
  }

  def "notEmpty(short[],message) should throw a CheckFailureException if the array is null or empty"() {
    when:
    Check.notEmpty((short[])value, "The %s is empty", "array")

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    value         | result
    null          | "The array is empty"
    [] as short[] | "The array is empty"
  }

  def "notEmpty(int[]) should throw a CheckFailureException with a default message if the array is null or empty"() {
    when:
    Check.notEmpty((int[])value)

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    value       | result
    null        | "The validated array is null"
    [] as int[] | "The validated array is empty"
  }

  def "notEmpty(int[],message) should throw a CheckFailureException if the array is null or empty"() {
    when:
    Check.notEmpty((int[])value, "The %s is empty", "array")

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    value       | result
    null        | "The array is empty"
    [] as int[] | "The array is empty"
  }

  def "notEmpty(long[]) should throw a CheckFailureException with a default message if the array is null or empty"() {
    when:
    Check.notEmpty((long[])value)

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    value        | result
    null         | "The validated array is null"
    [] as long[] | "The validated array is empty"
  }

  def "notEmpty(long[],message) should throw a CheckFailureException if the array is null or empty"() {
    when:
    Check.notEmpty((long[])value, "The %s is empty", "array")

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    value        | result
    null         | "The array is empty"
    [] as long[] | "The array is empty"
  }

  def "notEmpty(float[]) should throw a CheckFailureException with a default message if the array is null or empty"() {
    when:
    Check.notEmpty((float[])value)

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    value         | result
    null          | "The validated array is null"
    [] as float[] | "The validated array is empty"
  }

  def "notEmpty(double[],message) should throw a CheckFailureException if the array is null or empty"() {
    when:
    Check.notEmpty((double[])value, "The %s is empty", "array")

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    value          | result
    null           | "The array is empty"
    [] as double[] | "The array is empty"
  }

  def "notEmpty(collection) should throw a CheckFailureException with a default message if the collection is null or empty"() {
    when:
    Check.notEmpty(value)

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    value              | result
    null as Collection | "The validated collection is null"
    [] as Collection   | "The validated collection is empty"
  }

  def "notEmpty(collection,message) should throw a CheckFailureException if the collection is null or empty"() {
    when:
    Check.notEmpty(value, "The %s is empty", "array")

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    value              | result
    null as Collection | "The array is empty"
    [] as Collection   | "The array is empty"
  }

  def "notEmpty(string) should throw a CheckFailureException with a default message if the string is null or empty"() {
    when:
    Check.notEmpty((String)value)

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    value | result
    null  | "The validated string is null"
    ""    | "The validated string is empty"
  }

  def "notEmpty(string,message) should throw a CheckFailureException if the string is null or empty"() {
    when:
    Check.notEmpty((String)value, "The %s is empty", "array")

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    value | result
    null  | "The array is empty"
    ""    | "The array is empty"
  }

  def "notEmpty should not throw a CheckFailureException if the array/collection/string is not null and not empty"() {
    expect:
    Check.notEmpty(["abc"] as Object[])
    Check.notEmpty(["abc"] as Object[], "The %s is null", "value")
    Check.notEmpty([(byte)1] as byte[])
    Check.notEmpty([(byte)1] as byte[], "The %s is null", "value")
    Check.notEmpty([(short)1] as short[])
    Check.notEmpty([(short)1] as short[], "The %s is null", "value")
    Check.notEmpty([(int)1] as int[])
    Check.notEmpty([(int)1] as int[], "The %s is null", "value")
    Check.notEmpty([(long)1] as long[])
    Check.notEmpty([(long)1] as long[], "The %s is null", "value")
    Check.notEmpty([(float)1] as float[])
    Check.notEmpty([(float)1] as float[], "The %s is null", "value")
    Check.notEmpty([(double)1] as double[])
    Check.notEmpty([(double)1] as double[], "The %s is null", "value")
    Check.notEmpty([true] as boolean[])
    Check.notEmpty([true] as boolean[], "The %s is null", "value")
    Check.notEmpty(["abc"] as Collection)
    Check.notEmpty(["abc"] as Collection, "The %s is null", "value")
    Check.notEmpty("abc")
    Check.notEmpty("abc", "The %s is null", "value")
  }

  def "notEmpty should execute the failureHandler if the array is null or empty"() {
    when:
    Check.notEmpty(value) {
      throw new CheckFailureException("failureHandler worked")
    }

    then:
    def x = thrown(CheckFailureException)
    x.message == "failureHandler worked"

    where:
    value             | result
    null as Object[]  | true
    [] as Object[]    | true
    null as byte[]    | true
    [] as byte[]      | true
    null as short[]   | true
    [] as short[]     | true
    null as int[]     | true
    [] as int[]       | true
    null as long[]    | true
    [] as long[]      | true
    null as float[]   | true
    [] as float[]     | true
    null as double[]  | true
    [] as double[]    | true
    null as boolean[] | true
    [] as boolean[]   | true
    null as ArrayList | true
    [] as ArrayList   | true
    null as String    | true
    ""                | true
  }

  def "notEmpty should not execute the failureHandler if the array is not null and not empty"() {
    expect:
    Check.notEmpty(["abc"] as Object[]) {
      throw new CheckFailureException("failureHandler worked")
    }
    Check.notEmpty([(byte)1] as byte[]) {
      throw new CheckFailureException("failureHandler worked")
    }
    Check.notEmpty([(short)1] as short[]) {
      throw new CheckFailureException("failureHandler worked")
    }
    Check.notEmpty([(int)1] as int[]) {
      throw new CheckFailureException("failureHandler worked")
    }
    Check.notEmpty([(long)1] as long[]) {
      throw new CheckFailureException("failureHandler worked")
    }
    Check.notEmpty([(float)1] as float[]) {
      throw new CheckFailureException("failureHandler worked")
    }
    Check.notEmpty([(double)1] as double[]) {
      throw new CheckFailureException("failureHandler worked")
    }
    Check.notEmpty([true] as boolean[]) {
      throw new CheckFailureException("failureHandler worked")
    }
    Check.notEmpty(["abc"] as ArrayList) {
      throw new CheckFailureException("failureHandler worked")
    }
    Check.notEmpty("abc") {
      throw new CheckFailureException("failureHandler worked")
    }
  }

  def "notBlank should throw a CheckFailureException if the string is null, empty or blank"() {
    when:
    Check.notBlank(value, "The %s is blank", "value")

    then:
    def x = thrown(CheckFailureException)
    x.message == "The value is blank"

    where:
    value          | result
    null as String | true
    ""             | true
    " "            | true
  }

  def "notBlank should not throw a CheckFailureException if the string is not null, empty, or blank"() {
    expect:
    Check.notBlank("abc", "The %s is blank", "value")
  }

  def "notBlank should execute the failureHandler if the string is null, empty, or blank"() {
    when:
    Check.notBlank(value) {
      throw new CheckFailureException("failureHandler worked")
    }

    then:
    def x = thrown(CheckFailureException)
    x.message == "failureHandler worked"

    where:
    value          | result
    null as String | true
    ""             | true
    " "            | true
  }

  def "notBlank should not execute the failureHandler if the string is not null, empty, or blank"() {
    expect:
    Check.notEmpty("abc") {
      throw new CheckFailureException("failureHandler worked")
    }
  }

  def "exclusiveBetween(long) should throw a CheckFailureException with a default message if the value is not between start and end"() {
    when:
    Check.exclusiveBetween(start, end, value)

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    start | end | value | result
    1     | 100 | 0     | "The value 0 is not in the specified exclusive range of 1 to 100"
    1     | 100 | 1     | "The value 1 is not in the specified exclusive range of 1 to 100"
    1     | 100 | 100   | "The value 100 is not in the specified exclusive range of 1 to 100"
    1     | 100 | 101   | "The value 101 is not in the specified exclusive range of 1 to 100"
  }

  def "exclusiveBetween(long) should not throw a CheckFailureException with a default message if the value is between start and end"() {
    expect:
    Check.exclusiveBetween(start, end, value)

    where:
    start | end | value | result
    1     | 100 | 2     | "The value 2 is not in the specified exclusive range of 1 to 100"
    1     | 100 | 99    | "The value 99 is not in the specified exclusive range of 1 to 100"
  }

  def "exclusiveBetween(long,message) should throw a CheckFailureException if the value is not between start and end"() {
    when:
    Check.exclusiveBetween(start, end, value, "The value %d is not > %d and < %d", value, start, end)

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    start | end | value | result
    1     | 100 | 0     | "The value 0 is not > 1 and < 100"
    1     | 100 | 1     | "The value 1 is not > 1 and < 100"
    1     | 100 | 100   | "The value 100 is not > 1 and < 100"
    1     | 100 | 101   | "The value 101 is not > 1 and < 100"
  }

  def "exclusiveBetween(long,message) should not throw a CheckFailureException if the value is between start and end"() {
    expect:
    Check.exclusiveBetween(start, end, value, "The value %d is not > %d and < %d", value, start, end)

    where:
    start | end | value | result
    1     | 100 | 2     | "The value 2 is not > 1 and < 100"
    1     | 100 | 99    | "The value 99 is not > 1 and < 100"
  }

  def "exclusiveBetween(double) should throw a CheckFailureException with a default message if the value is not between start and end"() {
    when:
    Check.exclusiveBetween(start, end, value)

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    start | end   | value | result
    1.0   | 100.0 | 0.0   | "The value 0.0 is not in the specified exclusive range of 1.0 to 100.0"
    1.0   | 100.0 | 1.0   | "The value 1.0 is not in the specified exclusive range of 1.0 to 100.0"
    1.0   | 100.0 | 100.0 | "The value 100.0 is not in the specified exclusive range of 1.0 to 100.0"
    1.0   | 100.0 | 101.0 | "The value 101.0 is not in the specified exclusive range of 1.0 to 100.0"
  }

  def "exclusiveBetween(double) should not throw a CheckFailureException with a default message if the value is between start and end"() {
    expect:
    Check.exclusiveBetween(start, end, value)

    where:
    start | end   | value | result
    1.0   | 100.0 | 2.0   | "The value 2.0 is not in the specified exclusive range of 1.0 to 100.0"
    1.0   | 100.0 | 99.0  | "The value 99.0 is not in the specified exclusive range of 1.0 to 100.0"
  }

  def "exclusiveBetween(double,message) should throw a CheckFailureException if the value is not between start and end"() {
    when:
    Check.exclusiveBetween(start, end, value, "The value %.1f is not > %.1f and < %.1f", value, start, end)

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    start | end   | value | result
    1.0   | 100.0 | 0.0   | "The value 0.0 is not > 1.0 and < 100.0"
    1.0   | 100.0 | 1.0   | "The value 1.0 is not > 1.0 and < 100.0"
    1.0   | 100.0 | 100.0 | "The value 100.0 is not > 1.0 and < 100.0"
    1.0   | 100.0 | 101.0 | "The value 101.0 is not > 1.0 and < 100.0"
  }

  def "exclusiveBetween(double,message) should not throw a CheckFailureException if the value is between start and end"() {
    expect:
    Check.exclusiveBetween(start, end, value, "The value %.1f is not > %.1f and < %.1f", value, start, end)

    where:
    start | end   | value | result
    1.0   | 100.0 | 2.0   | "The value 2.0 is not > 1.0 and < 100.0"
    1.0   | 100.0 | 99.0  | "The value 99.0 is not > 1.0 and < 100.0"
  }

  def "exclusiveBetween(Comparable) should throw a CheckFailureException with a default message if the value is not between start and end"() {
    when:
    Check.exclusiveBetween(start, end, value)

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    start          | end             | value           | result
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 7, 31) | "The value 2016-07-31 is not in the specified exclusive range of 2016-08-01 to 2016-08-31"
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 8, 1)  | "The value 2016-08-01 is not in the specified exclusive range of 2016-08-01 to 2016-08-31"
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 8, 31) | "The value 2016-08-31 is not in the specified exclusive range of 2016-08-01 to 2016-08-31"
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 9, 1)  | "The value 2016-09-01 is not in the specified exclusive range of 2016-08-01 to 2016-08-31"
  }

  def "exclusiveBetween(Comparable) should not throw a CheckFailureException with a default message if the value is between start and end"() {
    expect:
    Check.exclusiveBetween(start, end, value)

    where:
    start          | end             | value           | result
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 8, 2)  | "The value 2016-08-02 is not in the specified exclusive range of 2016-08-01 to 2016-08-31"
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 8, 30) | "The value 2016-08-30 is not in the specified exclusive range of 2016-08-01 to 2016-08-31"
  }

  def "exclusiveBetween(Comparable,message) should throw a CheckFailureException if the value is not between start and end"() {
    when:
    Check.exclusiveBetween(start, end, value, "The value %s is not > %s and < %s", value, start, end)

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    start          | end             | value           | result
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 7, 31) | "The value 2016-07-31 is not > 2016-08-01 and < 2016-08-31"
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 8, 1)  | "The value 2016-08-01 is not > 2016-08-01 and < 2016-08-31"
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 8, 31) | "The value 2016-08-31 is not > 2016-08-01 and < 2016-08-31"
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 9, 1)  | "The value 2016-09-01 is not > 2016-08-01 and < 2016-08-31"
  }

  def "exclusiveBetween(Comparable,message) should not throw a CheckFailureException if the value is between start and end"() {
    expect:
    Check.exclusiveBetween(start, end, value, "The value %s is not > %s and < %s", value, start, end)

    where:
    start          | end             | value           | result
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 8, 2)  | "The value 2016-08-02 is not > 2016-08-01 and < 2016-08-31"
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 8, 30) | "The value 2016-08-30 is not > 2016-08-01 and < 2016-08-31"
  }

  def "inclusiveBetween(long) should throw a CheckFailureException with a default message if the value is not between start and end"() {
    when:
    Check.inclusiveBetween(start, end, value)

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    start | end | value | result
    1     | 100 | 0     | "The value 0 is not in the specified inclusive range of 1 to 100"
    1     | 100 | 101   | "The value 101 is not in the specified inclusive range of 1 to 100"
  }

  def "inclusiveBetween(long) should not throw a CheckFailureException with a default message if the value is between start and end"() {
    expect:
    Check.inclusiveBetween(start, end, value)

    where:
    start | end | value | result
    1     | 100 | 1     | "The value 1 is not in the specified inclusive range of 1 to 100"
    1     | 100 | 2     | "The value 2 is not in the specified inclusive range of 1 to 100"
    1     | 100 | 99    | "The value 99 is not in the specified inclusive range of 1 to 100"
    1     | 100 | 100   | "The value 100 is not in the specified inclusive range of 1 to 100"
  }

  def "inclusiveBetween(long,message) should throw a CheckFailureException if the value is not between start and end"() {
    when:
    Check.inclusiveBetween(start, end, value, "The value %d is not > %d and < %d", value, start, end)

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    start | end | value | result
    1     | 100 | 0     | "The value 0 is not > 1 and < 100"
    1     | 100 | 101   | "The value 101 is not > 1 and < 100"
  }

  def "inclusiveBetween(long,message) should not throw a CheckFailureException if the value is between start and end"() {
    expect:
    Check.inclusiveBetween(start, end, value, "The value %d is not > %d and < %d", value, start, end)

    where:
    start | end | value | result
    1     | 100 | 1     | "The value 1 is not > 1 and < 100"
    1     | 100 | 2     | "The value 2 is not > 1 and < 100"
    1     | 100 | 99    | "The value 99 is not > 1 and < 100"
    1     | 100 | 100   | "The value 100 is not > 1 and < 100"
  }

  def "inclusiveBetween(double) should throw a CheckFailureException with a default message if the value is not between start and end"() {
    when:
    Check.inclusiveBetween(start, end, value)

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    start | end   | value | result
    1.0   | 100.0 | 0.0   | "The value 0.0 is not in the specified inclusive range of 1.0 to 100.0"
    1.0   | 100.0 | 101.0 | "The value 101.0 is not in the specified inclusive range of 1.0 to 100.0"
  }

  def "inclusiveBetween(double) should not throw a CheckFailureException with a default message if the value is between start and end"() {
    expect:
    Check.inclusiveBetween(start, end, value)

    where:
    start | end   | value | result
    1.0   | 100.0 | 1.0   | "The value 1.0 is not in the specified inclusive range of 1.0 to 100.0"
    1.0   | 100.0 | 2.0   | "The value 2.0 is not in the specified inclusive range of 1.0 to 100.0"
    1.0   | 100.0 | 99.0  | "The value 99.0 is not in the specified inclusive range of 1.0 to 100.0"
    1.0   | 100.0 | 100.0 | "The value 100.0 is not in the specified inclusive range of 1.0 to 100.0"
  }

  def "inclusiveBetween(double,message) should throw a CheckFailureException if the value is not between start and end"() {
    when:
    Check.inclusiveBetween(start, end, value, "The value %.1f is not > %.1f and < %.1f", value, start, end)

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    start | end   | value | result
    1.0   | 100.0 | 0.0   | "The value 0.0 is not > 1.0 and < 100.0"
    1.0   | 100.0 | 101.0 | "The value 101.0 is not > 1.0 and < 100.0"
  }

  def "inclusiveBetween(double,message) should not throw a CheckFailureException if the value is between start and end"() {
    expect:
    Check.inclusiveBetween(start, end, value, "The value %.1f is not > %.1f and < %.1f", value, start, end)

    where:
    start | end   | value | result
    1.0   | 100.0 | 1.0   | "The value 1.0 is not > 1.0 and < 100.0"
    1.0   | 100.0 | 2.0   | "The value 2.0 is not > 1.0 and < 100.0"
    1.0   | 100.0 | 99.0  | "The value 99.0 is not > 1.0 and < 100.0"
    1.0   | 100.0 | 100.0 | "The value 100.0 is not > 1.0 and < 100.0"
  }

  def "inclusiveBetween(Comparable) should throw a CheckFailureException with a default message if the value is not between start and end"() {
    when:
    Check.inclusiveBetween(start, end, value)

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    start          | end             | value           | result
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 7, 31) | "The value 2016-07-31 is not in the specified inclusive range of 2016-08-01 to 2016-08-31"
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 9, 1)  | "The value 2016-09-01 is not in the specified inclusive range of 2016-08-01 to 2016-08-31"
  }

  def "inclusiveBetween(Comparable) should not throw a CheckFailureException with a default message if the value is between start and end"() {
    expect:
    Check.inclusiveBetween(start, end, value)

    where:
    start          | end             | value           | result
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 8, 1)  | "The value 2016-08-01 is not in the specified inclusive range of 2016-08-01 to 2016-08-31"
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 8, 2)  | "The value 2016-08-02 is not in the specified inclusive range of 2016-08-01 to 2016-08-31"
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 8, 30) | "The value 2016-08-30 is not in the specified inclusive range of 2016-08-01 to 2016-08-31"
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 8, 31) | "The value 2016-08-31 is not in the specified inclusive range of 2016-08-01 to 2016-08-31"
  }

  def "inclusiveBetween(Comparable,message) should throw a CheckFailureException if the value is not between start and end"() {
    when:
    Check.inclusiveBetween(start, end, value, "The value %s is not > %s and < %s", value, start, end)

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    start          | end             | value           | result
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 7, 31) | "The value 2016-07-31 is not > 2016-08-01 and < 2016-08-31"
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 9, 1)  | "The value 2016-09-01 is not > 2016-08-01 and < 2016-08-31"
  }

  def "inclusiveBetween(Comparable,message) should not throw a CheckFailureException if the value is between start and end"() {
    expect:
    Check.inclusiveBetween(start, end, value, "The value %s is not > %s and < %s", value, start, end)

    where:
    start          | end             | value           | result
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 8, 1)  | "The value 2016-08-01 is not > 2016-08-01 and < 2016-08-31"
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 8, 2)  | "The value 2016-08-02 is not > 2016-08-01 and < 2016-08-31"
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 8, 30) | "The value 2016-08-30 is not > 2016-08-01 and < 2016-08-31"
    ld(2016, 8, 1) | ld(2016, 8, 31) | ld(2016, 8, 31) | "The value 2016-08-31 is not > 2016-08-01 and < 2016-08-31"
  }

  def "matchesPattern(input,pattern) should throw a CheckFailureException with a default message if the value is not matched"() {
    when:
    Check.matchesPattern(input, pattern)

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    input | pattern | result
    "abc" | "d"     | "The string 'abc' does not match the pattern 'd'"
  }

  def "matchesPattern(input,pattern) should not throw a CheckFailureException with a default message if the value is matched"() {
    expect:
    Check.matchesPattern(input, pattern)

    where:
    input | pattern | result
    "abc" | "abc"   | "The string 'abc' does not match the pattern 'abc'"
  }

  def "matchesPattern(input,pattern,message) should throw a CheckFailureException if the value is not matched"() {
    when:
    Check.matchesPattern(input, pattern, "The value '%s' does not match '%s'", input, pattern)

    then:
    def x = thrown(CheckFailureException)
    x.message == result

    where:
    input | pattern | result
    "abc" | "d"     | "The value 'abc' does not match 'd'"
  }

  def "matchesPattern(input,pattern,message) should not throw a CheckFailureException if the value is matched"() {
    expect:
    Check.matchesPattern(input, pattern, "The value '%s' does not match '%s'", input, pattern)

    where:
    input | pattern | result
    "abc" | "abc"   | "The value 'abc' does not match 'abc'"
  }

  def "call the private constructor so the coverage is accurate"() {
    expect:
    new Check() != null
  }

  private LocalDate ld(int year, int month, int day) {
    return LocalDate.of(year, month, day)
  }
}
