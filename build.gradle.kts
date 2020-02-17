plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.61"

    application
}

repositories {
    jcenter()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    implementation(kotlin("stdlib-jdk8"))

    // Use the Kotlin JUnit integration.
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.0")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.21")
}

tasks.test {
    useJUnitPlatform()
}

application {
    // Define the main class for the application.
    mainClassName = "codes.pedromanoel.AppKt"
}
