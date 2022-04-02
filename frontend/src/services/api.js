import axios from "axios"

const API_URL = "http://localhost:8888"

const TOKEN_URL = API_URL + "/auth/token/"
const USER_URL = API_URL + "/user/"
const STUDENT_COURSES_URL = API_URL + "/user/courses/student/"
const ASSISTANT_COURSES_URL = API_URL + "/user/courses/assistant/"
const TEACHER_COURSES_URL = API_URL + "/user/courses/teacher/"
const COURSES_URL = API_URL + "/courses/"
const QUEUE_URL = API_URL + "/queue"

const CLIENT = axios.create({
    baseURL: API_URL,
    withCredentials: false,
    headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
    },
})

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

export function receiveAll() {
    return CLIENT.get("")
        .then((response) => {
            return response.data
        })
        .catch((error) => console.log(error))
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
        .get(COURSES_URL + `${id}/student/assignment/completed/`, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then((response) => {
            return response.data
        })
        .catch((error) => console.log(error))
}

export async function postQueueEntry(id, assignments) {
    return axios.post(QUEUE_URL + `${id}/add/`)
}
