import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.kotlinSerialization)

    // Room (локальная БД, KMP)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)

    // Swift interop — мост общей ViewModel → SwiftUI (Flow/suspend → Swift)
    alias(libs.plugins.skie)

    // Code Quality
    alias(libs.plugins.ktlint)
    alias(libs.plugins.detekt)
}

kotlin {
    // Room KMP использует `expect object` для конструктора БД — гасим Beta-предупреждение
    // KT-61573 про expect/actual-классы (рекомендация Room).
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    android {
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
            // Koin (DI) — ядро без Compose
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)

            // Общая ViewModel — базовый lifecycle-viewmodel (без Compose), пригодна для SwiftUI
            implementation(libs.androidx.lifecycle.viewmodel)

            // Ktor (Networking)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)

            // KtorScope — Ktor-плагин захвата запросов (UI-экран инспектора — в androidMain)
            implementation(libs.ktorscope.ktor)

            // Room (локальная БД, KMP) + bundled SQLite driver
            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)

            // Kotlinx
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines.core)

            // Логирование (мультиплатформенное)
            implementation(libs.kermit)

            // Firebase (GitLive SDK)
            implementation(libs.firebase.analytics)
            implementation(libs.firebase.config)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)

            // Тестирование корутин и Flow
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.turbine)
        }
    }
}

// Room: каталог для экспорта схем БД (нужен для миграций и тестов схемы).
room {
    schemaDirectory("$projectDir/schemas")
}

// SKIE: отключаем отправку анонимной аналитики в Touchlab (приватность сборки).
skie {
    analytics {
        enabled.set(false)
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
    // Room compiler (KSP) — по одному на каждый таргет KMP.
    add("kspAndroid", libs.room.compiler)
    add("kspIosArm64", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.room.compiler)

    // Code Quality — custom detekt rules
    detektPlugins(projects.detektCustomRules)
}
