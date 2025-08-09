plugins {
    id("org.springframework.boot") version "3.5.4"
    id("io.spring.dependency-management") version "1.1.4"
    java
}

group = "com.github.bismastr.sharedsecrets"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

extra["springdocVersion"] = "2.5.0"
extra["flywayVersion"] = "11.10.5"
val testcontainersVersion = "1.19.7"

dependencies {
    // Spring Boot dependencies
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
    //Spring boot starters
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    //Flyway
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")
    // API documentation
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${property("springdocVersion")}")
    runtimeOnly("org.postgresql:postgresql:42.7.3")
    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    // Test with Lombok support
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")
    // TestNG
    testImplementation("org.testng:testng:7.8.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // Testcontainers with JUnit 5 integration
    testImplementation("org.testcontainers:junit-jupiter:${testcontainersVersion}")
    testImplementation("org.testcontainers:postgresql:${testcontainersVersion}")
}

tasks.test {
    useJUnitPlatform()
}

springBoot {
    mainClass = "com.github.bismastr.sharedsecrets.Main"
}