package edu.ntnu.idatt2105.g16.backend.repository

import edu.ntnu.idatt2105.g16.backend.entity.CourseDescription
import org.springframework.data.repository.PagingAndSortingRepository

interface CourseDescriptionRepository : PagingAndSortingRepository<CourseDescription, Long>