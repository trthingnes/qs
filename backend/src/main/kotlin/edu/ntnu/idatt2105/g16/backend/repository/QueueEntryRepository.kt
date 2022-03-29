package edu.ntnu.idatt2105.g16.backend.repository

import edu.ntnu.idatt2105.g16.backend.entity.QueueEntry
import org.springframework.data.repository.PagingAndSortingRepository

interface QueueEntryRepository : PagingAndSortingRepository<QueueEntry, Long>
