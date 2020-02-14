package org.artifactscracow.artifactsserver.views

import org.artifactscracow.artifactsserver.entities.Artifact
import java.util.*

data class ArtifactView(val id: UUID, val latitude: Double, val longitude: Double, var street: String, var building: String, val type: String, val description: String) {

    constructor(artifact: Artifact) : this(artifact.id, artifact.latitude, artifact.longitude, artifact.content!!.street, artifact.content!!.building, artifact.content!!.type, artifact.content!!.description)

}