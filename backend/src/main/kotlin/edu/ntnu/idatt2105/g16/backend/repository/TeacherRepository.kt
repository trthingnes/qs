package edu.ntnu.idatt2105.g16.backend.repository

import edu.ntnu.idatt2105.g16.backend.entity.Teacher
import org.springframework.data.repository.PagingAndSortingRepository

interface TeacherRepository : PagingAndSortingRepository<Teacher, Long>