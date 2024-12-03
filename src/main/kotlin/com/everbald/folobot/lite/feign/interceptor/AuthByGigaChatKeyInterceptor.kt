package com.everbald.folobot.lite.feign.interceptor

import feign.RequestInterceptor
import feign.RequestTemplate
import mu.KotlinLogging
import org.springframework.http.HttpHeaders

class AuthByGigaChatKeyInterceptor(
    private val gigaChatKey: String
) : RequestInterceptor {
    companion object {
        const val BASIC: String = "Basic"
    }

    private val logger = KotlinLogging.logger { this::class.java }

    override fun apply(template: RequestTemplate) {
        if (!template.headers().containsKey(HttpHeaders.AUTHORIZATION)) {
            template.header(HttpHeaders.AUTHORIZATION, "$BASIC $gigaChatKey")
            logger.debug { "AuthByGigaChatKeyInterceptor inject ${template.headers()[HttpHeaders.AUTHORIZATION]} basic authorization for ${template.url()}." }
        } else {
            logger.warn {
                "AuthByGigaChatKeyInterceptor: request already has authorization header: ${template.headers()[HttpHeaders.AUTHORIZATION]}"
            }
        }
    }
}
