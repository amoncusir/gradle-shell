import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.10"

    //
    base

    // Quality check
    id("io.gitlab.arturbosch.detekt") version "1.0.0-RC11"
}

group = "info.digitalpoet.gradle"
version = "1.0.0"

//pluginBundle {
//    website = "https://github.com/amoncusir/gradle-shell"
//    vcsUrl = "https://github.com/amoncusir/gradle-shell"
//
//    description = "A gradle tasks for run commands in linux environments"
//
//}


repositories {
    jcenter()
    mavenCentral()

    maven("https://dl.bintray.com/amoncusir/<REPOSITORY_NAME")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(gradleApi())
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
