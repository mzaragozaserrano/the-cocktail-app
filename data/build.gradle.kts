import buildSrc.src.main.kotlin.Versions

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {

    namespace = "com.mzaragozaserrano.data"
    compileSdk = 33

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

}

dependencies {

    //----- AAR ----------/
    implementation(files(project.rootDir.resolve("libs/mzs-data.aar")))
    implementation(files(project.rootDir.resolve("libs/mzs-domain.aar")))

    //----- MODULES ----------/
    implementation(project(":domain"))

    //----- COROUTINES ----------/
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutinesVersion}")

    //----- DAGGER ----------/
    implementation("com.google.dagger:hilt-android:${Versions.hiltVersion}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}")

    //-----  JSON PARSER ----------/
    implementation("com.squareup.moshi:moshi:${Versions.moshiVersion}")
    implementation("com.squareup.moshi:moshi-kotlin:${Versions.moshiVersion}")

    //----- OKHTTP ----------/
    implementation("com.squareup.okhttp3:okhttp:${Versions.okHttp3Version}")

}