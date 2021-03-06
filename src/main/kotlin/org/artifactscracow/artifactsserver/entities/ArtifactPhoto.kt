package org.artifactscracow.artifactsserver.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*


@Entity(name = "ArtifactPhoto")
@Table(name = "artifact_photos")
class ArtifactPhoto {

    @Id
    @GeneratedValue
    @JsonIgnore
    private lateinit var id: UUID

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artifact_id")
    @JsonIgnore
    lateinit var artifact: Artifact

    @CreationTimestamp
    lateinit var createdAt: LocalDateTime

    var isArchival: Boolean = false

    lateinit var imageName: String
}