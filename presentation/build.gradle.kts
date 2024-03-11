plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {

    namespace = "com.thecocktailapp.presentation"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    flavorDimensions += "architecture"

    productFlavors {
        create("mvi") {
            dimension = "architecture"
        }

        create("mvvm") {
            dimension = "architecture"
        }
    }

}

dependencies {

    //----- MODULES ----------/
    implementation(project(":core"))
    implementation(project(":domain"))

    //----- ANDROIDX ----------/
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.navigation.fragment.ktx)

    //----- COIL ----------/
    implementation(libs.coil.kt.compose)

    //----- COMPOSE ----------/
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui.util)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)

    //----- DAGGER ----------/
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    //----- GLIDE ----------/
    implementation(libs.glide)

    //-----  LOTTIE ----------/
    implementation(libs.lottie)
    implementation(libs.lottie.compose)

    //----- MATERIAL ----------/
    implementation(libs.material3)

}