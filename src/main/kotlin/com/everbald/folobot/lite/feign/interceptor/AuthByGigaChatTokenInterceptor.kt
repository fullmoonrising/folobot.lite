package com.everbald.folobot.lite.feign.interceptor

import com.everbald.folobot.lite.auth.provider.GigaChatTokenProvider
import feign.RequestInterceptor
import feign.RequestTemplate
import mu.KotlinLogging
import org.springframework.cloud.openfeign.security.OAuth2AccessTokenInterceptor.BEARER
import org.springframework.http.HttpHeaders

class AuthByGigaChatTokenInterceptor(
    private val tokenProvider: GigaChatTokenProvider,
) : RequestInterceptor {
    private val logger = KotlinLogging.logger { this::class.java }

    override fun apply(template: RequestTemplate) {
        if (!template.headers().containsKey(HttpHeaders.AUTHORIZATION)) {
            template.header(HttpHeaders.AUTHORIZATION, "$BEARER ${tokenProvider.getToken()}")
            logger.debug { "AuthByGigaChatTokenInterceptor inject ${template.headers()[HttpHeaders.AUTHORIZATION]} accessToken for ${template.url()}." }
        } else {
            logger.warn {
                "AuthByGigaChatTokenInterceptor: request already has authorization header: ${template.headers()[HttpHeaders.AUTHORIZATION]}"
            }
        }
    }
}
