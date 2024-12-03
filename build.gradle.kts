import org.springframework.boot.gradle.tasks.bundling.BootJar

tasks.getByName<BootJar>("bootJar") { enabled = true; archiveBaseName.set(rootProject.name) }
tasks.getByName<Jar>("jar") { enabled = false }
plugins {
    id("org.springframework.boot") version "3.3.6"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.serialization") version "2.0.0"
    kotlin("plugin.spring") version "2.0.0"
}

group = "com.everbald"
version = "0.0.1"
description = "folobot.lite"

repositories {
    mavenCentral()
}

ext {
    set("springCloudVersion", "2023.0.3")
}

dependencies {
    // swagger
    implementation("io.swagger.core.v3:swagger-annotations:2.2.26")
    // feign
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.1.2")
    // telegram api
    implementation("org.telegram:telegrambots-longpolling:7.4.1")
    implementation("org.telegram:telegrambots-client:7.4.0")
    // serialisation
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.1")
    // logging
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xjsr305=strict")
    }
    jvmToolchain(19)
}