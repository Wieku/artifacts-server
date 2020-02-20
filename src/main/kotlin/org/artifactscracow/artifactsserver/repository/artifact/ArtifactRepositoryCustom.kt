package org.artifactscracow.artifactsserver.repository.artifact

import org.artifactscracow.artifactsserver.entities.Artifact
import org.artifactscracow.artifactsserver.entities.ArtifactPhoto
import org.artifactscracow.artifactsserver.entities.ArtifactRevision
import org.artifactscracow.artifactsserver.views.ArtifactAdd
import org.artifactscracow.artifactsserver.views.ArtifactView
import java.util.*

interface ArtifactRepositoryCustom {
    fun getInArea(lat1: Double, lon1: Double, lat2: Double, lon2: Double): List<Artifact>
    fun getArtifact(artifactId: UUID): Artifact?
    fun addArtifact(details: ArtifactAdd): Artifact
    fun updateArtifact(artifactId: UUID, revision: ArtifactRevision): Boolean
    fun removeArtifact(artifactId: UUID): Boolean
    fun addPhoto(artifactId: UUID, photo: ArtifactPhoto): Boolean
}