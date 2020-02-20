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
    lateinit var id: UUID

    var latitude: Double = 0.0

    var longitude: Double = 0.0

    @CreationTimestamp
    lateinit var timestamp: LocalDateTime

    @OneToOne(cascade = [CascadeType.ALL])
    var content = ArtifactRevision()

    @OneToMany(mappedBy = "artifact", cascade = [CascadeType.ALL])
    @OrderBy("timestamp ASC")
    val photos: MutableList<ArtifactPhoto> = ArrayList()

    fun getArchival() = photos.lastOrNull { it.isArchival }

    fun getThreeNewest() = photos.filter { !it.isArchival }.takeLast(3)
}