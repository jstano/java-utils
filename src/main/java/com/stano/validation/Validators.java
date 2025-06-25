package com.stano.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

   private static final String PHONE_NUMBER_PATTERN = ".{7,100}";
   private static final String EMAIL_PATTERN = ".+@.+";

   public static boolean validateEmail(String email) {

      return validateWithRegex(email, EMAIL_PATTERN);
   }

   public static boolean validatePhoneNumber(String phoneNumber) {

      return validateWithRegex(phoneNumber, PHONE_NUMBER_PATTERN);
   }

   private static boolean validateWithRegex(String value, String pattern) {

      Pattern patternRegex = Pattern.compile(pattern);
      Matcher matcher = patternRegex.matcher(value);
      return matcher.matches();
   }
}
