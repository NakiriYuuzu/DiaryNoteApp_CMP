plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
}

kotlin {
    jvmToolchain(17)
    androidTarget()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(projects.shared)
                api(libs.androidx.core)
                api(libs.androidx.appcompat)
                api(libs.androidx.activity.compose)
            }
        }
    }
}

android {
    compileSdk = libs.versions.android.sdk.compile.get().toInt()
    namespace = libs.versions.project.namespace.get()

    sourceSets["main"].manifest.srcFile("src/Main/AndroidManifest.xml")

    defaultConfig {
        applicationId = libs.versions.project.namespace.get()
        minSdk = libs.versions.android.sdk.min.get().toInt()
        targetSdk = libs.versions.android.sdk.target.get().toInt()
        versionCode = libs.versions.project.version.code.get().toInt()
        versionName = libs.versions.project.version.name.get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
        }
    }

    buildFeatures {
        buildConfig = true
    }
}