import { createStore } from "vuex"

export default createStore({
    state: {
        username: "",
        firstname: "",
        lastname: "",
        email: "",
        role: "",
    },
    getters: {
        userInfo(state) {
            return {
                username: state.username,
                firstname: state.firstname,
                lastname: state.lastname,
                email: state.email,
                role: state.role,
            }
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
        SET_ROLE(state, role) {
            state.role = role
        },
    },
    actions: {
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
