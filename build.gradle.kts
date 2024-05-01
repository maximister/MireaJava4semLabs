plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly("org.projectlombok:lombok:0.11.0")
    // https://mvnrepository.com/artifact/org.springframework/spring-context
    implementation("org.springframework:spring-context:6.1.5")
    // https://mvnrepository.com/artifact/org.springframework/spring-core
    implementation("org.springframework:spring-core:6.1.5")
    // https://mvnrepository.com/artifact/org.springframework/spring-beans
    implementation("org.springframework:spring-beans:6.1.5")

}



tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes(
                mapOf(
                        "Main-Class" to "ru.mirea.maximister.lab9.Hello"
                )
        )
    }
    from("ru.mirea.maximister.lab9/*")
    finalizedBy("moveJarToDirWithDockerFile");
}

tasks.register<Copy>("moveJarToDirWithDockerFile") {
    from("build/libs/MireaJava4SemesterLabs-1.0-SNAPSHOT.jar")
    into("${rootDir}/src/main/java/ru/mirea/maximister/lab9/jar")
    finalizedBy("buildDockerImage");
}

tasks.register<Exec>("buildDockerImage") {
    dependsOn("moveJarToDirWithDockerFile")
    commandLine("docker", "build", "-t", "mirea", "./src/main/java/ru/mirea/maximister/lab9/jar")
}
