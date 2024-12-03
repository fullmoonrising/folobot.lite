package com.everbald.folobot.lite.client.impl

import com.everbald.folobot.lite.client.GigaChatClient
import com.everbald.folobot.lite.feign.GigaChatFeignClient
import com.everbald.folobot.lite.feign.model.request.CompletionsRequest
import com.everbald.folobot.lite.feign.model.response.CompletionsResponse
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service

@Service
@ConditionalOnProperty(name = ["mock.gigaChatClient"], havingValue = "false", matchIfMissing = true)
class GigaChatClientImpl(
    private val gigaChatFeignClient: GigaChatFeignClient,
) : GigaChatClient {
    override fun chatCompletions(body: CompletionsRequest): CompletionsResponse =
        gigaChatFeignClient.chatCompletions(body = body)
}
