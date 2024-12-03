package com.everbald.folobot.lite.controller

import com.everbald.folobot.lite.service.GigaChatService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val gigaChatService: GigaChatService
) {
    @GetMapping(
        value = ["/chat/completions"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    fun chatCompletions(
        @RequestParam text: String,
    ): String = gigaChatService.chatCompletionSimple(text)

}