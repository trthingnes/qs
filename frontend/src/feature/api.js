import axios from "axios"

export function receiveAll() {
    return axios
        .get("")
        .then((response) => {
            return response.data
        })
        .catch((error) => console.log(error))
}
