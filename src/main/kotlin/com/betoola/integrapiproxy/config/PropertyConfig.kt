package com.betoola.integrapiproxy.config
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import javax.annotation.PostConstruct

import javax.validation.constraints.NotNull


@Component
@Validated
@ConfigurationProperties(prefix = "api")
open class PropertyConfig {


    @NotNull
    lateinit var basePath: String

}
