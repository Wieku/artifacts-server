package org.artifactscracow.artifactsserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ArtifactsServerApplication

fun main(args: Array<String>) {
	runApplication<ArtifactsServerApplication>(*args)
}
