package tg.efs.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "chats")
data class Chat(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,

    @Column(unique = true) var topic: String,

    var description: String,

    @OneToMany(mappedBy = "chat")
    @JsonIgnore
    var members: Set<ChatMember>,

    @OneToMany(mappedBy = "chat")
    @JsonIgnore
    var messages: Set<Message>,

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    var date: Date
)
