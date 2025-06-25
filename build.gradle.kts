plugins {
  id("java-library")
  id("groovy")
  id("jacoco")
  id("org.sonarqube") version "6.2.0.5505"
  id("maven-publish")
  id("signing")
}

dependencies {
  implementation("com.h2database:h2:2.3.232")
  implementation("com.microsoft.sqlserver:mssql-jdbc:12.10.1.jre11")
  implementation("commons-cli:commons-cli:1.9.0")
  implementation("commons-io:commons-io:2.19.0")
  implementation("io.opentelemetry.instrumentation:opentelemetry-jdbc:2.16.0-alpha")
  implementation("mysql:mysql-connector-java:8.0.33")
  implementation("org.apache.commons:commons-collections4:4.5.0")
  implementation("org.apache.commons:commons-dbcp2:2.13.0")
  implementation("org.apache.commons:commons-lang3:3.17.0")
  implementation("org.apache.groovy:groovy-all:4.0.27")
  implementation("org.hsqldb:hsqldb:2.7.4")
  implementation("org.jooq:joor-java-8:0.9.15")
  implementation("org.postgresql:postgresql:42.7.7")
  implementation("org.slf4j:slf4j-api:2.0.17")

  testImplementation("net.bytebuddy:byte-buddy:1.17.6")
  testImplementation("org.junit.jupiter:junit-jupiter:5.13.2")
  testImplementation("org.junit.platform:junit-platform-launcher:1.13.2")
  testImplementation("org.mockito:mockito-junit-jupiter:5.18.0")
  testImplementation("org.spockframework:spock-core:2.3-groovy-4.0")

  testImplementation(files("src/test/resources/test-jar1.jar"))
  testImplementation(files("src/test/resources/test-jar2.jar"))
  testImplementation(files("src/test/resources/test-jarjar.jar"))
}

publishing {
  publications {
    create<MavenPublication>("mavenJava") {
      from(components["java"])

      pom {
        name.set("Schema Model")
        description.set("A virtual model for relational database schemas.")
        url.set("https://github.com/jstano/java-schema")

        licenses {
          license {
            name.set("The MIT License")
            url.set("https://opensource.org/license/mit")
          }
        }

        developers {
          developer {
            id.set("jstano")
            name.set("Jeff Stano")
            email.set("jeff@stano.com")
          }
        }

        scm {
          connection.set("scm:git:https://github.com/jstano/java-schema.git")
          developerConnection.set("scm:git:ssh://git@github.com:jstano/java-schema.git")
          url.set("https://github.com/jstano/java-schema")
        }
      }
    }
  }

  repositories {
    maven {
      name = "sonatype"
      val releasesRepoUrl = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
      val snapshotsRepoUrl = uri("https://oss.sonatype.org/content/repositories/snapshots/")
      url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl

      credentials {
        username = findProperty("ossrhUsername") as String?
        password = findProperty("ossrhPassword") as String?
      }
    }
  }
}

signing {
  useInMemoryPgpKeys(
    findProperty("signing.keyId") as String?,
    findProperty("signing.key") as String?,
    findProperty("signing.password") as String?
  )
  sign(publishing.publications["mavenJava"])
}

sonar {
  val sonarHost = "http://localhost:9000"
  val sonarToken = "sqa_85bebeecde7b7f43bbbcdf9e9f6d57e1f7c5fabd"

  properties {
    property("sonar.host.url", sonarHost)
    property("sonar.token", sonarToken)
    property("sonar.projectName", "java-utils")
    property("sonar.projectKey", "${project.group}:java-utils")
    property("sonar.projectVersion", project.version)
  }
}

configurations {
  all {
    exclude(group = "commons-logging", module = "commons-logging")
  }
}

tasks.withType<JavaCompile>().configureEach {
  options.compilerArgs = compilerOptions()
  sourceCompatibility = "21"
  targetCompatibility = "21"
}
tasks.withType<GroovyCompile>().configureEach {
  options.compilerArgs = compilerOptions()
  sourceCompatibility = "21"
  targetCompatibility = "21"
  groovyOptions.setParameters(true)
}

java {
  withJavadocJar()
  withSourcesJar()
}

tasks.withType<Jar> {
  exclude("**/.gitkeep")
}
tasks.withType<Javadoc>().configureEach {
  (options as CoreJavadocOptions).addStringOption("Xdoclint:none", "-quiet")
}
tasks.withType<Test>().configureEach {
  useJUnitPlatform()
  jvmArgs("--add-opens", "java.base/java.lang.reflect=ALL-UNNAMED", "--add-opens", "java.base/java.lang=ALL-UNNAMED")
  finalizedBy("jacocoTestReport")
}
tasks.withType<JacocoReport>().configureEach {
  reports {
    html.required.set(true)
    xml.required.set(true)
  }
}

fun compilerOptions(): List<String> = listOf("-Xlint:none", "-Xdoclint:none", "-nowarn", "-parameters")
