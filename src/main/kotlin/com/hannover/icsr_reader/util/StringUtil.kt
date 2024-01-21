package com.hannover.icsr_reader.util

class StringUtil {
    companion object{
        fun showText(value:String?,prefix:String?,suffix:String?):String{
            if(value.isNullOrEmpty()) return ""
            return "${prefix?:""}$value${suffix?:""}"
        }

        fun string2Number(value:String?):Number?{
            if (value.isNullOrEmpty()) return null
            return if(value.contains('.')){
                value.toDoubleOrNull()
            }else {
                value.toIntOrNull()
            }
        }
    }
}