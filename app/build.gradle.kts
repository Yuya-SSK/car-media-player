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
    implementation(project(":log"))
    implementation(project(":data"))
    implementation(project(":device"))

    // Navigation
    implementation("androidx.navigation:navigation-fragment:2.2.1")
    implementation("androidx.navigation:navigation-ui:2.2.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.2.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.2.1")

    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.1")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf(
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xuse-experimental=kotlinx.coroutines.InternalCoroutinesApi",
            "-Xuse-experimental=kotlinx.coroutines.ObsoleteCoroutinesApi",
            "-Xuse-experimental=kotlinx.coroutines.FlowPreview")
    }
}