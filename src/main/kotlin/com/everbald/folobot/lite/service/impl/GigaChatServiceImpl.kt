package com.everbald.folobot.lite.service.impl

import com.everbald.folobot.lite.client.GigaChatClient
import com.everbald.folobot.lite.feign.model.request.CompletionsRequest
import com.everbald.folobot.lite.feign.model.response.CompletionsResponse
import com.everbald.folobot.lite.feign.model.type.Model
import com.everbald.folobot.lite.service.GigaChatService
import com.everbald.folobot.lite.utils.toList
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class GigaChatServiceImpl(
    private val client: GigaChatClient,
) : GigaChatService {
    private val logger = KotlinLogging.logger { this::class.java }

    override fun chatCompletions(body: CompletionsRequest): CompletionsResponse =
        try {
            client.chatCompletions(body)
                .also { logger.trace { "Made chat completions request to AI Gateway with body $body" } }
        } catch (ex: Exception) {
            throw RuntimeException("Error occurred while making chat completions request to GigaChat with body $body", ex)
        }

    override fun chatCompletionSimple(
        request: String,
        model: Model,
    ): String =
        request
            .toSimpleCompletionsRequest(model)
            .let { chatCompletions(it) }
            .extractSimpleResponse()

    private fun String.toSimpleCompletionsRequest(model: Model): CompletionsRequest =
        CompletionsRequest(
            model = model,
            messages =
                CompletionsRequest.Message(
                    role = CompletionsRequest.Message.Role.USER,
                    content = this,
                )
                    .toList(),
        )

    private fun CompletionsResponse.extractSimpleResponse(): String =
        this.choices
            .firstOrNull()
            ?.takeIf { it.finishReason == CompletionsResponse.Choice.FinishReason.STOP }
            ?.message
            ?.content
            ?: throw RuntimeException("Couldn't extract simple response from chat completions response $this")
}
