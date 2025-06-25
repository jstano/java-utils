package com.stano.check;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.regex.Pattern;

public final class Check {

   private static final String DEFAULT_IS_TRUE_MESSAGE = "The validated expression is false";
   private static final String DEFAULT_IS_NULL_MESSAGE = "The validated object is null";
   private static final String DEFAULT_IS_INSTANCE_OF_MESSAGE = "The validated object is an instance of the class '%s'";
   private static final String DEFAULT_ARRAY_IS_NULL_MESSAGE = "The validated array is null";
   private static final String DEFAULT_ARRAY_IS_EMPTY_MESSAGE = "The validated array is empty";
   private static final String DEFAULT_COLLECTION_IS_NULL_MESSAGE = "The validated collection is null";
   private static final String DEFAULT_COLLECTION_IS_EMPTY_MESSAGE = "The validated collection is empty";
   private static final String DEFAULT_STRING_IS_NULL_MESSAGE = "The validated string is null";
   private static final String DEFAULT_STRING_IS_EMPTY_MESSAGE = "The validated string is empty";
   private static final String DEFAULT_STRING_IS_BLANK_MESSAGE = "The validated string is blank";
   private static final String DEFAULT_VALUE_OUTSIDE_EXCLUSIVE_RANGE_MESSAGE = "The value %s is not in the specified exclusive range of %s to %s";
   private static final String DEFAULT_VALUE_OUTSIDE_INCLUSIVE_RANGE_MESSAGE = "The value %s is not in the specified inclusive range of %s to %s";
   private static final String DEFAULT_STRING_DOES_NOT_MATCH_PATTERN_MESSAGE = "The string '%s' does not match the pattern '%s'";

   public static void isTrue(boolean expression) {

      if (!expression) {
         throw new CheckFailureException(DEFAULT_IS_TRUE_MESSAGE);
      }
   }

   public static void isTrue(boolean expression, String message, Object... values) {

      if (!expression) {
         throw new CheckFailureException(String.format(message, values));
      }
   }

   public static void isTrue(boolean expression, CheckFailureHandler failureHandler) {

      if (!expression) {
         failureHandler.run();
      }
   }

   public static void notNull(Object object) {

      if (object == null) {
         throw new CheckFailureException(DEFAULT_IS_NULL_MESSAGE);
      }
   }

   public static void notNull(Object object, String message, Object... values) {

      if (object == null) {
         throw new CheckFailureException(String.format(message, values));
      }
   }

   public static void notNull(Object object, CheckFailureHandler failureHandler) {

      if (object == null) {
         failureHandler.run();
      }
   }

   @SuppressWarnings("unchecked")
   public static void notInstanceOf(Object object, Class clazz) {

      if (object != null && clazz.isAssignableFrom(object.getClass())) {
         throw new CheckFailureException(String.format(DEFAULT_IS_INSTANCE_OF_MESSAGE, clazz.getName()));
      }
   }

   @SuppressWarnings("unchecked")
   public static void notInstanceOf(Object object, Class clazz, String message, Object... values) {

      if (object != null && clazz.isAssignableFrom(object.getClass())) {
         throw new CheckFailureException(String.format(message, values));
      }
   }

   @SuppressWarnings("unchecked")
   public static void notInstanceOf(Object object, Class clazz, CheckFailureHandler failureHandler) {

      if (object != null && clazz.isAssignableFrom(object.getClass())) {
         failureHandler.run();
      }
   }

   public static void notEmpty(Object[] array) {

      if (array == null) {
         throw new CheckFailureException(DEFAULT_ARRAY_IS_NULL_MESSAGE);
      }

      if (array.length == 0) {
         throw new CheckFailureException(DEFAULT_ARRAY_IS_EMPTY_MESSAGE);
      }
   }

   public static void notEmpty(Object[] array, String message, Object... values) {

      if (array == null || array.length == 0) {
         throw new CheckFailureException(String.format(message, values));
      }
   }

   public static void notEmpty(Object[] array, CheckFailureHandler failureHandler) {

      if (array == null || array.length == 0) {
         failureHandler.run();
      }
   }

   public static void notEmpty(byte[] array) {

      if (array == null) {
         throw new CheckFailureException(DEFAULT_ARRAY_IS_NULL_MESSAGE);
      }

      if (array.length == 0) {
         throw new CheckFailureException(DEFAULT_ARRAY_IS_EMPTY_MESSAGE);
      }
   }

   public static void notEmpty(byte[] array, String message, Object... values) {

      if (array == null || array.length == 0) {
         throw new CheckFailureException(String.format(message, values));
      }
   }

   public static void notEmpty(byte[] array, CheckFailureHandler failureHandler) {

      if (array == null || array.length == 0) {
         failureHandler.run();
      }
   }

