package edu.ntnu.idatt2105.g16.backend.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Queue {
    @Id
    @GeneratedValue
    var id: Long = 0

    @OneToMany
    var entries: List<QueueEntry> = listOf()
}