plugins {
    kotlin("multiplatform") version "1.6.10"
    `maven-publish`
}

group = "com.happyandjust"
version = "1.0.0"

repositories {
    mavenCentral()
}

kotlin {
    jvm()
    js()
}

publishing {
    repositories {
        maven {
            credentials {
                username = System.getProperty("username")
                password = System.getProperty("password")
            }

            url = uri("https://repsy.io/mvn/happyandjust/repo")
        }
    }
}