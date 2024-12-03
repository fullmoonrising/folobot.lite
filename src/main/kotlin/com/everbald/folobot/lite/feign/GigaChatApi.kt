package com.everbald.folobot.lite.feign

import com.everbald.folobot.lite.feign.model.request.CompletionsRequest
import com.everbald.folobot.lite.feign.model.response.CompletionsResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Tag(name = "API сервиса GigaChat")
interface GigaChatApi {
    @Operation(summary = "Запрос на генерацию текста")
    @PostMapping(
        value = ["/chat/completions"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
    )
    fun chatCompletions(
        @RequestBody body: CompletionsRequest,
    ): CompletionsResponse
}
