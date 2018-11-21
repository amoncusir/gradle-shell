import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Date

object Constants
{
    const val VERSION = "1.0.0"
    const val RELEASE_TYPE = "Final"
}

plugins {
    kotlin("jvm") version "1.3.10"

    base

    // Quality check
    id("io.gitlab.arturbosch.detekt") version "1.0.0-RC11"

    // Bintray Upload
    id("com.jfrog.bintray") version "1.8.4"
}

group = "info.digitalpoet.gradle"
version = Constants.VERSION

bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")

    publish = true

    pkg = PackageConfig().apply {
        repo = "gradle-buildsrc"
        name = "shell"

        publicDownloadNumbers = true

        setLicenses("AGPL-V3")

        setLabels("gradle", "shell", "bash", "linux")

        vcsUrl = "https://github.com/amoncusir/gradle-shell.git"
        githubRepo = "amoncusir/gradle-shell"
        githubReleaseNotesFile = "README.md"

        version = VersionConfig().apply {
            name = "${Constants.VERSION}-${Constants.RELEASE_TYPE}"
            vcsTag = Constants.VERSION
            desc = "A gradle tasks for run commands in linux environments"
        }
    }
}

repositories {
    jcenter()
    mavenCentral()

    maven("https://dl.bintray.com/amoncusir/gradle-buildsrc")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(gradleApi())
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
