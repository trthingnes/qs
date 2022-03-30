import { createStore } from "vuex"

export default createStore({
    state: {
        firstname: "",
        lastname: "",
        email: "",
        password: "",
    },
    getters: {},
    mutations: {
        SET_FIRSTNAME(state, firstname) {
            state.firstname = firstname
        },
        SET_LASTNAME(state, lastname) {
            state.lastname = lastname
        },
        SET_EMAIL(state, email) {
            state.email = email
        },
        SET_PASSWORD(state, password) {
            state.password = password
        },
    },
    actions: {
        saveUserInfo(context, { firstname, lastname, email, password }) {
            this.commit("SET_FIRSTNAME", firstname)
            this.commit("SET_LASTNAME", lastname)
            this.commit("SET_EMAIL", email)
            this.commit("SET_PASSWORD", password)
        },
    },
    modules: {},
})
