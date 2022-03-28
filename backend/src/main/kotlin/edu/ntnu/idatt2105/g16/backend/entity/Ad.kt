package edu.ntnu.idatt2105.g16.backend.entity

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
class Ad {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @NotBlank(message = "Owner cannot be empty.")
    var owner = ""

    @NotBlank(message = "Title cannot be empty.")
    @Size(min = 5, max = 140, message = "Titles must have between {min} and {max} characters.")
    var title = ""

    @NotBlank(message = "Description cannot be empty.")
    var description = ""

    @Min(message = "Price cannot be negative.", value = 0)
    var price: BigDecimal = BigDecimal.ONE
}