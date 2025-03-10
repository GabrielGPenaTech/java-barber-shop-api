import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

plugins {
	java
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "br.com.dio"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

var mapStructVersion = "1.6.3"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-database-postgresql")
	implementation("org.mapstruct:mapstruct:${mapStructVersion}")

	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")

	annotationProcessor("org.mapstruct:mapstruct-processor:${mapStructVersion}")
	annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.named("build") {
	doLast {
		val trigger = file("src/main/resources/trigger.txt")
		if (!trigger.exists()) {
			trigger.mkdirs()
		}

		trigger.writeText(Date().time.toString())
	}
}


tasks.register("generateFlywayMigrationFile") {
	description = "Generate flyway migration"
	group = "Flyway"

	val migrationNameFromConsole = project.findProperty("migrationName") as String?
		?: throw IllegalStateException("migrationName must be set")

	val migrationsDir = file("src/main/resources/db/migration")
	val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddmmss"))
	val migrationName = "V${timestamp}__${migrationNameFromConsole}.sql"
	val migrationFile = file("${migrationsDir.path}/${migrationName}.sql")

	doLast {
		if (!migrationsDir.exists()) {
			migrationsDir.mkdirs()
		}
		migrationFile.writeText("-- $migrationName generated in ${migrationsDir.path}")
		logger.lifecycle("Generated migration file: ${migrationFile.path}")
	}
}
