package com.everbald.folobot.lite.service

import com.everbald.folobot.lite.feign.model.request.CompletionsRequest
import com.everbald.folobot.lite.feign.model.response.CompletionsResponse
import com.everbald.folobot.lite.feign.model.type.Model

interface GigaChatService {
    /**
     * Запрос ответа модели. Возвращает полную модель ответа
     */
    fun chatCompletions(body: CompletionsRequest): CompletionsResponse

    /**
     * Простой запрос на генерацию текста. Принимает простой промт [request] к модели [model] и возвращает один текстовый ответ
     */
    fun chatCompletionSimple(
        request: String,
        model: Model = Model.GIGA_CHAT,
    ): String
}
