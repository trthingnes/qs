import { createStore } from "vuex"

export default createStore({
    state: {
        username: "",
        firstname: "",
        lastname: "",
        email: "",
        token: "",
    },
    getters: {
        userInfo(state) {
            return {
                username: state.username,
                firstname: state.firstname,
                lastname: state.lastname,
                email: state.email,
            }
        },
        token(state) {
            return state.token
        },
    },
    mutations: {
        SET_USERNAME(state, username) {
            state.username = username
        },
        SET_FIRSTNAME(state, firstname) {
            state.firstname = firstname
        },
        SET_LASTNAME(state, lastname) {
            state.lastname = lastname
        },
        SET_EMAIL(state, email) {
            state.email = email
        },
        SET_TOKEN(state, token) {
            state.token = token
        },
    },
    actions: {
        setUserInfo(context, { username, firstName, lastName, email }) {
            context.commit("SET_USERNAME", username)
            context.commit("SET_FIRSTNAME", firstName)
            context.commit("SET_LASTNAME", lastName)
            context.commit("SET_EMAIL", email)
        },
        setToken(context, token) {
            context.commit("SET_TOKEN", token)
        },
    },
    modules: {},
})
