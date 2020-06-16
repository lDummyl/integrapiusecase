package com.betoola.integrapiproxy.dto

class SoapRequest {

    val params: MutableMap<String, Int> = mutableMapOf()

    fun merge(soapRequest: SoapRequest): SoapRequest{
        val mergedParams = this.params.plus(soapRequest.params)
        val result = SoapRequest()
        result.params.putAll(mergedParams)
        return result
    }
}
