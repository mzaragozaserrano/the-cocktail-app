plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {

    namespace = "com.thecocktailapp.data"
    compileSdk = 35

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

    implementation(project(":core"))
    //----- MODULES ----------/
    implementation(project(":domain"))

    //----- COROUTINES ----------/
    implementation(libs.kotlinx.coroutines.android)

    //-----  JSON PARSER ----------/
    implementation(libs.moshi.kotlin)

    //----- KOIN ----------/
    implementation(libs.koin.android)

    //----- OKHTTP ----------/
    implementation(libs.okhttp.logging)

    //----- ROOM ----------/
    implementation(libs.room)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

}