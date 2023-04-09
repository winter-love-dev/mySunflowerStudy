package com.example.mysunflowerstudy

object Versions {
    const val kotlin = "1.7.10"
    const val androidxCore = "1.9.0"
    const val appcompat = "1.6.1"
    const val material = "1.8.0"
    const val constraintLayout = "2.1.4"
    const val jvmTarget = "1.8"
    const val androidxFragment = "1.5.0"
    const val androidxNavigation = "2.5.0"
    const val androidxLifecycle = "2.5.0"
    const val viewPager = "1.0.0"
    const val fragment = "1.5.5"
    const val lifeCycle = "2.5.1"
    const val navigation = "2.5.3"
    const val glide = "4.15.1"
    const val gson = "2.10.1"
    const val coroutines = "1.6.1"

    const val retrofit = "2.9.0"
    const val okhttpLogging = "4.9.3"
}

object TestVersion {
    const val junit = "4.13.2"
    const val androidxJunitVersion = "1.1.5"
    const val androidxEspressoCoreVersion = "3.5.1"
}

object Libraries {

    // test
    const val junit = "junit:junit:${TestVersion.junit}"
    const val androidxJunit = "androidx.test.ext:junit:${TestVersion.androidxJunitVersion}"
    const val androidxEspressoCore = "androidx.test.espresso:espresso-core:${TestVersion.androidxEspressoCoreVersion}"

    // jetbrains
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    // google
    const val material = "com.google.android.material:material:${Versions.material}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    // android x
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidxCore}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager}"
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycle}"
    const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}"
    const val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    // network
    const val okhttp3LoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLogging}"
    const val retrofit2ConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"

    //
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
}