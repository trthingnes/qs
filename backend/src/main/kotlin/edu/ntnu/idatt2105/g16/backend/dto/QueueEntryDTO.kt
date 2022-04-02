package edu.ntnu.idatt2105.g16.backend.dto

import edu.ntnu.idatt2105.g16.backend.entity.Queue

data class QueueEntryDTO(
    val help: Boolean,
    val location: String,
    val hasAssistant: Boolean,
)
