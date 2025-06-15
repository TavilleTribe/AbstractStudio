import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "com.tavillecode.abstractstudio"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(files("D:/TavilleTribe/IdeaProject/AbstractStudio/pack/RXTXcomm.jar"))
}

tasks.withType<ShadowJar> {
    manifest.attributes.apply {
        put("Implementation-Version", archiveVersion)
        put("Main-Class", "com.tavillecode.abstractstudio.AbstractStudio")
    }
}
