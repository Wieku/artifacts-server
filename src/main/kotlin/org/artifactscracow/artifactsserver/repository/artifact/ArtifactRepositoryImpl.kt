package org.artifactscracow.artifactsserver.repository.artifact;

import org.artifactscracow.artifactsserver.entities.*
import org.artifactscracow.artifactsserver.views.ArtifactAdd
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

open class ArtifactRepositoryImpl : ArtifactRepositoryCustom {

    @PersistenceContext
    private lateinit var manager: EntityManager

    override fun getInArea(lat1: Double, lon1: Double, lat2: Double, lon2: Double): List<Artifact> {
        val notes = manager.createQuery("SELECT a FROM ${Artifact::class.java.name} a WHERE a.latitude >= $lat1 AND a.latitude <= $lat2 AND a.longitude >= $lon1 AND a.longitude <= $lon2").resultList
        return notes as List<Artifact>
    }

    @Transactional
    override fun updateArtifact(artifactId: UUID, details: ArtifactAdd, user: User): Artifact {
        val artifact = manager.find(Artifact::class.java, artifactId)

        artifact.content = ArtifactRevision()
        artifact.content.editedBy = user
        artifact.content.latitude = details.latitude
        artifact.content.longitude = details.longitude
        artifact.content.street = details.street
        artifact.content.building = details.building
        artifact.content.name = details.name
        artifact.content.type = details.type
        artifact.content.description = details.description
        manager.flush()
        return artifact
    }

    @Transactional
    override fun addArtifact(details: ArtifactAdd, user: User): Artifact {
        val artifact = Artifact()
        artifact.createdBy = user
        artifact.content.editedBy = user
        artifact.content.latitude = details.latitude
        artifact.content.longitude = details.longitude
        artifact.content.street = details.street
        artifact.content.building = details.building
        artifact.content.name = details.name
        artifact.content.type = details.type
        artifact.content.description = details.description

        manager.persist(artifact)
        manager.flush()
        return artifact
    }

    @Transactional
    override fun removeArtifact(artifactId: UUID): Boolean {
        val artifact = manager.find(Artifact::class.java, artifactId) ?: return false

        manager.remove(artifact.content)
        manager.remove(artifact)
        manager.flush()
        return true
    }

    @Transactional
    override fun addPhoto(artifactId: UUID, photo: ArtifactPhoto): Boolean {
        val artifact = manager.find(Artifact::class.java, artifactId) ?: return false
        artifact.photos += photo
        photo.artifact = artifact
        manager.flush()
        return true
    }
}
