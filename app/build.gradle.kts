plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "bsu.pi_13.flowers_team"
    compileSdk = 35

    defaultConfig {
        applicationId = "bsu.pi_13.flowers_team"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.junit.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation (libs.material3)
    implementation (libs.androidx.material3.v132)

    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    implementation (libs.logging.interceptor)
    implementation (libs.coil.compose)

    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    testImplementation (libs.junit)
    testImplementation (libs.mockito.core)
    testImplementation (libs.mockito.kotlin)
    testImplementation (libs.mockk)
    testImplementation (libs.robolectric)

    androidTestImplementation (libs.androidx.junit)
    androidTestImplementation (libs.androidx.espresso.core)
    androidTestImplementation (libs.ui.test.junit4)
    debugImplementation (libs.ui.test.manifest)

    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

}

configurations.all {
    resolutionStrategy {
        force(   "org.bouncycastle:bcprov-jdk18on:1.78",
            "io.netty:netty-handler:4.1.118.Final",
            "io.netty:netty-codec-http2:4.1.108.Final",
            "commons-io:commons-io:2.14.0",
            "com.google.protobuf:protobuf-java:3.25.5",
            "com.google.code.gson:gson:2.8.9",
            "com.google.guava:guava:32.0.1-android",
            "org.apache.commons:commons-compress:1.26.0",
            "com.squareup.okio:okio:3.4.0",
            "com.squareup.okio:okio-jvm:3.4.0")
    }
}