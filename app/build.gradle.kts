import buildSrc.src.main.kotlin.Versions

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.mzaragozaserrano.thecocktailapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mzaragozaserrano.thecocktailapp"
        minSdk = 24
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    //----- SUBMODULES ----------/
    implementation(project(":presentation"))

    //----- COMPOSE ----------/
    implementation("androidx.activity:activity-compose:$Versions.activityComposeVersion")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:${Versions.navigationComposeVersion}")
    implementation(platform("androidx.compose:compose-bom:${Versions.composeVersion}"))
    debugImplementation("androidx.compose.ui:ui-tooling")

}