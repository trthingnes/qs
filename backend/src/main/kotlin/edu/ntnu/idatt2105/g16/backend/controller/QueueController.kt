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

    @PostMapping("/{id}/{course}/add/{asmnt}")
    fun postQueueEntry(
        @RequestBody data: QueueEntryDTO,
        @PathVariable id: Long,
        @PathVariable course: Long,
        @PathVariable asmnt: Int
    ): ResponseEntity<Any> {
        val optionalQueue = queueRepository.findById(id)
        val optionalAssignment = assignmentRepository.findAllByCourseId(course)

        if (optionalQueue.isPresent && optionalAssignment.isPresent) {
            val assignment = optionalAssignment.get().filter { it.ordinal == asmnt }
            val queue = optionalQueue.get()
            val queueEntry = QueueEntry(data)
            //queueEntry.assignments.addAll(assignment)
            //queue.entries.add(queueEntry)
            println("Here")
            return ResponseEntity.ok(queueRepository.save(queue))
        } else {
            return ResponseEntity.badRequest().body("Could not find queue")
        }

    }
}
