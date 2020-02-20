package org.artifactscracow.artifactsserver.entities

import org.hibernate.annotations.Type
import javax.persistence.*

@Entity(name = "Asset")
@Table(name = "assets")
class Asset {

    @Id
    lateinit var name: String

    lateinit var mime: String

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "data")
    lateinit var data: ByteArray
}