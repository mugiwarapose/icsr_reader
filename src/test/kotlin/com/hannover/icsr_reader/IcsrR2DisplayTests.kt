package com.hannover.icsr_reader

import com.hannover.icsr_reader.display.R2Display
import com.hannover.icsr_reader.reader.R2Reader
import jakarta.annotation.Resource
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class IcsrR2DisplayTests {

    @Test
    fun contextLoads() {
    }

    @Resource
    private lateinit var display: R2Display

    @Test
    fun testR2(){
        display.display()
    }

}
