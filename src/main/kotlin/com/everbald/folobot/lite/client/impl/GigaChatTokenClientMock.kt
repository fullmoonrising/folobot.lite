package com.everbald.folobot.lite.client.impl

import com.everbald.folobot.lite.client.GigaChatTokenClient
import com.everbald.folobot.lite.domain.Token
import com.everbald.folobot.lite.feign.mapper.toToken
import com.everbald.folobot.lite.feign.model.response.TokenResponse
import com.everbald.folobot.lite.feign.model.type.Scope
import mu.KotlinLogging
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service
import java.time.Instant
import kotlin.time.Duration.Companion.minutes

@Service
@ConditionalOnProperty(name = ["mock.gigaChatClient"], havingValue = "true")
class GigaChatTokenClientMock() : GigaChatTokenClient {
    private val logger = KotlinLogging.logger { this::class.java }

    override fun oauth(scope: Scope): Token =
        TokenResponse(
            accessToken = RandomStringUtils.randomAlphanumeric(64),
            expiresAt = Instant.now().plusSeconds(30.minutes.inWholeSeconds)
        )
            .toToken()
}
