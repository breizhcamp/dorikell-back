package org.breizhcamp.dorikell.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration

@Configuration
class ESClientConfig: ElasticsearchConfiguration() {
    override fun clientConfiguration(): ClientConfiguration =
        ClientConfiguration.builder()
            .connectedTo("localhost:9201")
            .build()
}