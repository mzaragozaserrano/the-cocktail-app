import buildSrc.src.main.kotlin.Versions

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.thecocktailapp.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.thecocktailapp.app"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
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

    //----- AAR ----------/
    implementation(files(project.rootDir.resolve("libs/mzs-data.aar")))
    implementation(files(project.rootDir.resolve("libs/mzs-domain.aar")))
    implementation(files(project.rootDir.resolve("libs/mzs-presentation.aar")))

    //----- MODULES ----------/
    implementation(project(":data"))
    implementation(project(":domain"))
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

    //----- DAGGER ----------/
    implementation("com.google.dagger:hilt-android:${Versions.hiltVersion}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}")

    //----- TESTING ----------/
    testImplementation("app.cash.turbine:turbine:${Versions.testingTurbine}")
    testImplementation("org.mockito:mockito-core:${Versions.testingMockitoVersion}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.testingCoroutinesVersion}")
    testImplementation("com.google.dagger:hilt-android-testing:${Versions.testingHiltVersion}")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:${Versions.testingHiltVersion}")

}