import buildSrc.src.main.kotlin.Versions

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {

    namespace = "com.thecocktailapp.ui"
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

    //----- AAR ----------/
    implementation(files(project.rootDir.resolve("libs/mzs-domain.aar")))
    implementation(files(project.rootDir.resolve("libs/mzs-presentation.aar")))

    //----- MODULES ----------/
    implementation(project(":domain"))

    //----- ANDROIDX ----------/
    implementation("androidx.core:core-ktx:${Versions.androidxCoreVersion}")
    implementation("androidx.appcompat:appcompat:${Versions.appCompatVersion}")
    implementation("androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}")
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}")

    //----- COMPOSE ----------/
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("io.coil-kt:coil-compose:${Versions.coilCompose}")
    implementation("com.airbnb.android:lottie-compose:${Versions.lottieComposeVersion}")
    implementation("androidx.hilt:hilt-navigation-compose:${Versions.hiltComposeVersion}")
    implementation("androidx.navigation:navigation-compose:${Versions.navigationComposeVersion}")
    implementation("androidx.compose.runtime:runtime-livedata:${Versions.liveDataComposeVersion}")
    implementation(platform("androidx.compose:compose-bom:${Versions.composeVersion}"))
    debugImplementation("androidx.compose.ui:ui-tooling")

    //----- DAGGER ----------/
    implementation("com.google.dagger:hilt-android:${Versions.hiltVersion}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}")

    //----- GLIDE ----------/
    implementation("com.github.bumptech.glide:glide:${Versions.glideVersion}")

    //-----  LOTTIE ----------/
    implementation("com.airbnb.android:lottie:${Versions.lottieVersion}")

    //----- MATERIAL ----------/
    implementation("com.google.android.material:material:${Versions.materialVersion}")

}