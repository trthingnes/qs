package edu.ntnu.idatt2105.g16.backend.entity

import edu.ntnu.idatt2105.g16.backend.dto.QueueEntryDTO
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class QueueEntry() {
    constructor(dto: QueueEntryDTO) : this() {
        update(dto)
        this.hasAssistant = false
    }

    @Id
    @GeneratedValue
    var id: Long = 0

    @NotNull(message = "Help cannot be null")
    var help: Boolean = true

    @NotNull(message = "Has Assistant cannot be null")
    var hasAssistant: Boolean = false

    @NotBlank(message = "Location cannot be blank")
    lateinit var location: String

    @ManyToOne
    @NotNull(message = "User cannot be null")
    lateinit var user: User

    fun update(dto: QueueEntryDTO) {
        dto.help?.let { this.help = it }
        dto.location?.let { this.location = it }
    }
}
