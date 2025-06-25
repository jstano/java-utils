package com.stano.validation

import spock.lang.Specification
import spock.lang.Unroll

class ValidatorsTest extends Specification {

   @Unroll
   def "phone number validation regex handles a variety of phone number patterns (#phone)"() {
      expect:
      Validators.validatePhoneNumber(phone) == shouldMatch

      where:
      phone                                                 | shouldMatch
      "214-555-1212"                                        | true
      "214-555-1213"                                        | true
      "(202) 555-0125"                                      | true
      "(202)555-0125"                                       | true
      "2145551212"                                          | true
      "12145551212"                                         | true
      "+12145551212"                                        | true
      "636 856 789"                                         | true
      "1636 856 789"                                        | true
      "1 636 856 789"                                       | true
      "+111 (202) 555-0125"                                 | true
      "+11 636 85 67 89"                                    | true
      "636 856 789"                                         | true
      "12345678"                                            | true
      "z636 856 789"                                        | true
      "12345"                                               | false
   }

   @Unroll
   def "email validation regex handles a variety of email patterns (#email)"() {
      expect:
      Validators.validateEmail(email) == shouldMatch

      where:
      email                                                                      | shouldMatch
      "email@example.com"                                                        | true
      "firstname.lastname@example.com"                                           | true
      "email@subdomain.example.com"                                              | true
      "firstname+lastname@example.com"                                           | true
      "email@123.123.123.123"                                                    | true
      "email@[123.123.123.123]"                                                  | true
      "\"email\"@example.com"                                                    | true
      "1234567890@example.com"                                                   | true
      "email@example-one.com"                                                    | true
      "_______@example.com"                                                      | true
      "email@example.name"                                                       | true
      "email@example.museum"                                                     | true
      "email@example.co.jp"                                                      | true
      "firstname-lastname@example.com"                                           | true
      "much.”more\\ unusual”@example.com"                                        | true
      "very.unusual.”@”.unusual.com@example.com"                                 | true
      "very.”(),:;<>[]”.VERY.”very@\\\\ \" very ”.unusual @strange.example.com " | true
      "#@%^%#\$@#\$@#.com"                                                       | true
      "Joe Smith <email@example.com>"                                            | true
      ".email@example.com"                                                       | true
      "email.@example.com"                                                       | true
      "email..email@example.com"                                                 | true
      "あいうえお@example.com"                                                        | true
      "email@example.com (Joe Smith)"                                            | true
      "email@example"                                                            | true
      "email@-example.com"                                                       | true
      "email@example.web"                                                        | true
      "email@111.222.333.44444"                                                  | true
      "email@example..com"                                                       | true
      "Abc..123@example.com"                                                     | true
      "”(),:;<>[\\]@example.com"                                                 | true
      "just”not”right@example.com"                                               | true
      "@example.com"                                                             | false
      "email.example.com"                                                        | false
      "asdf"                                                                     | false
   }
}
