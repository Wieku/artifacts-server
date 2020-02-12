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
    private val id: UUID? = null

    private val latitude: Double = 0.0

    private val longitude: Double = 0.0

    @CreationTimestamp
    private val timestamp: LocalDateTime? = null

    @OneToMany(mappedBy = "artifact", cascade = [CascadeType.ALL])
    @OrderBy("revision_number ASC")
    private val revisions: MutableList<ArtifactRevision> = ArrayList<ArtifactRevision>()

    //private val removed = false

}