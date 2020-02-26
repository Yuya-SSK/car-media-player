plugins {
    `android-application`
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
}

android {
    defaultConfig {
        applicationId = "com.ysp.camep"
    }
    dataBinding {
        isEnabled = true
    }
}

dependencies {
    // Navigation
    implementation("androidx.navigation:navigation-fragment:2.2.1")
    implementation("androidx.navigation:navigation-ui:2.2.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.2.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.2.1")

    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.1")
}