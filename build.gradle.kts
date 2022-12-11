plugins {
    kotlin("jvm") version "1.7.22"
}

repositories {
    mavenCentral()
}

tasks {
    wrapper {
        gradleVersion = "7.6"
    }
}

sourceSets {
    main {
        java {
            srcDirs("src/main")
            srcDirs("src/main/resources")
        }
    }
    test {
        java {
            srcDir("src/test/aoc2022")
            srcDirs("src/test/resources")
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.21")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("com.github.kittinunf.fuel:fuel:2.3.1") // For downloading input file
    implementation("com.github.kittinunf.result:result:5.2.1") // Needed for Fuel
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

// config JVM target to 1.8 for kotlin compilation tasks
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}

// https://discuss.gradle.org/t/gradle-7-fail-for-duplicates-in-copy-specs-has-no-duplicates-in-project/39834/9
project.tasks.named("processTestResources", Copy::class.java) {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
project.tasks.named("processResources", Copy::class.java) {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}