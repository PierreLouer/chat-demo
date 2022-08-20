package tg.efs.rs

import tg.efs.model.ChatMember
import tg.efs.repository.ChatMemberRepository
import tg.efs.repository.ChatRepository
import tg.efs.repository.UserRepository
import io.micronaut.http.annotation.*

@Controller("/rs/chats/{chatId}/members")
class ChatMemberController(
    private val repository: ChatMemberRepository,
    private val chatRepository: ChatRepository,
    private val userRepository: UserRepository,
) {

    @Post("/add/{userId}")
    fun save(@PathVariable chatId: Long, @PathVariable userId: Long): ChatMember {
        return repository.save(
            ChatMember(
            chat = chatRepository.findById(chatId).get(),
            user = userRepository.findById(userId).get()
        )
        )
    }

    @Get("/list")
    fun list(@PathVariable chatId: Long): Iterable<ChatMember> {
        return repository.findByChatEquals(chatRepository.findById(chatId).get())
    }

    @Get("/find/{id}")
    fun find(@PathVariable chatId: Long, @PathVariable id: Long): ChatMember? {
        return repository.findById(id).let {
            if (it.isPresent) it.get() else null
        }
    }

    @Delete("/delete/{id}")
    fun delete(@PathVariable chatId: Long, @PathVariable id: Long) {
        repository.deleteById(id)
    }
}