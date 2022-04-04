<template>
    <v-card class="px-5 py-5">
        <v-card-title>Velkommen</v-card-title>
        <v-card-subtitle>Logg inn for å fortsette til Qs!</v-card-subtitle>
        <v-card-text>
            <v-alert type="error" v-if="loginFailed" class="mt-3 mb-6">
                Feil brukernavn eller passord.
            </v-alert>
            <form @submit.prevent="onLoginClick">
                <v-text-field
                    v-model="username"
                    label="Brukernavn"
                    aria-required="Vennligst skriv et brukernavn"
                ></v-text-field>
                <v-text-field
                    v-model="password"
                    type="password"
                    label="Passord"
                    aria-required="Vennligst skriv et passord"
                ></v-text-field>
                <v-btn append-icon="mdi-login" type="submit" color="success">
                    Logg inn
                </v-btn>
            </form>
        </v-card-text>
    </v-card>
</template>
<script>
import { ref } from "vue"
import { useStore } from "vuex"
import { useCookies } from "vue3-cookies"
import { getToken, getUserInfo } from "@/services/api"

export default {
    emits: ["login"],
    setup(props, ctx) {
        const store = useStore()
        const { cookies } = useCookies()
        const username = ref("")
        const password = ref("")
        const loginFailed = ref(false)

        const onLoginClick = async () => {
            try {
                let token = await getToken(username, password)
                cookies.set("token", token, "1d")

                let userinfo = await getUserInfo(token)
                store.dispatch("setUserInfo", userinfo)

                ctx.emit("login")
            } catch (message) {
                console.error(`Unable to finish log in: '${message}'`)
                loginFailed.value = true
            }
        }

        return { username, password, loginFailed, onLoginClick }
    },
}
</script>
