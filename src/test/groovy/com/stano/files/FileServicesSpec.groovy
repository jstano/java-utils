package com.stano.files

import spock.lang.Specification

class FileServicesSpec extends Specification {
  void testCreateTempFile() {
    def fileServices = new FileServices()

    when:
    def file = fileServices.createTempFile("ABC", "XYZ")

    then:
    file.exists()

    cleanup:
    file.delete()
  }

  void testCreateFileWriter() {
    def fileServices = new FileServices()
    def file = fileServices.createTempFile("ABC", "XYZ")

    when:
    def fileWriter = fileServices.createFileWriter(file)

    then:
    fileWriter != null

    cleanup:
    fileWriter.close()
    file.delete()
  }
}
