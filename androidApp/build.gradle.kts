import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)

    // Firebase — временно отключено: требует google-services.json из Firebase Console.
    // Вернуть, когда файл будет добавлен в androidApp/.
    // alias(libs.plugins.googleServices)

    // Code Quality
    alias(libs.plugins.ktlint)
    alias(libs.plugins.detekt)
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}

dependencies {
    // Shared Module
    implementation(projects.shared)

    // AndroidX
    implementation(libs.androidx.activity.compose)

    // Compose Tooling
    implementation(libs.compose.uiToolingPreview)
    debugImplementation(libs.compose.uiTooling)

    // Code Quality — custom detekt rules
    detektPlugins(projects.detektCustomRules)
}

android {
    namespace = "space.users.four.serphantom"
    compileSdk =
        libs.versions.android.compileSdk
            .get()
            .toInt()

    defaultConfig {
        applicationId = "space.users.four.serphantom"
        minSdk =
            libs.versions.android.minSdk
                .get()
                .toInt()
        targetSdk =
            libs.versions.android.targetSdk
                .get()
                .toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

// region Code Quality

ktlint {
    version.set("1.6.0")
    android.set(true)
    verbose.set(true)
    outputToConsole.set(true)
    filter {
        // Сгенерированный код (Compose Resources и т.п.) не проходит наши правила
        // именования и пересоздаётся при сборке — исключаем его из проверки.
        exclude { element -> element.file.path.contains("/build/generated/") }
    }
}

detekt {
    buildUponDefaultConfig = true
    parallel = true
    config.setFrom(rootProject.files("detekt/detekt.yml"))
}

// endregion
