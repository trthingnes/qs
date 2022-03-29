package edu.ntnu.idatt2105.g16.backend.repository

import edu.ntnu.idatt2105.g16.backend.entity.Assignment
import org.springframework.data.repository.PagingAndSortingRepository

interface AssignmentRepository : PagingAndSortingRepository<Assignment, Long>