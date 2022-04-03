package edu.ntnu.idatt2105.g16.backend.dto

import edu.ntnu.idatt2105.g16.backend.entity.QueueEntry
import edu.ntnu.idatt2105.g16.backend.entity.User

data class QueueEntryDTO(
    val help: Boolean?,
    val location: String?,
    val hasAssistant: Boolean?,
    val username: String?,
){
    constructor(q: QueueEntry) : this(q.help, q.location, q.hasAssistant, q.user.username)
}
