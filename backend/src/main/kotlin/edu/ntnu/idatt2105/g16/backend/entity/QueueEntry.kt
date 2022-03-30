package edu.ntnu.idatt2105.g16.backend.entity

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class QueueEntry {
    @Id
    @GeneratedValue
    var id: Long = 0

    @NotNull
    var help: Boolean = true

    @NotBlank
    lateinit var location: String

    @ManyToOne
    lateinit var queue: Queue

    @ManyToMany
    lateinit var assignments: List<Assignment>
}
