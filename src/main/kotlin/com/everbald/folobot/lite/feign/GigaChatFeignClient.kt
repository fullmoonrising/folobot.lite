package com.everbald.folobot.lite.feign

import com.everbald.folobot.lite.feign.config.AuthByGigaChatTokenFeignConfig
import org.springframework.cloud.openfeign.FeignClient
import com.everbald.folobot.lite.feign.config.GigaChatFeignClientConfiguration

@FeignClient(
    url = "\${app.feign.gigachat.url:https://gigachat.devices.sberbank.ru}/api/v1",
    configuration = [AuthByGigaChatTokenFeignConfig::class, GigaChatFeignClientConfiguration::class],
    value = "gigachat",
)
interface GigaChatFeignClient : GigaChatApi
