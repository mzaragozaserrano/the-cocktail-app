import buildSrc.src.main.kotlin.Versions

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.mzaragozaserrano.ui"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    //----- AAR ----------/
    implementation(files(project.rootDir.resolve("libs/mzs-domain.aar")))
    implementation(files(project.rootDir.resolve("libs/mzs-presentation.aar")))

    //----- MODULES ----------/
    implementation(project(":domain"))

    //----- ANDROIDX ----------/
    implementation("androidx.appcompat:appcompat:${Versions.appCompatVersion}")
    implementation("androidx.core:core-ktx:1.12.0")

    //----- COMPOSE ----------/
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:${Versions.navigationComposeVersion}")
    implementation(platform("androidx.compose:compose-bom:${Versions.composeVersion}"))
    debugImplementation("androidx.compose.ui:ui-tooling")

    //----- GOOGLE ----------/
    implementation("com.google.android.material:material:${Versions.materialVersion}")
    implementation("com.google.dagger:hilt-android:${Versions.hiltVersion}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}")

}

kapt {
    correctErrorTypes = true
}