import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    jvm {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }

    sourceSets {
        jvmMain.dependencies {
            // Detekt API — custom rules depend only on detekt-api (not the full detekt)
            compileOnly(libs.detekt.api)
        }
        jvmTest.dependencies {
            implementation(libs.detekt.test)
            implementation(libs.kotlin.test)
        }
    }
}
