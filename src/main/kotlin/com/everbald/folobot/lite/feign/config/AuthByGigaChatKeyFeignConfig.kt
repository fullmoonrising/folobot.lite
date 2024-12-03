package com.everbald.folobot.lite.feign.config

import com.everbald.folobot.lite.feign.interceptor.AuthByGigaChatKeyInterceptor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean

class AuthByGigaChatKeyFeignConfig {
    @Bean
    fun authByGigaChatKeyInterceptor(
        @Value("\${gigachat.key}") gigaChatKey: String
    ): AuthByGigaChatKeyInterceptor = AuthByGigaChatKeyInterceptor(gigaChatKey)
}
