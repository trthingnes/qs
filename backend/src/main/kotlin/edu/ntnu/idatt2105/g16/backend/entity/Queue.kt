package edu.ntnu.idatt2105.g16.backend.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OneToOne

@Entity
class Queue {
    @Id
    @GeneratedValue
    var id: Long = 0

    @OneToOne
    lateinit var course: Course

    @OneToMany
    lateinit var entries: List<QueueEntry>
}
