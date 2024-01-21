package com.hannover.icsr_reader.service

import com.hannover.icsr_reader.entity.DataDictionary
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service


@Service
class DataDictionaryService {


    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    fun find(key: String): DataDictionary? {
        val query = Query.query(Criteria.where("key").`is`(key))
        return mongoTemplate.findOne(query,DataDictionary::class.java)
    }


}