package org.artifactscracow.artifactsserver.repository.artifact

import org.artifactscracow.artifactsserver.entities.Artifact
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

internal interface ArtifactRepository : PagingAndSortingRepository<Artifact, UUID>, ArtifactRepositoryCustom {
    fun findAllByContentNotNullOrderByCreatedAtDesc(pagination: Pageable): Page<Artifact>
    fun findAllByContent_StreetContainsAndContent_BuildingContains(street: String, building: String): List<Artifact>
    fun countAllByContentNotNull(): Int
}