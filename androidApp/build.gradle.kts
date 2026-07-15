import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)

    // Type-safe навигация: @Serializable-маршруты
    alias(libs.plugins.kotlinSerialization)

    // Firebase — временно отключено: требует google-services.json из Firebase Console.
    // Вернуть, когда файл будет добавлен в androidApp/.
    // alias(libs.plugins.googleServices)

    // Code Quality
    alias(libs.plugins.ktlint)
    alias(libs.plugins.detekt)
}

// Единая версия приложения из каталога версий (gradle/libs.versions.toml).
val appVersionName: String =
    libs.versions.app.versionName
        .get()

// Метка времени сборки в формате yyyy-MM-dd_HH-mm-ss. Вычисляется на этапе
// конфигурации — каждая новая сборка получает свежее значение (это намеренно
// делает имя APK уникальным и отключает переиспользование configuration cache).
val buildTimestamp: String =
    LocalDateTime
        .now()
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"))

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}

dependencies {
    // Shared Module (общая логика, без UI)
    implementation(projects.shared)

    // AndroidX
    implementation(libs.androidx.activity.compose)

    // Compose (UI)
    implementation(libs.compose.runtime)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui)
    implementation(libs.compose.uiToolingPreview)
    debugImplementation(libs.compose.uiTooling)

    // Navigation (Compose)
    implementation(libs.navigation.compose)

    // Lifecycle — Compose-обвязка (viewModel(), collectAsStateWithLifecycle)
    implementation(libs.androidx.lifecycle.viewmodelCompose)
    implementation(libs.androidx.lifecycle.runtimeCompose)

    // Koin — DI + Compose-интеграция
    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.compose)
    implementation(libs.koin.compose.viewmodel)
    implementation(libs.koin.compose.viewmodel.navigation)

    // KtorScope — Compose-экран инспектора сети
    implementation(libs.ktorscope.compose)

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
        versionCode =
            libs.versions.app.versionCode
                .get()
                .toInt()
        versionName = appVersionName
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("debug") {
            // Отдельный applicationId, чтобы debug и release ставились рядом.
            applicationIdSuffix = ".debug"
            isMinifyEnabled = false
            isDebuggable = true
        }
        getByName("release") {
            isMinifyEnabled = false
            isDebuggable = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

// Имя выходного APK: FourUsers_<вид>_v<версия>_<дата>_<время>.apk
// Например: FourUsers_release_v1.0_2026-07-13_14-30-05.apk
androidComponents {
    onVariants { variant ->
        variant.outputs.forEach { output ->
            val apkName = "FourUsers_${variant.name}_v${appVersionName}_$buildTimestamp.apk"
            (output as? com.android.build.api.variant.impl.VariantOutputImpl)
                ?.outputFileName
                ?.set(apkName)
        }
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
