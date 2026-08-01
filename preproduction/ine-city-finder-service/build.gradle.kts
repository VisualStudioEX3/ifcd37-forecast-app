plugins {
    kotlin("jvm") version "2.4.0"
    kotlin("plugin.dataframe") version "2.4.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:dataframe:1.0.0-rc01")
    implementation("org.jetbrains.kotlinx:dataframe-core:1.0.0-rc01")
    implementation("org.jetbrains.kotlinx:dataframe-excel:1.0.0-rc01") {
        exclude("org.jetbrains.kotlinx", "dataframe-json")
    }
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}