package com.everbald.folobot.lite.feign.model.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import io.swagger.v3.oas.annotations.media.Schema
import com.everbald.folobot.lite.feign.model.type.Model
import java.math.BigDecimal
import java.util.UUID

@Schema(description = "Запрос ответа модели")
data class CompletionsRequest(
    @field:Schema(description = "Название модели")
    val model: Model,

    @field:Schema(description = "Массив сообщений, которыми пользователь обменивался с моделью")
    val messages: List<Message>,

    @field:Schema(description = "Режим работы с функциями")
    @JsonProperty("function_call")
    val functionCall: FunctionCall? = null,

    @field:Schema(description = "Массив с описанием пользовательских функций")
    val functions: List<Function>? = null,

    @field:Schema(description = "Температура выборки")
    val temperature: Float? = null,

    @field:Schema(description = "Альтернатива температуре", minimum = "0", maximum = "1")
    @JsonProperty("top_p")
    val topP: Float? = null,

    @field:Schema(description = "Указывает что сообщения надо передавать по частям в потоке")
    val stream: Boolean? = false,

    @field:Schema(description = "Максимальное количество токенов, которые будут использованы для создания ответов")
    @JsonProperty("max_tokens")
    val maxTokens: Int? = null,

    @field:Schema(description = "Количество повторений слов")
    @JsonProperty("repetition_penalty")
    val repetitionPenalty: Float? = null,

    @field:Schema(description = "Задает минимальный интервал в секундах, который проходит между отправкой токенов")
    @JsonProperty("update_interval")
    private val updateInterval: BigDecimal? = null,
) {
    @Schema(description = "Сообщение")
    data class Message(
        @field:Schema(description = "Роль автора сообщения")
        val role: Role?,

        @field:Schema(description = "Содержимое сообщения. Зависит от роли")
        val content: String?,

        @field:Schema(description = "Идентификатор, который объединяет массив функций, переданных в запросе")
        @JsonProperty("functions_state_id")
        val functionsStateId: UUID? = null,

        @field:Schema(description = "Массив идентификаторов файлов, которые нужно использовать при генерации")
        val attachments: List<String>? = null,
    ) {
        @Schema(description = "Роль автора сообщения")
        enum class Role {
            @field:Schema(
                description = "Системный промпт, который задает роль модели, например, должна модель отвечать как академик или как школьник",
            )
            @JsonProperty("system")
            SYSTEM,

            @field:Schema(description = "Ответ модели")
            @JsonProperty("user")
            USER,

            @field:Schema(description = "Сообщение пользователя")
            @JsonProperty("assistant")
            ASSISTANT,

            @field:Schema(description = "Сообщение с результатом работы пользовательской функции")
            @JsonProperty("function")
            FUNCTION,
        }
    }

    @Schema(description = "Режим работы с функциями")
    enum class FunctionCall {
        @field:Schema(description = "Режим работы по умолчанию. сгенерирует ответ в соответствии с полученными сообщениями")
        @JsonProperty("none")
        NONE,

        @field:Schema(description = "В зависимости от содержимого запроса, модель решает сгенерировать сообщение или вызвать функцию")
        @JsonProperty("auto")
        AUTO,
    }

    @Schema(description = "Пользовательская функция")
    data class Function(
        @field:Schema(description = "Название пользовательской функции, для которой будут сгенерированы аргументы")
        val name: String,

        @field:Schema(description = "Текстовое описание функции")
        val description: String?,

        @field:Schema(description = "Валидный JSON-объект с набором пар ключ-значение, которые описывают аргументы функции")
        val parameters: JsonNode,

        @field:Schema(
            description = "Объекты с парами запрос_пользователя-параметры_функции, которые будут служить модели примерами ожидаемого результата",
        )
        @JsonProperty("functions_state_id")
        val fewShotExamples: List<ShotExample>?,

        @field:Schema(description = "JSON-объект с описанием параметров, которые может вернуть ваша функция")
        @JsonProperty("return_parameters")
        val returnParameters: JsonNode?,

        ) {
        @Schema(description = "Пример ожидаемого результата")
        data class ShotExample(
            @field:Schema(description = "Запрос пользователя")
            val request: String,

            @field:Schema(description = "Пример заполнения параметров пользовательской функции")
            val params: JsonNode,
        )
    }
}
