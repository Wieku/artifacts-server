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

    @Id
    @GeneratedValue
    private lateinit var id: UUID

    @CreationTimestamp
    var creationDate: LocalDateTime? = null
        private set

    @OneToOne
    lateinit var artifact: Artifact

    @OneToOne
    lateinit var revision: ArtifactRevision

}