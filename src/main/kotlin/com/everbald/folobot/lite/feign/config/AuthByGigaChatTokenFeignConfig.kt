package com.everbald.folobot.lite.feign.config

import com.everbald.folobot.lite.auth.provider.GigaChatTokenProvider
import org.springframework.context.annotation.Bean
import com.everbald.folobot.lite.feign.interceptor.AuthByGigaChatTokenInterceptor

class AuthByGigaChatTokenFeignConfig {
    @Bean
    fun authByGigaChatTokenInterceptor(tokenProvider: GigaChatTokenProvider): AuthByGigaChatTokenInterceptor =
        AuthByGigaChatTokenInterceptor(tokenProvider)
}
