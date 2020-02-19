package org.artifactscracow.artifactsserver.repository.artifact

import org.artifactscracow.artifactsserver.entities.Artifact
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

internal interface ArtifactRepository : PagingAndSortingRepository<Artifact, UUID>, ArtifactRepositoryCustom {
    fun findAllByContentNotNullOrderByTimestampDesc(pagination: Pageable): Page<Artifact>
    fun countAllByContentNotNull(): Int
}