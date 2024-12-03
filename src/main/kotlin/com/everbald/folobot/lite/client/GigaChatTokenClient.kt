package com.everbald.folobot.lite.client

import com.everbald.folobot.lite.domain.Token
import com.everbald.folobot.lite.feign.model.type.Scope

interface GigaChatTokenClient {
    /**
     * Выпуск токена
     */
    fun oauth(scope: Scope): Token
}
