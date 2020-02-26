plugins {
    `android-library`
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":log"))

    // Room
    kapt("androidx.room:room-compiler:2.2.4")
    api("androidx.room:room-runtime:2.2.4")
    implementation("androidx.room:room-ktx:2.2.4")
}