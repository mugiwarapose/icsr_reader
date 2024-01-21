package com.hannover.icsr_reader

import com.hannover.icsr_reader.reader.R2Reader
import jakarta.annotation.Resource
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class IcsrReaderApplicationTests {

    @Test
    fun contextLoads() {
    }

    @Resource
    private lateinit var reader: R2Reader

    @Test
    fun testR2(){
        val file = File("D:\\EOCTZ\\R2\\E2B2023059_PRD640_20191209153438.XML")
        val data = reader.process(file)
        data.forEach {
            println(it)
        }
    }

}
