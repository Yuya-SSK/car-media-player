plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    androidCommon()
    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // Kotlin
    implementation(kotlin("stdlib-jdk7"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.3")

    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test:rules:1.2.0")
    androidTestImplementation("org.assertj:assertj-core:3.2.0")
    androidTestImplementation("io.mockk:mockk-android:1.9.3")

    testImplementation("junit:junit:4.12")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf(
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xuse-experimental=kotlinx.coroutines.ObsoleteCoroutinesApi",
            "-Xuse-experimental=kotlinx.coroutines.FlowPreview")
    }
}