   public static void notEmpty(short[] array) {

      if (array == null) {
         throw new CheckFailureException(DEFAULT_ARRAY_IS_NULL_MESSAGE);
      }

      if (array.length == 0) {
         throw new CheckFailureException(DEFAULT_ARRAY_IS_EMPTY_MESSAGE);
      }
   }

   public static void notEmpty(short[] array, String message, Object... values) {

      if (array == null || array.length == 0) {
         throw new CheckFailureException(String.format(message, values));
      }
   }

   public static void notEmpty(short[] array, CheckFailureHandler failureHandler) {

      if (array == null || array.length == 0) {
         failureHandler.run();
      }
   }

   public static void notEmpty(int[] array) {

      if (array == null) {
         throw new CheckFailureException(DEFAULT_ARRAY_IS_NULL_MESSAGE);
      }

      if (array.length == 0) {
         throw new CheckFailureException(DEFAULT_ARRAY_IS_EMPTY_MESSAGE);
      }
   }

   public static void notEmpty(int[] array, String message, Object... values) {

      if (array == null || array.length == 0) {
         throw new CheckFailureException(String.format(message, values));
      }
   }

   public static void notEmpty(int[] array, CheckFailureHandler failureHandler) {

      if (array == null || array.length == 0) {
         failureHandler.run();
      }
   }

   public static void notEmpty(long[] array) {

      if (array == null) {
         throw new CheckFailureException(DEFAULT_ARRAY_IS_NULL_MESSAGE);
      }

      if (array.length == 0) {
         throw new CheckFailureException(DEFAULT_ARRAY_IS_EMPTY_MESSAGE);
      }
   }

   public static void notEmpty(long[] array, String message, Object... values) {

      if (array == null || array.length == 0) {
         throw new CheckFailureException(String.format(message, values));
      }
   }

   public static void notEmpty(long[] array, CheckFailureHandler failureHandler) {

      if (array == null || array.length == 0) {
         failureHandler.run();
      }
   }

   public static void notEmpty(float[] array) {

      if (array == null) {
         throw new CheckFailureException(DEFAULT_ARRAY_IS_NULL_MESSAGE);
      }

      if (array.length == 0) {
         throw new CheckFailureException(DEFAULT_ARRAY_IS_EMPTY_MESSAGE);
      }
   }

   public static void notEmpty(float[] array, String message, Object... values) {

      if (array == null || array.length == 0) {
         throw new CheckFailureException(String.format(message, values));
      }
   }

   public static void notEmpty(float[] array, CheckFailureHandler failureHandler) {

      if (array == null || array.length == 0) {
         failureHandler.run();
      }
   }

   public static void notEmpty(double[] array) {

      if (array == null) {
         throw new CheckFailureException(DEFAULT_ARRAY_IS_NULL_MESSAGE);
      }

      if (array.length == 0) {
         throw new CheckFailureException(DEFAULT_ARRAY_IS_EMPTY_MESSAGE);
      }
   }

   public static void notEmpty(double[] array, String message, Object... values) {

      if (array == null || array.length == 0) {
         throw new CheckFailureException(String.format(message, values));
      }
   }

   public static void notEmpty(double[] array, CheckFailureHandler failureHandler) {

      if (array == null || array.length == 0) {
         failureHandler.run();
      }
   }

   public static void notEmpty(boolean[] array) {

      if (array == null) {
         throw new CheckFailureException(DEFAULT_ARRAY_IS_NULL_MESSAGE);
      }

      if (array.length == 0) {
         throw new CheckFailureException(DEFAULT_ARRAY_IS_EMPTY_MESSAGE);
      }
   }

   public static void notEmpty(boolean[] array, String message, Object... values) {

      if (array == null || array.length == 0) {
         throw new CheckFailureException(String.format(message, values));
      }
   }

   public static void notEmpty(boolean[] array, CheckFailureHandler failureHandler) {

      if (array == null || array.length == 0) {
         failureHandler.run();
      }
   }

   public static void notEmpty(Collection collection) {

      if (collection == null) {
         throw new CheckFailureException(DEFAULT_COLLECTION_IS_NULL_MESSAGE);
      }

      if (collection.isEmpty()) {
         throw new CheckFailureException(DEFAULT_COLLECTION_IS_EMPTY_MESSAGE);
      }
   }

   public static void notEmpty(Collection collection, String message, Object... values) {

      if (collection == null || collection.isEmpty()) {
         throw new CheckFailureException(String.format(message, values));
      }
   }

   public static void notEmpty(Collection collection, CheckFailureHandler failureHandler) {

      if (collection == null || collection.isEmpty()) {
         failureHandler.run();
      }
   }

