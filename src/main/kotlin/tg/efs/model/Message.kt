package tg.efs.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "messages")
data class Message(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var chat: Chat,

    @Column(length = 5000)
    var content: String,

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    var sendAt: Date
)
