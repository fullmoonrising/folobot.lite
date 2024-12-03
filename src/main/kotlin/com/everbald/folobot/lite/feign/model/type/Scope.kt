package com.everbald.folobot.lite.feign.model.type

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Название модели")
enum class Scope {
    @field:Schema(description = "Доступ для физических лиц")
    GIGACHAT_API_PERS,
    @field:Schema(description = "Доступ для ИП и юридических лиц по платным пакетам")
    GIGACHAT_API_B2B,
    @field:Schema(description = "Доступ для ИП и юридических лиц по схеме pay-as-you-go")
    GIGACHAT_API_CORP,
}
