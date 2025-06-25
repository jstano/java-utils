package com.stano.exceptions

import spock.lang.Specification

import java.sql.SQLException

class RuntimeSQLExceptionSpec extends Specification {

   def "should be able to create an exception with a nested SQLException and get the nested exception out"() {

      def rootCause = new NullPointerException()
      def sqlException = new SQLException("Something bad happened", rootCause)
      def exception = new RuntimeSQLException(sqlException)

      expect:
      exception.cause == sqlException
      exception.message == "java.sql.SQLException: Something bad happened"
   }

   def "should be able to create a RuntimeSQLException with a nested NullPointerException and get the nested exception out"() {

      def rootCause = new NullPointerException("Null Pointer Error")
      def exception = new RuntimeSQLException(rootCause)

      expect:
      exception.cause == rootCause
      exception.message == "java.lang.NullPointerException: Null Pointer Error"
   }

   def "should be able to create a RuntimeSQLException with a nested OutOfMemoryError and get the nested exception out"() {

      def rootCause = new OutOfMemoryError("Out of memory")
      def exception = new RuntimeSQLException(rootCause)

      expect:
      exception.cause == rootCause
      exception.message == "java.lang.OutOfMemoryError: Out of memory"
   }
}
