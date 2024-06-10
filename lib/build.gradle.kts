
val version = "0.0.1";

plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
	application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit test framework.
    testImplementation("junit:junit:4.13.2")

    // This dependency is exported to consumers, that is to say found on their compile classpath.
    api("org.apache.commons:commons-math3:3.6.1")

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    implementation("com.google.guava:guava:32.1.1-jre")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

// A lib setup with a Application plugin... crazy! i know.
// Due to the nature of the interpreter, it will has a default interpreter.
// But will be mainly used to power other applications with a nice and simple
// interpreter for a hellish and crazy-ass "language".
application {
    mainClass = "com.finalshare.microbf.Main"
}

tasks.withType(Jar::class) {
    manifest {
        attributes["Main-Class"] = "com.finalshare.microbf.Main"
		attributes["Implementation-Version"] = version;
		attributes["Name"] = "com/finalshare/microbf/Main/"
		}
}
