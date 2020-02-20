package org.artifactscracow.artifactsserver.security

val PBKDF2Secret: String = System.getenv("PBKDF2_SECRET")
val JWTSecret: String = System.getenv("JWT_SECRET")