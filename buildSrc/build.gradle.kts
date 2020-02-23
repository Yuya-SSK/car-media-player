plugins {
    `kotlin-dsl`
}
repositories {
    jcenter()
    google()
}
dependencies {
    implementation("com.android.tools.build:gradle:4.0.0-alpha09")
    implementation(kotlin("gradle-plugin", version = "1.3.61"))
}