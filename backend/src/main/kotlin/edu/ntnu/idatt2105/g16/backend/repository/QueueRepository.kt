package edu.ntnu.idatt2105.g16.backend.repository

import edu.ntnu.idatt2105.g16.backend.entity.Queue
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface QueueRepository : CrudRepository<Queue, Long>
