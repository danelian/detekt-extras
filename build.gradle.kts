import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.repositories

//import com.jfrog.bintray.gradle.BintrayExtension
//import io.gitlab.arturbosch.detekt.extensions.DetektExtension
//import io.gitlab.arturbosch.detekt.extensions.ProfileExtension
//import org.gradle.kotlin.dsl.apply
//import org.gradle.kotlin.dsl.repositories
//import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
//import org.junit.platform.console.options.Details
//import org.junit.platform.gradle.plugin.JUnitPlatformExtension
//import java.util.*


//buildscript {
//
//    repositories {
//        gradlePluginPortal()
//        jcenter()
//        mavenLocal()
//    }
//
//    val kotlinVersion: String by project
//    val junitPlatformVersion: String  by project
//    val usedDetektGradleVersion: String by project
//
//    dependencies {
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
//        classpath("org.junit.platform:junit-platform-gradle-plugin:$junitPlatformVersion")
//        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$usedDetektGradleVersion")
//    }
//}

//apply {
//    plugin("kotlin")
//}

plugins {
    id("java-library")
    id("com.jfrog.bintray").version("1.8.0")
    id("com.github.ben-manes.versions").version("0.17.0")
    id("com.github.johnrengelman.shadow").version("2.0.4").apply(false)
    id("com.gradle.plugin-publish").version("0.9.10").apply(false)
}

//subprojects {
//
//    apply {
//        plugin("org.junit.platform.gradle.plugin")
//        plugin("java-library")
//        plugin("kotlin")
//        plugin("com.jfrog.bintray")
//        plugin("maven-publish")
//    }
//
//    tasks.withType<KotlinCompile> {
//        kotlinOptions.jvmTarget = "1.8"
//        kotlinOptions.freeCompilerArgs = listOf("-Xskip-runtime-version-check")
//        kotlinOptions.allWarningsAsErrors = shouldTreatCompilerWarningsAsErrors()
//    }
//
//    val sourcesJar by tasks.creating(Jar::class) {
//        dependsOn("classes")
//        classifier = "sources"
//        from(the<JavaPluginConvention>().sourceSets["main"].allSource)
//    }
//
//    val kotlinVersion by project
//    val junitEngineVersion by project
//    val junitPlatformVersion by project
//    val assertjVersion by project
//    val spekVersion by project
//    val kotlinImplementation by configurations.creating
//    val kotlinTest by configurations.creating
//    val junitPlatform = configurations["junitPlatform"]
//
//    dependencies {
//        kotlinImplementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:$kotlinVersion")
//        kotlinImplementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
//        kotlinTest("org.junit.jupiter:junit-jupiter-api:$junitEngineVersion")
//        kotlinTest("org.jetbrains.kotl`in:kotlin-test:$kotlinVersion")
//        kotlinTest("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
//        kotlinTest("org.assertj:assertj-core:$assertjVersion")
//        kotlinTest("org.jetbrains.spek:spek-api:$spekVersion")
//        kotlinTest("org.jetbrains.spek:spek-subject-extension:$spekVersion")
//        kotlinTest("org.junit.jupiter:junit-jupiter-engine:$junitEngineVersion")
//        kotlinTest("org.reflections:reflections:0.9.11")
//        junitPlatform("org.junit.platform:junit-platform-launcher:$junitPlatformVersion")
//        junitPlatform("org.junit.platform:junit-platform-console:$junitPlatformVersion")
//        junitPlatform("org.jetbrains.spek:spek-junit-platform-engine:$spekVersion")
//    }
//
//    the<JavaPluginConvention>().sourceSets {
//        "main" {
//            java {
//                srcDirs("src/main/kotlin")
//            }
//        }
//    }
//
//}
