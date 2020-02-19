package org.artifactscracow.artifactsserver.views

data class ArtifactAdd(val latitude: Double, val longitude: Double, val street: String, val building: String, val name: String, val type: String, val description: String) {

    fun validate(): Boolean {
        return street.isNotEmpty() && building.isNotEmpty() && name.isNotEmpty() && type.isNotEmpty() && description.isNotEmpty()
    }

}