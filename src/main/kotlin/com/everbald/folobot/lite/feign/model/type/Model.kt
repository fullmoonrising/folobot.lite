package com.everbald.folobot.lite.feign.model.type

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Название модели")
enum class Model {
    @field:Schema(description = "Легкая модель для простых задач, требующих максимальной скорости работы")
    @JsonProperty("GigaChat")
    GIGA_CHAT,

    @field:Schema(description = "Продвинутая модель для сложных задач, требующих креативности и лучшего следования инструкциям")
    @JsonProperty("GigaChat-Pro")
    GIGA_CHAT_PRO,

    @field:Schema(description = "Продвинутая модель для сложных задач, требующих высокого уровня креативности и качества работы")
    @JsonProperty("GigaChat-Max")
    GIGA_CHAT_MAX,
}
