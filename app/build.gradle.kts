@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.universitieslist"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.universitieslist"
        minSdk = 24
        targetSdk = 33
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
    kotlin {
        jvmToolchain(17)
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/*"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycleRuntime)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.timber)
    implementation(libs.coil)
    implementation(libs.okHttp3)
    implementation(libs.okHttp3Logging)
    implementation(libs.lifecycleViewmodel)
    implementation(libs.lifecycleLivedata)
    implementation(libs.retrofit)
    implementation(libs.retrofitMoshiConverter)
    implementation(libs.moshi)
    implementation(libs.moshiCodegen)
    implementation(libs.roomKtx)
    implementation(libs.roomRuntime)
    implementation(libs.hiltAndroid)
    implementation(libs.hiltNavigation)

    ksp(libs.moshiCodegen)
    ksp(libs.hiltCompiler)
    ksp(libs.roomCompiler)

    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    testImplementation(libs.junit)
    testImplementation(libs.coroutinesTest)
    testImplementation(libs.mockk)
    testImplementation(libs.roomTesting)
    testImplementation(libs.robolectric)
    testImplementation(libs.turbine)

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(libs.mockkAndroid)
}
