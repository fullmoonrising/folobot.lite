package com.everbald.folobot.lite.feign.mapper

import com.everbald.folobot.lite.domain.Token
import com.everbald.folobot.lite.feign.model.response.TokenResponse

fun TokenResponse.toToken(): Token =
    Token(
        accessToken = accessToken,
        expiresAt = expiresAt
    )