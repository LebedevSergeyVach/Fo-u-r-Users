import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)

    // Code Quality
    alias(libs.plugins.ktlint)
    alias(libs.plugins.detekt)
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    androidLibrary {
        namespace = "space.users.four.serphantom.shared"
        compileSdk =
            libs.versions.android.compileSdk
                .get()
                .toInt()
        minSdk =
            libs.versions.android.minSdk
                .get()
                .toInt()

        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
        androidResources {
            enable = true
        }
        withHostTest {
            isIncludeAndroidResources = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            // Compose
            implementation(libs.compose.uiToolingPreview)

            // Ktor (Android Engine)
            implementation(libs.ktor.client.okhttp)

            // Firebase Android BOM — задаёт версии транзитивных com.google.firebase:*
            // артефактов, которые GitLive Firebase SDK объявляет без версии.
            implementation(project.dependencies.platform(libs.firebase.bom))
        }
        iosMain.dependencies {
            // Ktor (iOS Engine)
            implementation(libs.ktor.client.darwin)
        }
        commonMain.dependencies {
            // Compose
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)

            // Navigation
            implementation(libs.navigation.compose)

            // Lifecycle
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            // Koin (DI)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.navigation)

            // Ktor (Networking)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)

            // Kotlinx
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines.core)

            // Firebase (GitLive SDK)
            implementation(libs.firebase.analytics)
            implementation(libs.firebase.config)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
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

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)

    // Code Quality — custom detekt rules
    detektPlugins(projects.detektCustomRules)
}
