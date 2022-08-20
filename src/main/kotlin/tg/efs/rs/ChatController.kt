package tg.efs.rs

import tg.efs.model.Chat
import tg.efs.repository.ChatRepository
import io.micronaut.http.annotation.*

@Controller("/rs/chats")
class ChatController(
    private val repository: ChatRepository
) {

    @Post("/save")
    fun save(@Body chat: Chat): Chat {
        return repository.save(chat)
    }

    @Get("/list")
    fun list(): Iterable<Chat> {
        return repository.findAll()
    }

    @Get("/find/{id}")
    fun find(@PathVariable id: Long): Chat? {
        return repository.findById(id).let {
            if (it.isPresent) it.get() else null
        }
    }

    @Delete("/delete/{id}")
    fun delete(@PathVariable id: Long) {
        repository.deleteById(id)
    }
}