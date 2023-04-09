import com.example.mysunflowerstudy.Libraries
import com.example.mysunflowerstudy.Configuration

plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    namespace = Configuration.applicationId

    compileSdk = Configuration.compileSdk
    defaultConfig {
        applicationId = Configuration.applicationId

        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            sourceSets {
                getByName("main") {
                    java.srcDir(File("build/generated/ksp/debug/kotlin"))
                }
            }
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            sourceSets {
                getByName("main") {
                    java.srcDir(File("build/generated/ksp/release/kotlin"))
                }
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // test
    implementation(Libraries.junit)
    testImplementation(Libraries.junit)
    androidTestImplementation(Libraries.androidxJunit)
    androidTestImplementation(Libraries.androidxEspressoCore)

    // jetbrains
    // implementation(Libraries.kotlinSerialization)
    implementation(Libraries.coroutinesCore)
    implementation(Libraries.coroutinesAndroid)

    // google
    implementation(Libraries.material)
    implementation(Libraries.gson)

    // android x
    implementation(Libraries.androidxCore)
    implementation(Libraries.appcompat)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.viewPager2)
    implementation(Libraries.fragment)
    implementation(Libraries.livedata)
    implementation(Libraries.viewmodel)
    implementation(Libraries.navigation)
    implementation(Libraries.navigationUi)

    // network
    implementation(Libraries.okhttp3LoggingInterceptor)
    implementation(Libraries.retrofit2ConverterGson)
    implementation(Libraries.retrofit)

    //
    implementation(Libraries.glide)
}
