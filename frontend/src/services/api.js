import axios from "axios"
import { useStore } from "vuex"

const API_URL = "http://localhost:8888"

const CLIENT = axios.create({
    baseURL: API_URL,
    withCredentials: false,
    headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
    },
})

export function getToken(username, password) {
    return axios
        .post(API_URL + "/auth/token", {
            username: username,
            password: password,
        })
        .then((response) => {
            let token = response.data.token

            if (token) {
                console.info("Successfully retrieved token.")
            } else {
                console.warn("Got no token from token endpoint.")
                return null
            }
        })
        .catch((error) => {
            console.error(error)
            return null
        })
}

export function getUserInfo() {
    let token = useStore.getters.token

    if (!token) {
        console.error("Cannot get user info without token.")
        return null
    }

    return axios
        .get(
            API_URL + "/user",
            {},
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            }
        )
        .then((response) => {
            console.log(response)
        })
        .catch((error) => {
            console.error(error)
            return null
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
