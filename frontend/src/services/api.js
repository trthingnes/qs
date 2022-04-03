import axios from "axios"

const API_URL = "http://localhost:8888"

const TOKEN_URL = API_URL + "/auth/token/"
const USER_URL = API_URL + "/user/"
const STUDENT_COURSES_URL = API_URL + "/courses/student/"
const ASSISTANT_COURSES_URL = API_URL + "/courses/assistant/"
const TEACHER_COURSES_URL = API_URL + "/courses/teacher/"
const COURSES_URL = API_URL + "/courses/"
const QUEUE_URL = API_URL + "/queue"

export async function getToken(username, password) {
    return axios
        .post(TOKEN_URL, {
            username: username.value,
            password: password.value,
        })
        .then((response) => {
            let token = response.data.token

            if (token) {
                return token
            } else {
                throw "Token from endpoint was empty."
            }
        })
        .catch(() => {
            throw "Unable to get token."
        })
}

export async function createCourse(token, info) {
    return axios
        .post(COURSES_URL, info, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then((response) => {
            return response.data
        })
        .catch("Unable to create course")
}

export async function getCourseInfo(token) {
    return axios
        .get(COURSES_URL, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then((response) => {
            return response.data
        })
        .catch(() => {
            throw "Unable to retrieve course data."
        })
}

export async function getUserInfo(token) {
    return axios
        .get(USER_URL, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then((response) => {
            return response.data
        })
        .catch(() => {
            throw "Unable to retrieve user data."
        })
}

export async function updateUserInfo(token, info) {
    return axios
        .put(USER_URL, info, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then(() => {
            return true
        })
        .catch(() => {
            return false
        })
}

export async function getUserStudentCourses(token) {
    return axios
        .get(STUDENT_COURSES_URL, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then((response) => {
            return response.data
        })
        .catch(() => {
            throw "Unable to retrieve course data."
        })
}

export async function getUserAssistantCourses(token) {
    return axios
        .get(ASSISTANT_COURSES_URL, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then((response) => {
            return response.data
        })
        .catch(() => {
            throw "Unable to retrieve course data."
        })
}

export async function getUserTeacherCourses(token) {
    return axios
        .get(TEACHER_COURSES_URL, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then((response) => {
            return response.data
        })
        .catch(() => {
            throw "Unable to retrieve course data."
        })
}

export async function getCourseById(token, id) {
    return axios
        .get(COURSES_URL + `${id}/`, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then((response) => {
            return response.data
        })
        .catch(() => {
            throw "Unable to retrieve user data."
        })
}

export async function getCourseStudentsById(token, id) {
    return axios
        .get(COURSES_URL + `${id}/students/`, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then((response) => {
            console.log(response.data)
            return response.data
        })
        .catch(() => {
            throw "Unable to retrieve course students."
        })
}

export async function getCourseAssistantsById(token, id) {
    return axios
        .get(COURSES_URL + `${id}/assistants/`, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then((response) => {
            return response.data
        })
        .catch(() => {
            throw "Unable to retrieve course assistants."
        })
}

export async function updateCourseInfoById(token, id, info) {
    return axios
        .put(COURSES_URL + `${id}`, info, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then(() => {
            return true
        })
        .catch(() => {
            return false
        })
}

export async function getAssignments(id) {
    return axios
        .get(COURSES_URL + `${id}/assignments`)
        .then((response) => {
            return response.data
        })
        .catch((error) => console.log(error))
}

export async function getCompletedAssignments(token, id) {
    return axios
        .get(COURSES_URL + `${id}/student/assignments/completed/`, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then((response) => {
            return response.data
        })
        .catch((error) => console.log(error))
}

export async function postQueueEntry(id) {
    return axios.post(QUEUE_URL + `${id}/add/`)
}
