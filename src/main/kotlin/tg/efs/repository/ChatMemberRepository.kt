package tg.efs.repository

import tg.efs.model.Chat
import tg.efs.model.ChatMember
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface ChatMemberRepository: CrudRepository<ChatMember, Long> {
    fun findByChatEquals(chat: Chat): Iterable<ChatMember>
}