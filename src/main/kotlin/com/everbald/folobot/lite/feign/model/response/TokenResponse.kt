package com.everbald.folobot.lite.feign.model.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

data class TokenResponse(
    @JsonProperty("access_token")
    val accessToken: String,

    @JsonProperty("expires_at")
    val expiresAt: Instant
)
