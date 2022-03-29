package edu.ntnu.idatt2105.g16.backend.repository

import edu.ntnu.idatt2105.g16.backend.entity.User
import org.springframework.data.repository.PagingAndSortingRepository

interface UserRepository: PagingAndSortingRepository<User, Long>