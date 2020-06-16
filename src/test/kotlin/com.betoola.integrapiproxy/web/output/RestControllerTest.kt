package com.betoola.integrapiproxy.web.output

import controller.CustomResultHandler
import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


class RestControllerTest : ControllerParent() {

   
    @Test
    @Throws(Exception::class)
    fun getSumValidOk() {
        this.mockMvc.perform(
            get("/root/calculator/sum") 
                .param("a", "3")
                .param("b", "5")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().string("8"))
            .andDo(CustomResultHandler.handleResult(name.methodName, MockMvcRestDocumentation::document))
    }
}
