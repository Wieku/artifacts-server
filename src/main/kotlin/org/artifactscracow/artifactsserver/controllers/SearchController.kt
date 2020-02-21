package org.artifactscracow.artifactsserver.controllers

import org.artifactscracow.artifactsserver.repository.artifact.ArtifactRepository
import org.artifactscracow.artifactsserver.views.ArtifactView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchController {

    @Autowired
    private lateinit var repository: ArtifactRepository

    @GetMapping(value = ["/api/v1/artifacts/search/by_address"])
    fun searchArtifacts(@RequestParam(value = "street") street: String, @RequestParam(value = "building", required = false) building: String?): ResponseEntity<Any> {
        val artifacts = repository.findAllByContent_StreetContainsAndContent_BuildingContains(street, building ?: "")
        return ResponseEntity.ok(artifacts.map { ArtifactView(it) })
    }

}