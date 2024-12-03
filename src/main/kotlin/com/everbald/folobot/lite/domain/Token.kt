package com.everbald.folobot.lite.domain

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

data class Token(
    @JsonProperty("access_token")
    val accessToken: String,

    @JsonProperty("expires_at")
    val expiresAt: Instant
)
