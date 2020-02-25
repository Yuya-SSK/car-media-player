import com.android.build.gradle.BaseExtension

fun BaseExtension.common() {
    compileSdkVersion(Versions.COMPILE_SDK)
    defaultConfig {
        minSdkVersion(Versions.MIN_SDK)
        targetSdkVersion(Versions.TARGET_SDK)
        versionCode = Versions.CODE
        versionName = Versions.NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}