package org.artifactscracow.artifactsserver.repository.artifact

import org.artifactscracow.artifactsserver.entities.Artifact
import org.artifactscracow.artifactsserver.entities.ArtifactPhoto
import org.artifactscracow.artifactsserver.entities.ArtifactRevision
import org.artifactscracow.artifactsserver.entities.User
import org.artifactscracow.artifactsserver.views.ArtifactAdd
import org.artifactscracow.artifactsserver.views.ArtifactView
import java.util.*

interface ArtifactRepositoryCustom {
    fun addArtifact(details: ArtifactAdd, user: User): Artifact
    fun updateArtifact(artifactId: UUID, details: ArtifactAdd, user: User): Artifact
    fun removeArtifact(artifactId: UUID): Boolean
    fun addPhoto(artifactId: UUID, photo: ArtifactPhoto): Boolean
}