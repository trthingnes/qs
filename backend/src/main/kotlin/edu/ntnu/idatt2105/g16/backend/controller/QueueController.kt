package edu.ntnu.idatt2105.g16.backend.controller

import edu.ntnu.idatt2105.g16.backend.dto.QueueEntryDTO
import edu.ntnu.idatt2105.g16.backend.entity.QueueEntry
import edu.ntnu.idatt2105.g16.backend.repository.AssignmentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/queue")
class QueueController {

    @Autowired
    private lateinit var assignmentRepository: AssignmentRepository

    @Autowired
    private lateinit var queueEntryRepository: QueueEntryRepository

    @PostMapping("/{id}/add")
    fun postQueueEntry(
        @RequestBody data: QueueEntryDTO,
        @PathVariable id: Long
    ): ResponseEntity<Any> {
        val optionalQueue = queueRepository.findById(id)

        if (optionalQueue.isPresent) {
            val queue = optionalQueue.get()
            val queueEntry = QueueEntry(data)
            queue.entries.add(queueEntry)
            println("Here")
            return ResponseEntity.ok(queueRepository.save(queue))
        } else {
            return ResponseEntity.badRequest().body("Could not find queue")
        }

    }
}
