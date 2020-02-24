import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BuildType
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project

fun NamedDomainObjectContainer<BuildType>.release(body: BuildType.() -> Unit) {
    getByName("release") { body(this) }
}

fun Project.module(action: LibraryExtension.() -> Unit) {
    afterEvaluate {
        if (hasProperty("android") && project.name != "app") {
            action(extensions.getByName("android") as LibraryExtension)
        }
    }
}