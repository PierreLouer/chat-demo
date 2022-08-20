package tg.efs.repository

import tg.efs.model.User
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface UserRepository: CrudRepository<User, Long> {

    fun findByIdentityEqualsAndSecretEquals(identity: String, secret: String): User?

}