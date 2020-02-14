package org.artifactscracow.artifactsserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.aspectj.EnableSpringConfigured

@SpringBootApplication(exclude = [org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration::class])
@EnableSpringConfigured
class ArtifactsServerApplication

fun main(args: Array<String>) {
	runApplication<ArtifactsServerApplication>(*args)
}
