plugins {
    kotlin("jvm") version "1.9.24"
}

group = "org.colosseum"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
