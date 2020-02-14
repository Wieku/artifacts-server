package org.artifactscracow.artifactsserver.controllers

import org.artifactscracow.artifactsserver.repository.user.UserRepository
import org.artifactscracow.artifactsserver.security.JWTHelper
import org.artifactscracow.artifactsserver.security.PBKDF2Secret
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.web.bind.annotation.*;

@RestController
class UserController {

    @Autowired
    private lateinit var repository: UserRepository

    @PostMapping(value = ["/api/v1/user/register"])
    fun registerUser(@RequestParam email: String, @RequestParam password: String, @RequestParam name: String): ResponseEntity<Any> {
        if(repository.findByEmail(email) != null) return ResponseEntity.badRequest().build()

        val user = repository.registerUser(email, password, name) ?: return ResponseEntity.badRequest().build()
        return ResponseEntity.ok(JWTHelper.generateToken(user))
    }

    @PostMapping(value = ["/api/v1/user/login"])
    fun loginUser(@RequestParam email: String, @RequestParam password: String): ResponseEntity<Any> {
        val user = repository.authenticateUser(email, password) ?: return ResponseEntity.badRequest().build()

        return ResponseEntity.ok(JWTHelper.generateToken(user))
    }
}
