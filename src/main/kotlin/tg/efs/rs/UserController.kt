package tg.efs.rs

import tg.efs.model.User
import tg.efs.model.UserRegistrationArgument
import tg.efs.repository.UserRepository
import tg.efs.utils.sha256
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import javax.validation.Valid

@ExecuteOn(TaskExecutors.IO)
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/rs/users")
open class UserController(
    private val userRepository: UserRepository
) {

    @Post("/register")
    open fun register(@Body @Valid user: UserRegistrationArgument): User {
        return userRepository.save(user.build().apply {
            secret = secret.sha256()
        })
    }

    @Put("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Status(HttpStatus.OK)
    fun update(@Body user: User): User {
        return userRepository.save(user.apply {
            secret = secret.sha256()
        })
    }

}
