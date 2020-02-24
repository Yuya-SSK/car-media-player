plugins {
    id("androidx.navigation.safeargs")
}

android {
    compileSdkVersion(Versions.COMPILE_SDK)
    defaultConfig {
        applicationId = "com.ysp.camep"
        minSdkVersion(Versions.MIN_SDK)
        targetSdkVersion(Versions.TARGET_SDK)
        versionCode = Versions.CODE
        versionName = Versions.NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
         // Project.ktに拡張関数を記述している.
//        getByName("release") {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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

    // Core
    implementation("androidx.core:core-ktx:1.2.0")

    // UI
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.fragment:fragment-ktx:1.2.2")
    implementation("com.google.android.material:material:1.1.0")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-livedata:2.2.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-livedata-core-ktx:2.2.0")

    // Navigation
    implementation("androidx.navigation:navigation-fragment:2.2.1")
    implementation("androidx.navigation:navigation-ui:2.2.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.2.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.2.1")

    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.1")

    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")

    testImplementation("junit:junit:4.12")
}