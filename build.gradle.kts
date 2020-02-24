buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.0-alpha09")
        classpath(kotlin("gradle-plugin", version = "1.3.61"))
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.2.1")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

subprojects {
    if (project.name == "app") {
        apply(plugin = "com.android.application")
    } else {
        apply(plugin = "com.android.library")
    }
    apply(plugin = "kotlin-android")
    apply(plugin = "kotlin-android-extensions")

//    module {
//        compileSdkVersion(Versions.COMPILE_SDK)
//        defaultConfig {
//            minSdkVersion(Versions.MIN_SDK)
//            targetSdkVersion(Versions.TARGET_SDK)
//            versionCode = Versions.CODE
//            versionName = Versions.NAME
//            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//            consumerProguardFiles("consumer-rules.pro")
//        }
//    }
}

//subprojects {
//    module {
//        compileSdkVersion(Versions.COMPILE_SDK)
//        defaultConfig {
//            minSdkVersion(Versions.MIN_SDK)
//            targetSdkVersion(Versions.TARGET_SDK)
//            versionCode = Versions.CODE
//            versionName = Versions.NAME
//            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//            consumerProguardFiles("consumer-rules.pro")
//        }
//    }
//}

task<Delete>("clean") { delete(rootProject.buildDir) }