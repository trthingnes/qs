import axios from "axios"

const CLIENT = axios.create({
    baseURL: "http://localhost:8080",
    withCredentials: false,
    headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
    },
})

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
