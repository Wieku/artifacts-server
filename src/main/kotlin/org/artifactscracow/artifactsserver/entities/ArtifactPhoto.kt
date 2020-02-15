package org.artifactscracow.artifactsserver.entities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.Type
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*


@Entity(name = "ArtifactPhoto")
@Table(name = "artifact_photos")
class ArtifactPhoto {

    @Id
    @GeneratedValue
    lateinit var id: UUID

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artifact_id")
    lateinit var artifact: Artifact

    @CreationTimestamp
    lateinit var timestamp: LocalDateTime

    var isArchival: Boolean = false

    lateinit var imageName: String
}