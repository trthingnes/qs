import axios from "axios"

const API_URL = "http://localhost:8888"

const TOKEN_ENDPOINT = "/auth/token/"
const USER_ENDPOINT = "/user/"

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
        .post(API_URL + TOKEN_ENDPOINT, {
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
        .get(API_URL + USER_ENDPOINT, {
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

export function receiveAll() {
    return CLIENT.get("")
        .then((response) => {
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
