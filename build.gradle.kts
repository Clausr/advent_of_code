plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.serialization") version "2.1.0"
}

repositories {
    mavenCentral()
}

tasks {
    wrapper {
        gradleVersion = "8.14.3"
    }
}

sourceSets {
    val main by getting {
        java {
            srcDirs("src/main")
            srcDirs("src/main/resources")
        }
    }
    val test by getting {
        java {
            srcDir("src/test/aoc2024")
            srcDir("src/test/aoc2023")
            srcDir("src/test/aoc2022")
            srcDir("src/test/aoc2018")
            srcDirs("src/test/resources")
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    testImplementation(platform("org.junit:junit-bom:5.11.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("com.github.kittinunf.fuel:fuel:2.3.1") // For downloading input file
    implementation("com.github.kittinunf.result:result:5.6.0") // Needed for Fuel
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

// config JVM target to 1.8 for kotlin compilation tasks
//tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
//    kotlinOptions.jvmTarget = JavaVersion.VERSION_19.majorVersion
//}

// https://discuss.gradle.org/t/gradle-7-fail-for-duplicates-in-copy-specs-has-no-duplicates-in-project/39834/9
project.tasks.named("processTestResources", Copy::class.java) {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
project.tasks.named("processResources", Copy::class.java) {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
