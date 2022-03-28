package edu.ntnu.idatt2105.g16.backend.repository

import edu.ntnu.idatt2105.g16.backend.entity.Ad
import org.springframework.data.repository.PagingAndSortingRepository

interface AdRepository : PagingAndSortingRepository<Ad, Long>