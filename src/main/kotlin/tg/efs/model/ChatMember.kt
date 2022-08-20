package tg.efs.model

import javax.persistence.*

@Entity
@Table(name = "chat_members")
data class ChatMember(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, updatable = false)
    var chat: Chat,

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, updatable = false)
    var user: User
)
