plugins {
    `android-library`
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":log"))

    // Room
    kapt("androidx.room:room-compiler:2.2.5")
    api("androidx.room:room-runtime:2.2.5")
    implementation("androidx.room:room-ktx:2.2.5")
}