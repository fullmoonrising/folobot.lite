package com.everbald.folobot.lite.client.impl

import com.everbald.folobot.lite.client.GigaChatClient
import com.everbald.folobot.lite.feign.model.request.CompletionsRequest
import com.everbald.folobot.lite.feign.model.response.CompletionsResponse
import com.everbald.folobot.lite.feign.model.type.Model
import mu.KotlinLogging
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service

@Service
@ConditionalOnProperty(name = ["mock.gigaChatClient"], havingValue = "true")
class GigaChatClientMock() : GigaChatClient {
    private val logger = KotlinLogging.logger { this::class.java }

    override fun chatCompletions(body: CompletionsRequest): CompletionsResponse =
        CompletionsResponse(
            choices =
                listOf(
                    CompletionsResponse.Choice(
                        message =
                            CompletionsResponse.Choice.Message(
                                role = CompletionsResponse.Choice.Message.Role.ASSISTANT,
                                content = RandomStringUtils.randomAlphabetic(32),
                                dataForContext = emptyList(),
                            ),
                        index = 0,
                        finishReason = CompletionsResponse.Choice.FinishReason.STOP,
                    ),
                ),
            created = System.currentTimeMillis(),
            model = Model.GIGA_CHAT.name,
            usage =
                CompletionsResponse.Usage(
                    promptTokens = 32,
                    completionTokens = 32,
                    totalTokens = 64,
                ),
            methodObject = "chat.completion",
        )
            .also { logger.info { "Mock AI Gateway request: chat/completions. request: $body" } }
}
