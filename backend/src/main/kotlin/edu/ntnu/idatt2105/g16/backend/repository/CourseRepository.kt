package edu.ntnu.idatt2105.g16.backend.repository

import edu.ntnu.idatt2105.g16.backend.entity.Course
import org.springframework.data.repository.PagingAndSortingRepository

interface CourseRepository : PagingAndSortingRepository<Course, Long>