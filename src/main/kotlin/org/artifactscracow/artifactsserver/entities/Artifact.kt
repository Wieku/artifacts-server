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

    @CreationTimestamp
    lateinit var createdAt: LocalDateTime

    @OneToOne(cascade = [CascadeType.ALL])
    var content = ArtifactRevision()

    @OneToMany(mappedBy = "createdAt", cascade = [CascadeType.ALL])
    @OrderBy("createdAt ASC")
    val photos: MutableList<ArtifactPhoto> = ArrayList()

    fun getArchival() = photos.lastOrNull { it.isArchival }

    fun getThreeNewest() = photos.filter { !it.isArchival }.takeLast(3)
}