plugins {
    alias(libs.plugins.android.library)
    id("com.google.devtools.ksp")

    // Hilt
    id("com.google.dagger.hilt.android")

    // Secrets Gradle Plugin
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")

    // Dataframe
    kotlin("plugin.dataframe") version "2.4.10"
}

android {
    namespace = "com.visualstudioex3.application"
    compileSdk {
        version = release(37) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 34

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.lifecycle.viewmodel.compose)
    ksp(libs.hilt.android.compiler)

    // Dataframe
    implementation(libs.dataframe)
    implementation(libs.dataframe.core)
    implementation(libs.dataframe.excel)
    /*
     * BUG: Kotlin Dataframe used Slf4j logger under the hood. Seems as bug when program initializes that failed to
     * find a valid provider and shows annoying log warnings messages on log output.
     *
     * Forcing to add Slf4j-NOP provider solve these warnings but still initializes Slf4j as logger (because Kotlin
     * Dataframe used Kotlin Loggin internally).
     *
     * This bug is harmless, only add noise to log output, not compromise the Kotlin Dataframe right execution.
     *
     * Possible propper solution seems to be implemented in library side ():
     * https://youtrack.jetbrains.com/projects/KTNB/issues/KTNB-1198/Log4j-reports-no-logger-dependency-when-dataframe-excel-is-used
     */
    implementation(libs.slf4j.nop)
}

secrets {
    propertiesFileName = "secrets.properties"
    /*
     * FYI: secrets.properties file is not showed on "Gradle Scripts" folder and neither on "app"
     * folder when "Android" project view is active. Change view to "Project Files" to discover the
     * file in the root level of project folder.
     *
     * The key bridge (the key-value reflected in runtime) is created as meta-data element in
     * AndroidManifest.xml file.
     */
}
