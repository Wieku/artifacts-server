package org.artifactscracow.artifactsserver.views

import org.artifactscracow.artifactsserver.entities.User
import java.util.*

data class UserIdAndName(val userId: UUID, val name: String) {
    constructor(user: User) : this(user.id, user.name)
}