package org.artifactscracow.artifactsserver.entities

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "ArtifactRevision")
@Table(name = "artifact_revisions")
class ArtifactRevision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    var id: Int = 0
        private set

    private var revisionNumber = 0

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artifact_id")
    private var artifact: Artifact? = null

    @CreationTimestamp
    var creationDate: LocalDateTime? = null
        private set

    var street: String? = null
    var building: String? = null

    var type: String? = null
    var description: String? = null

}