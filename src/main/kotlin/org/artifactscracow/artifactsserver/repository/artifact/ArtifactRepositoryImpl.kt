package org.artifactscracow.artifactsserver.repository.artifact;

import org.artifactscracow.artifactsserver.entities.Artifact
import org.artifactscracow.artifactsserver.entities.ArtifactChangeRequest
import org.artifactscracow.artifactsserver.entities.ArtifactPhoto
import org.artifactscracow.artifactsserver.entities.ArtifactRevision
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

    override fun getArtifact(artifactId: UUID): Artifact? = manager.find(Artifact::class.java, artifactId)

    @Transactional
    override fun updateArtifact(artifactId: UUID, revision: ArtifactRevision): Boolean {
        val artifact = manager.find(Artifact::class.java, artifactId) ?: return false

        val changeRequest = ArtifactChangeRequest(artifact, revision)
        manager.persist(changeRequest)
        manager.flush()
        return true
    }

    @Transactional
    override fun addArtifact(details: ArtifactAdd): Artifact {
        val artifact = Artifact()
        artifact.latitude = details.latitude
        artifact.longitude = details.longitude
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
