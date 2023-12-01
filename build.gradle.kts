plugins {
    kotlin("jvm") version "1.7.21"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.arrow-kt:arrow-core:1.2.0")
    
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}