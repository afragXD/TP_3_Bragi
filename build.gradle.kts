import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.10"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation ("org.seleniumhq.selenium:selenium-java:4.15.0")
    implementation ("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    implementation ("io.ktor:ktor-client-core:2.3.6")
    implementation ("io.ktor:ktor-client-cio:2.3.6")

    implementation("com.google.code.gson:gson:2.8.6")

    implementation("org.jsoup:jsoup:1.13.1")

    implementation("ch.qos.logback:logback-classic:1.2.3")

    implementation("com.datastax.oss:java-driver-core:4.17.0")
    implementation("com.datastax.oss:java-driver-query-builder:4.17.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}