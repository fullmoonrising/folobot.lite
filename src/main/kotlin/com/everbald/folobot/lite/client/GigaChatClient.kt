package com.everbald.folobot.lite.client

import com.everbald.folobot.lite.feign.model.request.CompletionsRequest
import com.everbald.folobot.lite.feign.model.response.CompletionsResponse

interface GigaChatClient {
    /**
     * Запрос ответа модели
     */
    fun chatCompletions(body: CompletionsRequest): CompletionsResponse
}
