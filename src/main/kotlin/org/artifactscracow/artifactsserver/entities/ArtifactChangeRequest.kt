package org.artifactscracow.artifactsserver.entities

import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity(name = "ArtifactChangeRequest")
@Table(name = "change_requests")
@EntityListeners(AuditingEntityListener::class)
class ArtifactChangeRequest() {

    constructor(artifact: Artifact, revision: ArtifactRevision): this() {
        this.artifact = artifact
        this.revision = revision
    }

    @Id
    @GeneratedValue
    private lateinit var id: UUID

    @CreationTimestamp
    var creationDate: LocalDateTime? = null
        private set

    @OneToOne(cascade = [CascadeType.ALL])
    lateinit var artifact: Artifact

    @OneToOne(cascade = [CascadeType.ALL])
    lateinit var revision: ArtifactRevision

}