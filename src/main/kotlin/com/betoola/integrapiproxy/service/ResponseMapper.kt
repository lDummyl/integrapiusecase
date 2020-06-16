package com.betoola.integrapiproxy.service

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import com.betoola.integrapiproxy.dto.SoapResponse

@Service
class ResponseMapper <T>{
    fun convert(soapResponse: SoapResponse): ResponseEntity<T> {
        val payload = soapResponse.bodyPayload
        return ResponseEntity(payload as T , HttpStatus.OK)
    }
}
