package com.hannover.icsr_reader.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "data_dictionary")
data class DataDictionary(
        @Id
        var id: String? = null,
        var key:String? = null,
        var name:String? = null,
        var version: String? = null,
        var children:List<DataDictionaryItem> = listOf()
)

data class DataDictionaryItem(
        var id:String? = null,
        var label:String? = null,
        var value:String? = null
)

