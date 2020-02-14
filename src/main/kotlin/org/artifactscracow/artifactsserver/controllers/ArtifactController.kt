package org.artifactscracow.artifactsserver.controllers

import org.artifactscracow.artifactsserver.repository.artifact.ArtifactRepository
import org.artifactscracow.artifactsserver.security.SecurityManager
import org.artifactscracow.artifactsserver.views.ArtifactAdd
import org.artifactscracow.artifactsserver.views.ArtifactPoint
import org.artifactscracow.artifactsserver.views.ArtifactView
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ArtifactController {

    @Autowired
    private lateinit var repository: ArtifactRepository
    @Autowired
    private lateinit var security: SecurityManager

    @RequestMapping(value = ["/api/v1/artifacts/in_area"], method = [RequestMethod.GET])
    fun getArtifacts(@RequestParam(value = "lat1") lat1: Double, @RequestParam(value = "lon1") lon1: Double, @RequestParam(value = "lat2") lat2: Double, @RequestParam(value = "lon2") lon2: Double): ResponseEntity<Any> {
        val artifacts = repository.getInArea(lat1, lon1, lat2, lon2).stream().map { artifact -> ArtifactPoint(artifact.id, artifact.latitude, artifact.longitude) }.collect(Collectors.toList())
        return ResponseEntity.ok(artifacts)
    }

    @RequestMapping(value = ["/api/v1/artifacts/by_id/{artifactId}"], method = [RequestMethod.GET])
    fun getArtifact(@PathVariable artifactId: UUID): ResponseEntity<Any> {
        val artifact = repository.getArtifact(artifactId)
        return if (artifact == null) ResponseEntity.notFound().build() else ResponseEntity.ok(ArtifactView(artifact))
    }

    @RequestMapping(value = ["/api/v1/artifacts"], method = [RequestMethod.POST])
    fun addArtifact(@RequestBody(required = true) artifactBody: ArtifactAdd, @RequestHeader(value = "Authorization", required = false) token: String?): ResponseEntity<Any> {
        repository.addArtifact(artifactBody, token != null && security.isAuthenticated(token))
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping(value = ["/api/v1/artifacts/{artifactId}"])
    fun removeArtifact(@PathVariable artifactId: UUID, @RequestHeader(value = "Authorization", required = false) token: String?): ResponseEntity<Any> {
        if (token == null || !security.isAuthenticated(token)) return ResponseEntity(HttpStatus.UNAUTHORIZED)
        return if (repository.removeArtifact(artifactId)) ResponseEntity.noContent().build() else ResponseEntity.notFound().build()
    }


}
