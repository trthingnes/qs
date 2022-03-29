package edu.ntnu.idatt2105.g16.backend.entity

import org.hibernate.validator.constraints.URL
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
class CourseDescription {
    @Id
    @GeneratedValue
    var id: Long = 0

    @NotBlank
    @Size(min = 7, max = 9)
    var code: String = ""

    @NotBlank
    @Size(min = 10, max = 200)
    var name: String = ""

    @URL
    @NotNull
    var url: String = ""

    var description: String = ""
}
