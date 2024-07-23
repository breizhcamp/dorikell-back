package org.breizhcamp.dorikell.infrastructure.http.utils

import org.breizhcamp.dorikell.config.http.HttpClientConfig
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

inline fun <reified T : AuthorizedHttpClient>createAuthorizedClient(
    authorizedClientManager: OAuth2AuthorizedClientManager,
    config: HttpClientConfig,
): T {
    val oauth = ServletOAuth2AuthorizedClientExchangeFilterFunction(
        authorizedClientManager
    )

    oauth.setDefaultClientRegistrationId("keycloak")

    val client = WebClient
        .builder()
        .baseUrl(config.url)
        .filter(oauth)
        .build()

    val factory = HttpServiceProxyFactory
        .builder()
        .exchangeAdapter(
            WebClientAdapter
                .forClient(client)
                .asReactorExchangeAdapter()
        )
        .build()

    return factory.createClient(T::class.java)
}