plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)

    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.sqlDelightPlugin)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                implementation(compose.ui)
                implementation(compose.runtime)
                implementation(compose.animation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.foundation)

                implementation(libs.bundles.ktor)
                implementation(libs.bundles.precompose)
                implementation(libs.bundles.sqldelight)

                implementation(libs.koin.core)
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.serialization)
                implementation(libs.calf.filePicker)
                implementation(libs.moriMoshi.core)
                implementation(libs.mokoBiometry.core)
                implementation(libs.mokoLocation.core)
                implementation(libs.multiplatformSettings.noArg)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
        val androidMain by getting {
            dependencies {
                //put your android dependencies here
                api(compose.preview)
                api(compose.uiTooling)
                implementation(libs.androidx.core)
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.activity.compose)
                implementation(libs.koin.android)
                implementation(libs.ktor.android)
                implementation(libs.sqlDelight.android)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.ktor.ios)
                implementation(libs.sqlDelight.native)
            }
        }
    }
}

android {
    namespace = libs.versions.project.namespace.get() + ".common"
    compileSdk = libs.versions.android.sdk.compile.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig { minSdk = libs.versions.android.sdk.min.get().toInt() }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.android.kotlin.compiler.get()
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}
