package tg.efs.rs

import tg.efs.model.Message
import tg.efs.repository.ChatRepository
import tg.efs.repository.MessageRepository
import io.micronaut.http.annotation.*

@Controller("/rs/chats/{chatId}/messages")
class MessageController(
    private val repository: MessageRepository,
    private val chatRepository: ChatRepository,
) {

    @Post("/save")
    fun save(@PathVariable chatId: Long, @Body message: Message): Message {
        return repository.save(message.copy(
            chat = chatRepository.findById(chatId).get()
        ))
    }

    @Suppress("MnUnresolvedPathVariable")
    @Get("/list")
    fun list(@PathVariable chatId: Long): Iterable<Message> {
        return repository.findAll()
    }

    @Suppress("MnUnresolvedPathVariable")
    @Get("/find/{id}")
    fun find(@PathVariable chatId: Long, @PathVariable id: Long): Message? {
        return repository.findById(id).let {
            if (it.isPresent) it.get() else null
        }
    }

    @Suppress("MnUnresolvedPathVariable")
    @Delete("/delete/{id}")
    fun delete(@PathVariable id: Long) {
        repository.deleteById(id)
    }

}