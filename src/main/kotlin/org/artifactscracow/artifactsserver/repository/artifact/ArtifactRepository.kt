package org.artifactscracow.artifactsserver.repository.artifact

import org.artifactscracow.artifactsserver.entities.Artifact
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

internal interface ArtifactRepository : PagingAndSortingRepository<Artifact, UUID>, ArtifactRepositoryCustom {
    fun findAllByContent_LatitudeBetweenAndContent_LongitudeBetween(lat1: Double, lon1: Double, lat2: Double, lon2: Double): List<Artifact>
    fun findAllByOrderByCreatedAtDesc(pagination: Pageable): Page<Artifact>
    fun findAllByContent_NameContainsAndContent_TypeContainsAndContent_StreetContainsAndContent_BuildingContainsOrderByContent_EditedAtDesc(name: String, type: String, street: String, building: String, pagination: Pageable): Page<Artifact>
}