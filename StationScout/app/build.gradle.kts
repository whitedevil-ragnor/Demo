plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.meow.stationscout"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.meow.stationscout"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation ("androidx.compose.material:material:1.6.0")
    implementation ("androidx.navigation:navigation-compose:2.7.4")

    // Core libraries
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.activity:activity-compose:1.9.1")

    // Compose BOM for version management
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))

    // Jetpack Compose libraries
    implementation("androidx.compose.ui:ui") // Core UI
    implementation("androidx.compose.ui:ui-graphics") // Graphics
    implementation("androidx.compose.ui:ui-tooling-preview") // Preview tools
    implementation("androidx.compose.material3:material3:1.2.1") // Material3 UI components

    // Testing dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4") // UI testing

    // Debugging dependencies
    debugImplementation("androidx.compose.ui:ui-tooling") // Debug tools
    debugImplementation("androidx.compose.ui:ui-test-manifest") // Testing manifest
}