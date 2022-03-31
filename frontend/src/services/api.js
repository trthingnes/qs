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
    return ["1", "2", "3", "4", "5"]
}

export function getCompletedAssignments() {
    return ["1"]
}

export function getStudents() {
    return [{ name: "Jonathan" }, { name: "Tobias" }, { name: "Tor" }]
}

export function getQueue() {
    return [
        { name: "Jonathan", type: "Help", assignment: "1" },
        { name: "Tobias", type: "Help", assignment: "69" },
        { name: "Tor", type: "Help", assignment: "38" },
    ]
}
