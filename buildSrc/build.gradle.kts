plugins {
    `kotlin-dsl`
}
repositories {
    google()
    jcenter()
}
dependencies {
    implementation("com.android.tools.build:gradle:3.6.0")
    implementation(kotlin("gradle-plugin", version = "1.3.61"))
}