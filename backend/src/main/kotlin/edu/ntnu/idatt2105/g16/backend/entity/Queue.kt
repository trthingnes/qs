package edu.ntnu.idatt2105.g16.backend.entity

import edu.ntnu.idatt2105.g16.backend.dto.QueueDTO
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OneToOne

@Entity
class Queue() {
    constructor(dto: QueueDTO): this() {
        this.course = dto.course
    }

    @Id
    @GeneratedValue
    var id: Long = 0

    @OneToOne
    lateinit var course: Course

    @OneToMany
    var entries: List<QueueEntry> = listOf()
}
