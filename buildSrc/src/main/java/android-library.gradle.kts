//plugins {
//    id("com.android.library")
//    id("kotlin-android")
//}
//
//android {
//    compileSdkVersion(Versions.COMPILE_SDK)
//    defaultConfig {
//        minSdkVersion(Versions.MIN_SDK)
//        targetSdkVersion(Versions.TARGET_SDK)
//        versionCode = Versions.CODE
//        versionName = Versions.NAME
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles("consumer-rules.pro")
//    }
//    buildTypes {
//        getByName("release") {
//            isMinifyEnabled = false
//            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
//        }
//    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
//}