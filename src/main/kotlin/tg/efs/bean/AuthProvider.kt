package tg.efs.bean

import tg.efs.repository.UserRepository
import tg.efs.utils.sha256
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.AuthenticationProvider
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink

@Singleton
class AuthProvider(
    private val userRepository: UserRepository
): AuthenticationProvider {

    override fun authenticate(
        httpRequest: HttpRequest<*>?,
        authenticationRequest: AuthenticationRequest<*, *>
    ): Publisher<AuthenticationResponse> {
        return Flux.create({ emitter: FluxSink<AuthenticationResponse> ->
            val user = userRepository.findByIdentityEqualsAndSecretEquals(
                identity = authenticationRequest.identity as String? ?: "",
                secret = (authenticationRequest.secret as String? ?: "").sha256()
            )

            if (user != null) {
                emitter.next(AuthenticationResponse.success(user.identity))
                emitter.complete()
            } else {
                emitter.error(AuthenticationResponse.exception())
            }
        }, FluxSink.OverflowStrategy.BUFFER)
    }
}