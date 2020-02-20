package org.artifactscracow.artifactsserver.views

import org.artifactscracow.artifactsserver.entities.Artifact
import org.artifactscracow.artifactsserver.entities.ArtifactPhoto
import java.time.LocalDateTime
import java.util.*

data class ArtifactView(
        val id: UUID,
        val createdAt: LocalDateTime,
        val createdBy: UserIdAndName,
        val editedAt: LocalDateTime?,
        val editedBy: UserIdAndName?,
        val latitude: Double,
        val longitude: Double,
        var street: String,
        var building: String,
        val name: String,
        val type: String,
        val description: String,
        val archivalPhoto: ArtifactPhoto?,
        val recentPhotos: List<ArtifactPhoto>
) {

    constructor(artifact: Artifact) : this(
            artifact.id,
            artifact.createdAt,
            UserIdAndName(artifact.createdBy),
            if (artifact.createdAt != artifact.content.editedAt) artifact.content.editedAt else null,
            if (artifact.createdAt != artifact.content.editedAt) UserIdAndName(artifact.content.editedBy) else null,
            artifact.content.latitude,
            artifact.content.longitude,
            artifact.content.street,
            artifact.content.building,
            artifact.content.name,
            artifact.content.type,
            artifact.content.description,
            artifact.getArchival(),
            artifact.getThreeNewest()
    )

}