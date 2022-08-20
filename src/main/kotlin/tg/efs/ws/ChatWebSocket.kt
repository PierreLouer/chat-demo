package tg.efs.ws

import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import io.micronaut.websocket.WebSocketBroadcaster
import io.micronaut.websocket.WebSocketSession
import io.micronaut.websocket.annotation.OnClose
import io.micronaut.websocket.annotation.OnMessage
import io.micronaut.websocket.annotation.OnOpen
import io.micronaut.websocket.annotation.ServerWebSocket
import java.util.function.Predicate

@ServerWebSocket("/chat/{topic}/{identity}")
@Secured(SecurityRule.IS_ANONYMOUS)
class ChatWebSocket(
    private val broadcaster: WebSocketBroadcaster
) {

    @OnOpen //
    fun onOpen(topic: String, identity: String, session: WebSocketSession) {
        val msg = "[$identity] Joined!"
        broadcaster.broadcastSync(msg, isValid(topic, session))
    }

    @OnMessage //
    fun onMessage(topic: String, identity: String,
                  message: String, session: WebSocketSession) {
        val msg = "[$identity] $message"
        broadcaster.broadcastSync(msg, isValid(topic, session)) //
    }

    @OnClose //
    fun onClose(topic: String, identity: String, session: WebSocketSession) {
        val msg = "[$identity] Disconnected!"
        broadcaster.broadcastSync(msg, isValid(topic, session))
    }

    private fun isValid(topic: String, session: WebSocketSession): Predicate<WebSocketSession> {
        return Predicate<WebSocketSession> {
            (it !== session && topic.equals(it.uriVariables.get("topic", String::class.java, null), ignoreCase = true))
        }
    }

}