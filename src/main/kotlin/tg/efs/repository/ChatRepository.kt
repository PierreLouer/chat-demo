package tg.efs.repository

import tg.efs.model.Chat
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface ChatRepository: CrudRepository<Chat, Long> {
}