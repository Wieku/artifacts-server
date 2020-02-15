package org.artifactscracow.artifactsserver.repository.asset

import org.artifactscracow.artifactsserver.entities.Asset
import org.springframework.data.jpa.repository.JpaRepository

interface AssetRepository: JpaRepository<Asset, String>