package com.stano.changecase;

import static com.stano.changecase.NoCase.noCase;
import static com.stano.changecase.UpperCase.upperCaseTransform;

public class ConstantCase {
  /**
   * Transform into upper case string with an underscore between words.
   * "test string" becomes "TEST_STRING"
   *
   * @param text The text to transform
   * @return The transformed text
   */
  public static String constantCase(String text) {
    return noCase(text, Options.options()
                               .withDelimiter("_")
                               .withTransform(upperCaseTransform));
  }
}
