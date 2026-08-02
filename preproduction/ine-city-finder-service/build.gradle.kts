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
    implementation("org.jetbrains.kotlinx:dataframe:1.0.0-rc01") {
        /*
         * BUG: Kotlin Dataframe used Slf4j logger under the hood. Seems as bug when program initializes that failed to
         * find a valid provider and shows annoying log warnings messages on log output.
         *
         * Forcing to add Slf4j-NOP provider solve these warnings but still initializes Slf4j as logger (because Kotlin
         * Dataframe used Kotlin Loggin internally).
         *
         * This bug is harmless, only add noise to log output, not compromise the Kotlin Dataframe right execution.
         */
        implementation("org.slf4j:slf4j-nop:2.0.18")
    }
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