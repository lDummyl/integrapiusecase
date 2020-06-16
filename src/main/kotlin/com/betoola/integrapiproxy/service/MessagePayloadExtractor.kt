package com.betoola.integrapiproxy.service

import com.betoola.integrapiproxy.dto.RestRequest

class MessagePayloadExtractor {

    fun aggregateRequestPayload(params: MutableMap<String, Int?>, body: Any?): RestRequest {
        val restRequest = RestRequest()
        restRequest.body = body
        restRequest.params = params
        return restRequest;
    }
}