   public static void notEmpty(String string) {

      if (string == null) {
         throw new CheckFailureException(DEFAULT_STRING_IS_NULL_MESSAGE);
      }

      if (StringUtils.isEmpty(string)) {
         throw new CheckFailureException(DEFAULT_STRING_IS_EMPTY_MESSAGE);
      }
   }

   public static void notEmpty(String string, String message, Object... values) {

      if (StringUtils.isEmpty(string)) {
         throw new CheckFailureException(String.format(message, values));
      }
   }

   public static void notEmpty(String string, CheckFailureHandler failureHandler) {

      if (StringUtils.isEmpty(string)) {
         failureHandler.run();
      }
   }

   public static void notBlank(String string) {

      if (string == null) {
         throw new CheckFailureException(DEFAULT_STRING_IS_NULL_MESSAGE);
      }

      if (StringUtils.isBlank(string)) {
         throw new CheckFailureException(DEFAULT_STRING_IS_BLANK_MESSAGE);
      }
   }

   public static void notBlank(String string, String message, Object... values) {

      if (StringUtils.isBlank(string)) {
         throw new CheckFailureException(String.format(message, values));
      }
   }

   public static void notBlank(String string, CheckFailureHandler failureHandler) {

      if (StringUtils.isBlank(string)) {
         failureHandler.run();
      }
   }

   public static void exclusiveBetween(double start, double end, double value) {

      if (value <= start || value >= end) {
         throw new CheckFailureException(String.format(DEFAULT_VALUE_OUTSIDE_EXCLUSIVE_RANGE_MESSAGE, value, start, end));
      }
   }

   public static void exclusiveBetween(double start, double end, double value, String message, Object... values) {

      if (value <= start || value >= end) {
         throw new CheckFailureException(String.format(message, values));
      }
   }

   public static void exclusiveBetween(long start, long end, long value) {

      if (value <= start || value >= end) {
         throw new CheckFailureException(String.format(DEFAULT_VALUE_OUTSIDE_EXCLUSIVE_RANGE_MESSAGE, value, start, end));
      }
   }

   public static void exclusiveBetween(long start, long end, long value, String message, Object... values) {

      if (value <= start || value >= end) {
         throw new CheckFailureException(String.format(message, values));
      }
   }

   public static <T> void exclusiveBetween(T start, T end, Comparable<T> value) {

      if (value.compareTo(start) <= 0 || value.compareTo(end) >= 0) {
         throw new CheckFailureException(String.format(DEFAULT_VALUE_OUTSIDE_EXCLUSIVE_RANGE_MESSAGE, value, start, end));
      }
   }

   public static <T> void exclusiveBetween(T start, T end, Comparable<T> value, String message, Object... values) {

      if (value.compareTo(start) <= 0 || value.compareTo(end) >= 0) {
         throw new CheckFailureException(String.format(message, values));
      }
   }

   public static void inclusiveBetween(double start, double end, double value) {

      if (value < start || value > end) {
         throw new CheckFailureException(String.format(DEFAULT_VALUE_OUTSIDE_INCLUSIVE_RANGE_MESSAGE, value, start, end));
      }
   }

   public static void inclusiveBetween(double start, double end, double value, String message, Object... values) {

      if (value < start || value > end) {
         throw new CheckFailureException(String.format(message, values));
      }
   }

   public static void inclusiveBetween(long start, long end, long value) {

      if (value < start || value > end) {
         throw new CheckFailureException(String.format(DEFAULT_VALUE_OUTSIDE_INCLUSIVE_RANGE_MESSAGE, value, start, end));
      }
   }

   public static void inclusiveBetween(long start, long end, long value, String message, Object... values) {

      if (value < start || value > end) {
         throw new CheckFailureException(String.format(message, values));
      }
   }

   public static <T> void inclusiveBetween(T start, T end, Comparable<T> value) {

      if (value.compareTo(start) < 0 || value.compareTo(end) > 0) {
         throw new CheckFailureException(String.format(DEFAULT_VALUE_OUTSIDE_INCLUSIVE_RANGE_MESSAGE, value, start, end));
      }
   }

   public static <T> void inclusiveBetween(T start, T end, Comparable<T> value, String message, Object... values) {

      if (value.compareTo(start) < 0 || value.compareTo(end) > 0) {
         throw new CheckFailureException(String.format(message, values));
      }
   }

   public static void matchesPattern(CharSequence input, String pattern) {

      if (!Pattern.matches(pattern, input)) {
         throw new CheckFailureException(String.format(DEFAULT_STRING_DOES_NOT_MATCH_PATTERN_MESSAGE, input, pattern));
      }
   }

   public static void matchesPattern(CharSequence input, String pattern, String message, Object... values) {

      if (!Pattern.matches(pattern, input)) {
         throw new CheckFailureException(String.format(message, values));
      }
   }

   private Check() {

   }
}
