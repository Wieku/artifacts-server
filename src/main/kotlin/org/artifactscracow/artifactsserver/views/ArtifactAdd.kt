package org.artifactscracow.artifactsserver.views

data class ArtifactAdd(val latitude: Double, val longitude: Double, val street: String, val building: String, val name: String, val type: String, val description: String) {

    fun validate(): Boolean {
        return street.isNotEmpty() &&
                building.isNotEmpty() &&
                name.isNotEmpty() &&
                type.isNotEmpty() &&
                description.isNotEmpty() &&
                street.length <= 255 &&
                building.length <= 255 &&
                name.length <= 255 &&
                type.length <= 255 &&
                description.length <= 10000
    }

}