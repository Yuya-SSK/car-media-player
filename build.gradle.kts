buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.6.1")
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

task<Delete>("clean") { delete(rootProject.buildDir) }