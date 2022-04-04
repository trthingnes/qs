package edu.ntnu.idatt2105.g16.backend.dto

import edu.ntnu.idatt2105.g16.backend.entity.QueueEntry

data class QueueEntryDTO(
    val id: Long,
    val help: Boolean?,
    val location: String?,
    val hasAssistant: Boolean?,
    val username: String?,
){
    constructor(q: QueueEntry) : this(q.id, q.help, q.location, q.hasAssistant, q.user.username)
}
