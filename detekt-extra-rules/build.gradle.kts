import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.*
import org.gradle.kotlin.dsl.provider.KotlinScriptBasePlugin
import org.gradle.kotlin.dsl.provider.KotlinScriptPlugin

//buildscript {



//    repositories {
//        gradlePluginPortal()
//        mavenLocal()
//        jcenter()
//    }
//
//    val kotlinVersion: String by project
//
//    dependencies {
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
//        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.0")
//    }
//
//}

plugins {
    kotlin("jvm")
}

//val kotlinVersion: String by project
//val assertjVersion: String by project
val usedDetektVersion: String  by project
//val junitEngineVersion: String by project
//val junitPlatformVersion: String by project

val implementation by configurations
//val testImplementation by configurations
//val androidTestImplementation by configurations
//
//val testRuntimeOnly by configurations

repositories {
    gradlePluginPortal()
    mavenLocal()
    jcenter()
}

dependencies {
    implementation("io.gitlab.arturbosch.detekt:detekt-api:$usedDetektVersion")

//    testImplementation("io.gitlab.arturbosch.detekt:detekt-test:$usedDetektVersion")
//    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitEngineVersion")
//    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
//    testImplementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
//    testImplementation("org.assertj:assertj-core:$assertjVersion")
//    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitEngineVersion")
//
//    testRuntimeOnly("org.junit.platform:junit-platform-launcher:$junitPlatformVersion")
//    testRuntimeOnly("org.junit.platform:junit-platform-console:$junitPlatformVersion")
}
