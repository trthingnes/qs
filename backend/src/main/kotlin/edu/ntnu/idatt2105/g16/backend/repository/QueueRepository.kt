package edu.ntnu.idatt2105.g16.backend.repository

import edu.ntnu.idatt2105.g16.backend.entity.Queue
import org.springframework.data.repository.PagingAndSortingRepository

interface QueueRepository : PagingAndSortingRepository<Queue, Long>
