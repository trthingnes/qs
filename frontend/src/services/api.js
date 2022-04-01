import axios from "axios"

const API_URL = "http://localhost:8888"

const TOKEN_URL = "/auth/token/"
const USER_URL = "/user/"
const STUDENT_COURSES_URL = "/user/courses/student"
const ASSISTANT_COURSES_URL = "/user/courses/assistant"

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
        .post(API_URL + TOKEN_URL, {
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
        .get(API_URL + USER_URL, {
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

export async function getUserStudentCourses(token) {
    return axios
        .get(API_URL + STUDENT_COURSES_URL, {
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
        .get(API_URL + ASSISTANT_COURSES_URL, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then((response) => {
            console.log(response.data)
            return response.data
        })
        .catch(() => {
            throw "Unable to retrieve course data."
        })
}

export function receiveAll() {
    return CLIENT.get("")
        .then((response) => {
            console.log(response.data)
            return response.data
        })
        .catch((error) => console.log(error))
}

export function getAssignments() {
    return ["1", "2", "3", "4"]
}

export function getCompletedAssignments() {
    return ["1"]
}
