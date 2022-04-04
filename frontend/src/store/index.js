import { createStore } from "vuex"

export default createStore({
    state: {
        username: "",
        firstname: "",
        lastname: "",
        email: "",
        role: "",
        token: "",
    },
    getters: {
        token(state) {
            return state.token
        },
        userInfo(state) {
            return {
                username: state.username,
                firstname: state.firstname,
                lastname: state.lastname,
                email: state.email,
                role: state.role,
            }
        },
        courseInfo(state) {
            return {
                code: state.code,
                name: state.name,
                numAssignments: state.numAssignments,
            }
        },
    },
    mutations: {
        SET_TOKEN(state, token) {
            state.token = token
        },
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
        SET_ROLE(state, role) {
            state.role = role
        },
    },
    actions: {
        setToken(context, token) {
            context.commit("SET_TOKEN", token)
        },
        setUserInfo(context, { username, firstName, lastName, email, role }) {
            context.commit("SET_USERNAME", username)
            context.commit("SET_FIRSTNAME", firstName)
            context.commit("SET_LASTNAME", lastName)
            context.commit("SET_EMAIL", email)
            context.commit("SET_ROLE", role)
        },
        logout(context) {
            context.commit("SET_USERNAME", "")
            context.commit("SET_FIRSTNAME", "")
            context.commit("SET_LASTNAME", "")
            context.commit("SET_EMAIL", "")
            context.commit("SET_ROLE", "")
        },
    },
    modules: {},
})
