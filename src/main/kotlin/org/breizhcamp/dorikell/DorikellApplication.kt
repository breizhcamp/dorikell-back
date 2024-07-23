package org.breizhcamp.dorikell

import org.breizhcamp.dorikell.config.DorikellConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(DorikellConfig::class)
class DorikellApplication

fun main(args: Array<String>) {
    runApplication<DorikellApplication>(*args)
}
