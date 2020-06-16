package com.betoola.integrapiproxy.web.input

import org.springframework.stereotype.Service
import org.tempuri.Calculator
import com.betoola.integrapiproxy.dto.SoapRequest
import com.betoola.integrapiproxy.dto.SoapResponse

@Service
class SoapClient {
    fun call(soapRequest: SoapRequest): SoapResponse {
        val service = Calculator()
        val port = service.calculatorSoap12

        val a = soapRequest.params["a"] ?: 0
        val b = soapRequest.params["b"] ?: 0

        val addResult = port.add(a, b)
        val soapResponse = SoapResponse()
        soapResponse.bodyPayload = addResult
        return soapResponse
    }
}

