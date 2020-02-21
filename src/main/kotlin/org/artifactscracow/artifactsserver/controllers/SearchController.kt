package org.artifactscracow.artifactsserver.controllers

import org.artifactscracow.artifactsserver.repository.artifact.ArtifactRepository
import org.artifactscracow.artifactsserver.views.ArtifactView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchController {

    @Autowired
    private lateinit var repository: ArtifactRepository

    @GetMapping(value = ["/api/v1/artifacts/search"])
    fun searchArtifacts(@RequestParam(value = "name", required = false) name: String?, @RequestParam(value = "type", required = false) type: String?, @RequestParam(value = "street", required = false) street: String?, @RequestParam(value = "building", required = false) building: String?, @RequestParam(value = "page", required = false) page: Int?, @RequestParam(value = "size", required = false) size: Int?): ResponseEntity<Any> {
        if (name.isNullOrBlank() && type.isNullOrBlank() && street.isNullOrBlank() && building.isNullOrBlank())
            return ResponseEntity.badRequest().build()
        val artifacts = repository.findAllByContent_NameContainsAndContent_TypeContainsAndContent_StreetContainsAndContent_BuildingContainsOrderByContent_EditedAtDesc(
                name ?: "", type ?: "", street ?: "", building ?: "", PageRequest.of(page ?: 0, size ?: 10))
        return ResponseEntity.ok(artifacts.map { ArtifactView(it) })
    }

}