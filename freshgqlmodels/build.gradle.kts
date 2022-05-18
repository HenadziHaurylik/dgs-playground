import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.netflix.dgs.codegen") version "5.1.7"
    kotlin("jvm") version "1.6.10"
}

group = "com.gena"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.2.1")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask> {
    schemaPaths = mutableListOf("${project.projectDir}/src/main/resources/schema")
    packageName = "com.gena.model.gql"
    generateClient = false
    language = "KOTLIN"
}
