package com.everbald.folobot.lite.client.impl

import com.everbald.folobot.lite.client.GigaChatTokenClient
import com.everbald.folobot.lite.domain.Token
import com.everbald.folobot.lite.feign.GigaChatTokenFeignClient
import com.everbald.folobot.lite.feign.mapper.toToken
import com.everbald.folobot.lite.feign.model.type.Scope
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service

@Service
@ConditionalOnProperty(name = ["mock.gigaChatClient"], havingValue = "false", matchIfMissing = true)
class GigaChatTokenClientImpl(
    private val gigaChatTokenFeignClient: GigaChatTokenFeignClient,
) : GigaChatTokenClient {
    override fun oauth(scope: Scope): Token =
        gigaChatTokenFeignClient.oauth(scope = scope)
            .toToken()
}
