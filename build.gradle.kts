import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.dependencies
//import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {

    repositories {
//        gradlePluginPortal()
        jcenter()
//        mavenLocal()
    }

    val kotlinVersion: String by project
    val junitPlatformVersion: String  by project
//    val usedDetektGradleVersion: String by project

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.junit.platform:junit-platform-gradle-plugin:$junitPlatformVersion")
//        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$usedDetektGradleVersion")
    }

}

//plugins {
//    kotlin("jvm") version "1.2.41"
//}

//val compileKotlin by tasks.getting(KotlinCompile::class) {
//    // Customise the “compileKotlin” task.
//    kotlinOptions.jvmTarget = "1.8"
//    doLast { println("Finished compiling Kotlin source code") }
//}
