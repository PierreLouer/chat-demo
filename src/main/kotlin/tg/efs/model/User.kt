package tg.efs.model

import com.fasterxml.jackson.annotation.JsonIgnore
import io.micronaut.core.annotation.Introspected
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.PositiveOrZero
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,

    @NotEmpty @Column(unique = true) var identity: String,

    @NotEmpty
    @Column(length = 1024)
    var secret: String,

    @NotEmpty var name: String,
    @NotEmpty var forename: String,

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    var chats: Set<ChatMember> = mutableSetOf()
)

@Introspected
data class UserRegistrationArgument(
    @PositiveOrZero
    val id: Long?,

    @NotEmpty
    val identity: String,

    @NotEmpty
    @Size(min = 6)
    val secret: String,

    @NotEmpty
    @Size(min = 3)
    val name: String,

    @NotEmpty
    @Size(min = 3)
    val forename: String,
) {
    fun build(): User = User(
        id ?: 0, identity, secret, name, forename
    )
}