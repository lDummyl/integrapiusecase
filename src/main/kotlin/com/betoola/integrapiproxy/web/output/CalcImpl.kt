package com.betoola.integrapiproxy.web.output

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import com.betoola.integrapiproxy.service.MessagePayloadExtractor


@RestController
open class CalcImpl(
    private val requestMapper: com.betoola.integrapiproxy.service.RequestMapper,
    private val responseMapper: com.betoola.integrapiproxy.service.ResponseMapper<Int>,
    private val client: com.betoola.integrapiproxy.web.input.SoapClient

) : com.betoola.integrapi.api.DefaultApi {

    override fun calculatorSumGet(a: Int?, b: Int?): ResponseEntity<Int> {
        val aggregateRequestPayload = MessagePayloadExtractor().aggregateRequestPayload(mutableMapOf(Pair("a", a), Pair("b", b)), null)
        val convertedRequest = requestMapper.convert(aggregateRequestPayload)
        val rawResponse = client.call(convertedRequest)
        return responseMapper.convert(rawResponse)
    }
}

