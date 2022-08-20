package tg.efs.repository

import tg.efs.model.Message
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface MessageRepository: CrudRepository<Message, Long> {
}