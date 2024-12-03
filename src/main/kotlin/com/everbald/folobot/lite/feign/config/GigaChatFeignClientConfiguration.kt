package com.everbald.folobot.lite.feign.config

import feign.Request
import feign.codec.Decoder
import feign.codec.Encoder
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer
import org.springframework.context.annotation.Bean
import java.util.concurrent.TimeUnit

class GigaChatFeignClientConfiguration(
    @Value("\${app.feign.gigachat.connectTimeout:10000}") private val connectTimeout: Long,
    @Value("\${app.feign.gigachat.readTimeout:30000}") private val readTimeout: Long,
) {
    @Bean
    fun feignEncoder(): Encoder =
        customFeignEncoder()

    @Bean
    fun feignDecoder(customizers: ObjectProvider<HttpMessageConverterCustomizer>): Decoder =
        customFeignDecoder(customizers)

    @Bean
    fun requestOptions(): Request.Options =
        Request.Options(
            connectTimeout,
            TimeUnit.MILLISECONDS,
            readTimeout,
            TimeUnit.MILLISECONDS,
            true,
        )
}
