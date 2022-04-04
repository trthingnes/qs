import axios from "axios"

const API_URL = "http://localhost:8888"

const TOKEN_URL = API_URL + "/auth/token/"
const USER_URL = API_URL + "/user/"
const STUDENT_COURSES_URL = API_URL + "/courses/student/"
const ASSISTANT_COURSES_URL = API_URL + "/courses/assistant/"
const TEACHER_COURSES_URL = API_URL + "/courses/teacher/"
const COURSES_URL = API_URL + "/courses/"

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
            if (response.status == 200) {
                return response.data
            }

            return false
        })
        .catch(() => {
            return false
        })
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

export async function addStudentToCourseById(token, id, info) {
    return axios
        .post(COURSES_URL + `${id}/students/`, info, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then((response) => {
            if (response.status == 200) {
                return response.data
            }

            return false
        })
        .catch(() => {
            return false
        })
}

export async function addAssistantToCourseById(token, id, info) {
    return axios
        .post(COURSES_URL + `${id}/assistants/`, info, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then((response) => {
            if (response.status == 200) {
                return response.data
            }

            return false
        })
        .catch(() => {
            return false
        })
}

export async function removeStudentByCourseId(token, id, username) {
    return axios
        .delete(COURSES_URL + `${id}/students/${username}`, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then((response) => {
            if (response.status == 200) {
                return response.data
            }

            return false
        })
        .catch(() => {
            return false
        })
}

export async function removeAssistantsByCourseId(token, id, username) {
    return axios
        .delete(COURSES_URL + `${id}/assistants/${username}`, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then((response) => {
            if (response.status == 200) {
                return response.data
            }

            return false
        })
        .catch(() => {
            return false
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

export async function getQueueEntriesById(token, id) {
    return axios
        .get(COURSES_URL + `${id}/queue/`, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then((response) => {
            return response.data
        })
        .catch(() => {
            throw "Unable to retrieve queue entries."
        })
}

export async function postCompletedAssignment(token, id, ordinal, username) {
    return axios
        .get(
            COURSES_URL + `${id}/assignments/${ordinal}`,
            {
                username: username,
            },
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            }
        )
        .then(() => {
            return true
        })
        .catch(() => {
            throw "Could not find user or assignment"
        })
}

export async function getAssignments(token, id) {
    return axios
        .get(COURSES_URL + `${id}/assignments`, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then((response) => {
            return response.data
        })
        .catch((error) => console.log(error))
}

export async function getCompletedAssignments(token, id) {
    return axios
        .get(COURSES_URL + `${id}/assignments/completed/`, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then((response) => {
            return response.data
        })
        .catch((error) => console.log(error))
}

export async function postQueueEntry(token, id, help, location) {
    return axios
        .post(
            COURSES_URL + `${id}/queue/`,
            {
                help: help,
                location: location,
                hasAssistant: false,
            },
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            }
        )
        .then(() => {
            return true
        })
        .catch(() => {
            return false
        })
}

export async function putQueueEntryHasAssistant(token, id, entryId) {
    return axios
        .put(
            COURSES_URL + `${id}/queue/${entryId}/`,
            {
                hasAssistant: true,
            },
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            }
        )
        .then(() => {
            return true
        })
        .catch(() => {
            return false
        })
}

export async function deleteQueueEntry(token, id, entryId) {
    return axios
        .delete(COURSES_URL + `${id}/queue/${entryId}`, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then(() => {
            return true
        })
        .catch(() => {
            throw "Could not delete queue entry"
        })
}
