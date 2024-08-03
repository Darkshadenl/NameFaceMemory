plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).version("8.3.0").apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.kotlinCocoapods).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    id("com.google.gms.google-services") version "4.4.2" apply false
}
