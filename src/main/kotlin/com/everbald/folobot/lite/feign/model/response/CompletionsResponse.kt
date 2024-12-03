package com.everbald.folobot.lite.feign.model.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Ответ модели, сгенерированный на основе переданных сообщений")
@JsonIgnoreProperties(ignoreUnknown = true)
data class CompletionsResponse(
    @field:Schema(description = "Массив ответов модели")
    val choices: List<Choice>,

    @field:Schema(description = "Дата и время создания ответа в формате Unix time")
    val created: Long,

    @field:Schema(description = "Название модели")
    val model: String,

    @field:Schema(description = "Данные об использовании модели")
    val usage: Usage,

    @field:Schema(description = "Название вызываемого метода")
    @JsonProperty("object")
    val methodObject: String,
) {
    @Schema(description = "Ответо модели")
    data class Choice(
        @field:Schema(description = "Сгенерированное сообщение")
        val message: Message,

        @field:Schema(description = "Индекс сообщения в массиве, начиная с ноля")
        val index: Int,

        @field:Schema(description = "Причина завершения гипотезы")
        @JsonProperty("finish_reason")
        val finishReason: FinishReason,
    ) {
        @Schema(description = "Сгенерированное сообщение")
        data class Message(
            @field:Schema(description = "Роль автора сообщения")
            val role: Role,

            @field:Schema(description = "Содержимое сообщения, например, результат генерации")
            val content: String,

            @field:Schema(description = "Массив сообщений, описывающих работу встроенных функций")
            @JsonProperty("data_for_context")
            val dataForContext: List<JsonNode>?,
        ) {
            @Schema(description = "Роль автора сообщения")
            enum class Role {
                @field:Schema(description = "Сообщение пользователя")
                @JsonProperty("assistant")
                ASSISTANT,

                @field:Schema(description = "Используется при работе встроенных функций в режиме потоковой передачи токенов")
                @JsonProperty("function_in_progress")
                FUNCTION_IN_PROGRESS,
            }
        }

        @Schema(description = "Причина завершения гипотезы")
        enum class FinishReason {
            @field:Schema(description = "Модель закончила формировать гипотезу и вернула полный ответ")
            @JsonProperty("stop")
            STOP,

            @field:Schema(description = "Достигнут лимит токенов в сообщении")
            @JsonProperty("length")
            LENGTH,

            @field:Schema(
                description = "При запросе была вызвана встроенная функция или сгенерированы аргументы для пользовательской функции",
            )
            @JsonProperty("function_call")
            FUNCTION_CALL,

            @field:Schema(description = "Запрос попадает под тематические ограничения")
            @JsonProperty("blacklist")
            BLACKLIST,

            @field:Schema(description = "Ответ модели содержит невалидные аргументы пользовательской функции")
            @JsonProperty("error")
            ERROR,
        }
    }

    @Schema(description = "Данные об использовании модели")
    data class Usage(
        @field:Schema(description = "Количество токенов во входящем сообщении")
        @JsonProperty("prompt_tokens")
        private var promptTokens: Int?,

        @field:Schema(description = "Количество токенов, сгенерированных моделью")
        @JsonProperty("completion_tokens")
        private val completionTokens: Int?,

        @field:Schema(description = "Общее количество токенов")
        @JsonProperty("total_tokens")
        private val totalTokens: Int?,
    )
}
