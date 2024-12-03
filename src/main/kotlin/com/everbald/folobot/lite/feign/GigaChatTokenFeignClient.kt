package com.everbald.folobot.lite.feign

import com.everbald.folobot.lite.feign.config.AuthByGigaChatKeyFeignConfig
import com.everbald.folobot.lite.feign.config.GigaChatFeignTokenClientConfiguration
import org.springframework.cloud.openfeign.FeignClient

@FeignClient(
    url = "\${app.feign.gigachat.token.url:https://ngw.devices.sberbank.ru:9443}/api/v2",
    configuration = [AuthByGigaChatKeyFeignConfig::class, GigaChatFeignTokenClientConfiguration::class],
    value = "gigachat-token",
)
interface GigaChatTokenFeignClient : GigaChatTokenApi
