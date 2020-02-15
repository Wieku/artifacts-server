package org.artifactscracow.artifactsserver.controllers

import org.artifactscracow.artifactsserver.repository.asset.AssetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class AssetController {

    @Autowired
    private lateinit var repository: AssetRepository

    @GetMapping(value = ["/assets/{name}"])
    fun getResource(@PathVariable name: String): HttpEntity<ByteArray> {
        val asset = repository.findByIdOrNull(name) ?: return ResponseEntity.notFound().build()

        val header = HttpHeaders()
        header.contentType = MediaType.parseMediaType(asset.mime)
        header[HttpHeaders.CONTENT_DISPOSITION] = "filename=" + asset.name
        header.contentLength = asset.data.size.toLong()

        return HttpEntity(asset.data, header)
    }

}