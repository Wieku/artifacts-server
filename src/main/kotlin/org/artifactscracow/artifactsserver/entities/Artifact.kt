package org.artifactscracow.artifactsserver.entities

import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity(name = "Artifact")
@Table(name = "artifacts")
@EntityListeners(AuditingEntityListener::class)
class Artifact {

    @Id
    @GeneratedValue
    private lateinit var id: UUID

    val latitude: Double = 0.0
    val longitude: Double = 0.0

    @CreationTimestamp
    val timestamp: LocalDateTime? = null

    @OneToOne
    val content: ArtifactRevision? = null

    val lastEdit: LocalDateTime? = null

}