package org.artifactscracow.artifactsserver.entities

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity(name = "ArtifactRevision")
@Table(name = "artifact_revisions")
class ArtifactRevision {

    @Id
    @GeneratedValue
    private lateinit var id: UUID

    @CreationTimestamp
    var editedAt: LocalDateTime? = null
        private set

    var latitude: Double = 0.0

    var longitude: Double = 0.0

    var street: String = ""

    var building: String = ""

    var name: String = ""

    var type: String = ""

    var description: String = ""
}