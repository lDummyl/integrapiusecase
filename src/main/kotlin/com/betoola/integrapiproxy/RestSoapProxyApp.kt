package com.betoola.integrapiproxy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class RestSoapProxyApp

fun main(args: Array<String>) {
    runApplication<RestSoapProxyApp>(*args)
}
