package edu.ntnu.idatt2105.g16.backend.entity

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class QueueEntry {
    @Id
    @GeneratedValue
    var id: Long = 0

    @NotBlank
    var location: String = ""

    @NotNull
    var help: Boolean = true

    @ManyToOne
    var queue: Queue = Queue()

    @ManyToMany
    var assignments: List<Assignment> = listOf()
}
