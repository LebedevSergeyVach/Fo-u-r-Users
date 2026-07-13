plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader

    // Android
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidMultiplatformLibrary) apply false

    // Kotlin
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinSerialization) apply false

    // Room + KSP
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.room) apply false

    // Compose
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false

    // Firebase
    alias(libs.plugins.googleServices) apply false

    // Code Quality
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.detekt) apply false
}