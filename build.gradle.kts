plugins {
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    id("com.google.cloud.tools.jib") version "3.4.3"
    kotlin("plugin.jpa") version "1.9.24"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

allprojects {
    group = "org.colosseum"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    kotlin {
        jvmToolchain(21)
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

jib {
    val env = System.getenv("DEPLOY_ENVIRONMENT")
    from {
        image = "amazoncorretto:21"
    }
    to {
        image =
            "${System.getenv("AWS_ECR_REGISTRY")}/${System.getenv("AWS_ECR_REPOSITORY_NAME")}:${System.getenv("IMAGE_TAG")}"
    }
    container {
        jvmFlags = when (env) {
            "alpha" -> mutableListOf(
                "-Xms512m",
                "-Xmx1024m",
                "-Dspring.profiles.active=$env",
                "-Duser.timezone=UTC",
                "-XX:+UseContainerSupport",
            )

            "staging" -> mutableListOf(
                "-Xms1024m",
                "-Xmx2048m",
                "-Dspring.profiles.active=$env",
                "-Duser.timezone=UTC",
                "-XX:+UseContainerSupport",
            )

            else -> emptyList()
        }
        creationTime = "USE_CURRENT_TIMESTAMP"
        ports = mutableListOf("8080")
        mainClass = "org.example.Main"
    }
}