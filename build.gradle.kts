// Top-level build file where you can add configuration options common to all sub-projects/modules.
import io.gitlab.arturbosch.detekt.Detekt

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.detetk)
    alias(libs.plugins.kapt) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

subprojects {
    apply(from = file("${rootProject.rootDir}/config/githooks.gradle.kts"))
    apply(plugin = "io.gitlab.arturbosch.detekt")

    detekt {
        source.setFrom(files(projectDir))
        config.setFrom(files("${rootProject.rootDir}/detekt/detekt-config.yml"))
        baseline = file("${rootProject.rootDir}/detekt/baseline/detekt-baseline.xml")
        buildUponDefaultConfig = true
        disableDefaultRuleSets = false
        debug = false
        ignoreFailures = false
    }

    tasks.withType<Detekt>().configureEach {
        include("**/*.kt", "**/*.kts")
        exclude("build/", "**/build/**")
        jvmTarget = JavaVersion.VERSION_17.toString()
        reports {
            xml.required.set(false)
            html.required.set(false)
            txt.required.set(false)
        }
    }
}

true // Needed to make the Suppress annotation work for the plugins block
