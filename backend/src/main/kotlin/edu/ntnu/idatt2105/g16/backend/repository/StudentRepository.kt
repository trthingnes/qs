package edu.ntnu.idatt2105.g16.backend.repository

import edu.ntnu.idatt2105.g16.backend.entity.Student
import org.springframework.data.repository.PagingAndSortingRepository

interface StudentRepository : PagingAndSortingRepository<Student, Long>
