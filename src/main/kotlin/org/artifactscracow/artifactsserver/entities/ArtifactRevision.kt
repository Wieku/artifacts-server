package org.artifactscracow.artifactsserver.entities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.Type
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
    lateinit var editedAt: LocalDateTime

    @OneToOne
    lateinit var editedBy: User

    var latitude: Double = 0.0

    var longitude: Double = 0.0

    var street: String = ""

    var building: String = ""

    var name: String = ""

    var type: String = ""

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    var description: String = ""
}