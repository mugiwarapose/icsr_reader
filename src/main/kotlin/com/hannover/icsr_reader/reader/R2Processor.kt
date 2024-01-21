package com.hannover.icsr_reader.reader

import com.hannover.icsr_reader.entity.GeneralR2
import com.hannover.icsr_reader.entity.R2Fields
import org.springframework.stereotype.Component

@Component
class R2Processor {

    //edqm
    //meddra
    //causality

    fun processor(data:List<R2Fields>){
            data.forEach {
                val general =processGeneral(it.general)
            }
    }

    fun processGeneral(general:GeneralR2?){

    }
}