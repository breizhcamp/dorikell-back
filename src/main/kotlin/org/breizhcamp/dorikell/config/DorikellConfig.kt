package org.breizhcamp.dorikell.config

import org.breizhcamp.dorikell.config.http.HttpClientConfig
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "dorikell")
data class DorikellConfig(
    val kalon: HttpClientConfig,
    val bilhed: HttpClientConfig
)
