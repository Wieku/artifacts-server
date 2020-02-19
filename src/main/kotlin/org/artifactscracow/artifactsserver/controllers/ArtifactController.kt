package org.artifactscracow.artifactsserver.controllers

import org.artifactscracow.artifactsserver.entities.ArtifactPhoto
import org.artifactscracow.artifactsserver.entities.Asset
import org.artifactscracow.artifactsserver.repository.artifact.ArtifactRepository
import org.artifactscracow.artifactsserver.repository.asset.AssetRepository
import org.artifactscracow.artifactsserver.security.SecurityManager
import org.artifactscracow.artifactsserver.views.ArtifactAdd
import org.artifactscracow.artifactsserver.views.ArtifactPoint
import org.artifactscracow.artifactsserver.views.ArtifactView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.UUID
import java.util.stream.Collectors

@RestController
class ArtifactController {

    @Autowired
    private lateinit var repository: ArtifactRepository

    @Autowired
    private lateinit var assetsRepository: AssetRepository

    @Autowired
    private lateinit var security: SecurityManager

    @GetMapping(value = ["/api/v1/artifacts/count"])
    fun getCount() = ResponseEntity.ok(repository.countAllByContentNotNull())

    @GetMapping(value = ["/api/v1/artifacts"])
    fun getArtifacts(@RequestParam(value = "page", required = false) page: Int?, @RequestParam(value = "size", required = false) size: Int?): ResponseEntity<Any> {
        val artifacts = repository.findAllByContentNotNullOrderByTimestampDesc(PageRequest.of(page ?: 0, size ?: 10))
        return ResponseEntity.ok(artifacts.content.map { ArtifactView(it) })
    }

    @GetMapping(value = ["/api/v1/artifacts/in_area"])
    fun getArtifacts(@RequestParam(value = "lat1") lat1: Double, @RequestParam(value = "lon1") lon1: Double, @RequestParam(value = "lat2") lat2: Double, @RequestParam(value = "lon2") lon2: Double): ResponseEntity<Any> {
        val artifacts = repository.getInArea(lat1, lon1, lat2, lon2).stream().map { artifact -> ArtifactPoint(artifact.id, artifact.latitude, artifact.longitude, artifact.content!!.type) }.collect(Collectors.toList())
        return ResponseEntity.ok(artifacts)
    }

    @GetMapping(value = ["/api/v1/artifacts/by_id/{artifactId}"])
    fun getArtifact(@PathVariable artifactId: UUID): ResponseEntity<Any> {
        val artifact = repository.getArtifact(artifactId)
        return if (artifact == null) ResponseEntity.notFound().build() else ResponseEntity.ok(ArtifactView(artifact))
    }

    @PostMapping(value = ["/api/v1/artifacts"])
    fun addArtifact(@RequestBody(required = true) artifactBody: ArtifactAdd, @RequestHeader(value = "Authorization", required = false) token: String?): ResponseEntity<Any> {
        repository.addArtifact(artifactBody, token != null && security.isAuthenticated(token))
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping(value = ["/api/v1/artifacts/by_id/{artifactId}"])
    fun removeArtifact(@PathVariable artifactId: UUID, @RequestHeader(value = "Authorization") token: String): ResponseEntity<Any> {
        if (!security.isAuthenticated(token)) return ResponseEntity(HttpStatus.UNAUTHORIZED)
        return if (repository.removeArtifact(artifactId)) ResponseEntity.noContent().build() else ResponseEntity.notFound().build()
    }

    @PostMapping(value = ["/api/v1/artifacts/by_id/{artifactId}/photo"])
    fun addArtifactPhoto(@PathVariable artifactId: UUID, @RequestParam("file") file: MultipartFile, @RequestParam(value = "archival", required = false, defaultValue = "false") archival: Boolean, @RequestHeader(value = "Authorization", required = false) token: String?): ResponseEntity<Any> {
        if (file.contentType != MediaType.IMAGE_PNG_VALUE && file.contentType != MediaType.IMAGE_JPEG_VALUE) return ResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
        if (file.size > 5242880) return ResponseEntity(HttpStatus.PAYLOAD_TOO_LARGE)

        if (archival && (token == null || !security.isAuthenticated(token))) return ResponseEntity(HttpStatus.FORBIDDEN)

        if (!repository.existsById(artifactId)) return ResponseEntity.notFound().build()

        val name = UUID.randomUUID().toString().replace("-", "") + "." + file.originalFilename!!.substringAfterLast(".")

        val asset = Asset()
        asset.name = name
        asset.mime = file.contentType!!
        asset.data = file.bytes
        assetsRepository.saveAndFlush(asset)

        val photo = ArtifactPhoto()
        photo.imageName = name
        photo.isArchival = archival

        repository.addPhoto(artifactId, photo)

        return ResponseEntity.noContent().build()
    }

}
