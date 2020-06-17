package com.betoola.integrapiproxy.web.output

@org.springframework.web.bind.annotation.RestController
open class CalcImpl(
    private val requestMapper: com.betoola.integrapiproxy.service.RequestMapper,
    private val responseMapper: com.betoola.integrapiproxy.service.ResponseMapper<Int>,
    private val client: com.betoola.integrapiproxy.web.input.SoapClient

) : com.betoola.integrapi.api.DefaultApi {

  

        override fun calculatorSumGet(a: kotlin.Int?, b: kotlin.Int?): org.springframework.http.ResponseEntity<kotlin.Int> {
        val aggregateRequestPayload = com.betoola.integrapiproxy.service.MessagePayloadExtractor().aggregateRequestPayload(mutableMapOf(Pair("a", a), Pair("b", b)), null)
        val convertedRequest = requestMapper.convert(aggregateRequestPayload)
        val rawResponse = client.call(convertedRequest)
        return responseMapper.convert(rawResponse)

    }

}