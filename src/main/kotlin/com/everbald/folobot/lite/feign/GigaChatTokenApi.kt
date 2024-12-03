package com.everbald.folobot.lite.feign

import com.everbald.folobot.lite.feign.model.response.TokenResponse
import com.everbald.folobot.lite.feign.model.type.Scope
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestPart
import java.util.*

@Tag(name = "API сервиса авторизщации GigaChat")
interface GigaChatTokenApi {
    @Operation(summary = "Запрос на выпуск токена")
    @PostMapping(
        value = ["/oauth"],
        consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    fun oauth(
        @RequestHeader("RqUID") requestId: UUID = UUID.randomUUID(),
        @RequestPart scope: Scope,
    ): TokenResponse
}
