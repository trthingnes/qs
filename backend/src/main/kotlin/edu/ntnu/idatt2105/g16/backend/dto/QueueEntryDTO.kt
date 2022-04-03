package edu.ntnu.idatt2105.g16.backend.dto

import edu.ntnu.idatt2105.g16.backend.entity.QueueEntry

data class QueueEntryDTO(
    val help: Boolean?,
    val location: String?,
){
    constructor(q: QueueEntry) : this(q.help, q.location)
}
