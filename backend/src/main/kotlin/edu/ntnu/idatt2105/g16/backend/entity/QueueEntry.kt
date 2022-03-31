package edu.ntnu.idatt2105.g16.backend.entity

import edu.ntnu.idatt2105.g16.backend.dto.QueueEntryDTO
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class QueueEntry () {
    constructor(dto: QueueEntryDTO): this() {
        this.help = dto.help
        this.location = dto.location
        this.queue = dto.queue
    }

    @Id
    @GeneratedValue
    var id: Long = 0

    @NotNull(message = "Help cannot be null")
    var help: Boolean = true

    @NotBlank(message = "Location cannot be blank")
    lateinit var location: String

    @ManyToOne
    lateinit var queue: Queue

    @ManyToMany
    var assignments: List<Assignment> = listOf()
}
