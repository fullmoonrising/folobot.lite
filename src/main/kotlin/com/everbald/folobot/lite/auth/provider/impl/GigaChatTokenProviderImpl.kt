package com.everbald.folobot.lite.auth.provider.impl

import com.everbald.folobot.lite.auth.provider.GigaChatTokenProvider
import com.everbald.folobot.lite.client.GigaChatTokenClient
import com.everbald.folobot.lite.domain.Token
import com.everbald.folobot.lite.feign.model.type.Scope
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class GigaChatTokenProviderImpl(
    private val gigaChatTokenClient: GigaChatTokenClient
): GigaChatTokenProvider {
    private var token: Token? = null

    override fun getToken(): String =
        token
            ?.takeIf { it.expiresAt.isAfter(Instant.now()) }
            ?.accessToken
            ?: issueToken()

    private fun issueToken(): String =
        gigaChatTokenClient.oauth(Scope.GIGACHAT_API_PERS)
            .also { token = it }
            .accessToken
}