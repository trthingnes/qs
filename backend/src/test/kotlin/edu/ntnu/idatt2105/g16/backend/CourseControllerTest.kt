package edu.ntnu.idatt2105.g16.backend

/*
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
*/
class CourseControllerTest {
    /*
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var courseService: CourseService

    private val userDto = UserDTO("user", "p", "f", "l", "e@m.l", Role.STUDENT)
    private val courseDto = CourseDTO(1, "c", "n", "www.url.com", "d", Semester.SUMMER, 2000, 0)

    @BeforeAll
    internal fun setUp() {
        val user = userService.createUser(userDto)
        courseService.addCourse(user.username, courseDto)
        println(courseService.getCourseById(1).get())
    }

    @Test
    internal fun testGetCourseById() {
        mockMvc.perform(get("/course/1"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.code").value(courseDto.code))
    }
    */
}