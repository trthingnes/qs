package edu.ntnu.idatt2105.g16.backend.controller

import edu.ntnu.idatt2105.g16.backend.dto.QueueEntryDTO
import edu.ntnu.idatt2105.g16.backend.entity.QueueEntry
import edu.ntnu.idatt2105.g16.backend.repository.CourseRepository
import edu.ntnu.idatt2105.g16.backend.repository.QueueEntryRepository
import edu.ntnu.idatt2105.g16.backend.repository.UserRepository
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/courses/{id}")
@Api(description = "Operations pertaining to creating, updating and getting queue entries.")
class QueueController {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var courseRepository: CourseRepository

    @Autowired
    private lateinit var queueEntryRepository: QueueEntryRepository

    @GetMapping("/queue")
    @ApiOperation("Gets a list of all queue entries in the course with the given id.")
    fun getAllQueueEntries(@PathVariable id: Long): ResponseEntity<Any> {
        val optionalCourse = courseRepository.findCourseById(id)

        return if (optionalCourse.isPresent) {
            ResponseEntity.ok(
                optionalCourse.get().queueEntries.map {
                    QueueEntryDTO(it)
                }
            )
        } else {
            ResponseEntity.badRequest().body("Could not find course")
        }
    }

    @PostMapping("/queue")
    @ApiOperation("Adds a new queue entry in the course with the given id.")
    fun createQueueEntry(principal: Principal, @PathVariable id: Long, @RequestBody data: QueueEntryDTO): ResponseEntity<Any> {
        val optionalCourse = courseRepository.findById(id)
        val optionalUser = userRepository.findByUsername(principal.name)

        return if (optionalCourse.isPresent && optionalUser.isPresent) {
            val course = optionalCourse.get()
            var queueEntry = QueueEntry(data)
            queueEntry.user = optionalUser.get()
            queueEntry = queueEntryRepository.save(queueEntry)
            course.queueEntries.add(queueEntry)
            courseRepository.save(course)
            return ResponseEntity.ok(QueueEntryDTO(queueEntry))
        } else {
            ResponseEntity.badRequest().body("Could not find course")
        }
    }

    @GetMapping("/queue/{entryId}")
    @ApiOperation("Get a single queue entry from id")
    fun getQueueEntry(@PathVariable id: Long, @PathVariable entryId: Long): ResponseEntity<Any> {
        val optionalEntry = queueEntryRepository.findById(entryId)

        return if (optionalEntry.isPresent) {
            ResponseEntity.ok(QueueEntryDTO(optionalEntry.get()))
        } else {
            ResponseEntity.badRequest().body("No entry found")
        }
    }

    @PutMapping("/queue/{entryId}")
    @ApiOperation("Updates the queue entry with the given id.")
    fun updateQueueEntry(@PathVariable id: Long, @PathVariable entryId: Long, @RequestBody dto: QueueEntryDTO): ResponseEntity<Any> {
        val optionalQueueEntry = queueEntryRepository.findById(entryId)
        if (!optionalQueueEntry.isPresent) {
            return ResponseEntity.badRequest().body("Queue entry not found.")
        }

        val queueEntry = optionalQueueEntry.get()
        queueEntry.update(dto)

        return ResponseEntity.ok(QueueEntryDTO(queueEntryRepository.save(queueEntry)))
    }

    @DeleteMapping("/queue/{entryId}")
    @ApiOperation("Deletes the queue entry with the given id.")
    fun deleteQueueEntry(@PathVariable id: Long, @PathVariable entryId: Long): ResponseEntity<Any> {
        val optionalQueueEntry = queueEntryRepository.findById(entryId)
        val optionalCourse = courseRepository.findCourseById(id)

        if (!optionalQueueEntry.isPresent || !optionalCourse.isPresent) {
            return ResponseEntity.badRequest().body("Queue entry not found")
        }
        val queueEntry = optionalQueueEntry.get()
        val course = optionalCourse.get()
        course.queueEntries.remove(queueEntry)
        courseRepository.save(course)
        queueEntryRepository.delete(queueEntry)

        return ResponseEntity.ok("Queue entry $entryId deleted")
    }
}
