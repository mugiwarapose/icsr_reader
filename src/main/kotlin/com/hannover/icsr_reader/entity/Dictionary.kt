package com.hannover.icsr_reader.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * 医学编码字典 - MedDRA/WhoDDE/...
 */
@Document(collection = "dictionary")
data class Dictionary(
        @Id
        var id: ObjectId? = null,
        var code: String? = null,
        var nameCn: String? = null,
        var nameEn: String? = null,
        var nameKo: String? = null,
        var level: Int? = null,
        var levelCode: String? = null,
        var parent: List<String>? = null,
        var dict: String? = null,
        var version: String? = null
)