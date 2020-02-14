package org.artifactscracow.artifactsserver.repository.artifact

import org.artifactscracow.artifactsserver.entities.Artifact
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

internal interface ArtifactRepository : JpaRepository<Artifact, UUID>, ArtifactRepositoryCustom