plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("com.google.dagger.hilt.android")
    id("kotlinx-serialization")

}

android {
    namespace = "com.example.recipeapppaparaproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.recipeapppaparaproject"
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)






    implementation ("com.airbnb.android:lottie-compose:6.4.0")
    implementation ("androidx.navigation:navigation-compose:2.5.3")
    implementation ("com.airbnb.android:lottie-compose:6.0.0")


    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation ("androidx.activity:activity-compose:1.5.1")

    //Firebase
    implementation(platform(libs.firebase.bom))
    implementation (libs.firebase.auth)

    // Coroutines
    implementation(libs.coroutines.play.services)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    kapt(libs.hilt.androidx.compiler)
    implementation(libs.hilt.navigation.compose)

    // Splash
    implementation ("androidx.compose.ui:ui:1.4.2")
    implementation ("androidx.compose.material:material:1.4.2")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.4.2")
    implementation ("androidx.navigation:navigation-compose:2.5.3")

    // Lottie
    implementation ("com.airbnb.android:lottie-compose:6.0.0")

    // Register
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation ("androidx.activity:activity-compose:1.5.1")

    // Retrofit
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.kotlin.serialization)

    // KotlinX Serialization
    implementation(libs.kotlinx.serialization.json)



    // Coil
    implementation(libs.coil.compose)


    // Jsoup
    implementation(libs.jsoup)

}