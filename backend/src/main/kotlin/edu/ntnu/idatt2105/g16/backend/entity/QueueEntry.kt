package edu.ntnu.idatt2105.g16.backend.entity

import edu.ntnu.idatt2105.g16.backend.dto.QueueEntryDTO
import edu.ntnu.idatt2105.g16.backend.dto.UserDTO
import io.swagger.annotations.ApiModel
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@ApiModel(description = "An entry in a courses queue.")
class QueueEntry() {
    constructor(dto: QueueEntryDTO) : this() {
        update(dto)
    }

    @Id
    @GeneratedValue
    var id: Long = 0

    @NotNull(message = "Help cannot be null")
    var help: Boolean = true

    @NotNull(message = "Has assistant cannot be null")
    var hasAssistant: Boolean = false

    @NotBlank(message = "Location cannot be blank")
    lateinit var location: String

    @ManyToOne
    @NotNull(message = "User cannot be null")
    lateinit var user: User

    fun update(dto: QueueEntryDTO) {
        dto.help?.let { this.help = it }
        dto.location?.let { this.location = it }
        dto.hasAssistant?.let { this.hasAssistant = it }
    }
}
