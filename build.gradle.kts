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
}

task<Delete>("clean") { delete(rootProject.buildDir) }