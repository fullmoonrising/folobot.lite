package com.everbald.folobot.lite.feign.config

import com.everbald.folobot.lite.config.objectMapper
import feign.codec.Decoder
import feign.codec.Encoder
import feign.optionals.OptionalDecoder
import org.springframework.beans.factory.ObjectFactory
import org.springframework.beans.factory.ObjectProvider
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder
import org.springframework.cloud.openfeign.support.SpringDecoder
import org.springframework.cloud.openfeign.support.SpringEncoder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

fun customFeignEncoder(): Encoder =
    MappingJackson2HttpMessageConverter(objectMapper)
        .let { ObjectFactory { HttpMessageConverters(it) } }
        .let { SpringEncoder(it) }

fun customFeignDecoder(customizers: ObjectProvider<HttpMessageConverterCustomizer>): Decoder =
    MappingJackson2HttpMessageConverter(objectMapper)
        .let { ObjectFactory { HttpMessageConverters(it) } }
        .let { OptionalDecoder(ResponseEntityDecoder(SpringDecoder(it, customizers))) }