package com.example.mysunflowerstudy

object Versions {

    const val androidxCore = "1.9.0"
    const val appcompat = "1.6.1"
    const val material = "1.8.0"
    const val constraintLayout = "2.1.4"

    const val kotlin = "1.7.10"

    const val jvmTarget = "1.8"
    const val androidxFragment = "1.5.0"
    const val androidxNavigation = "2.5.0"
    const val androidxLifecycle = "2.5.0"
}

object TestVersion {
    const val junit = "4.13.2"
    const val extJunit = "1.1.5"
    const val espressoCore = "3.5.1"
}

object Libraries {
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidxCore}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val junit = "junit:junit:${TestVersion.junit}"
    const val extJunit = "androidx.test.ext:${TestVersion.extJunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${TestVersion.espressoCore}"

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
}