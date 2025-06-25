package com.stano.changecase

import spock.lang.Specification

class SentenceCaseSpec extends Specification {

   def "sentenceCase"() {

      expect:
      SentenceCase.sentenceCase(text) == result

      where:
      text             | result
      null             | null
      ""               | ""
      "test"           | "Test"
      "test string"    | "Test string"
      "Test String"    | "Test string"
      "TestV2"         | "Test v2"
      "version 1.2.10" | "Version 1 2 10"
      "version 1.21.0" | "Version 1 21 0"
   }
}
