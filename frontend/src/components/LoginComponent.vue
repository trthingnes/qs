<template>
    <v-card class="px-5 py-5">
        <v-card-title>Velkommen</v-card-title>
        <v-card-subtitle> Logg inn for å fortsette til Qs! </v-card-subtitle>
        <v-card-text>
            <form @submit.prevent="onLoginClick">
                <v-text-field
                    v-model="username"
                    label="Brukernavn"
                ></v-text-field>
                <v-text-field
                    v-model="password"
                    type="password"
                    label="Passord"
                ></v-text-field>
                <v-btn type="submit" color="success">Logg inn</v-btn>
            </form>
        </v-card-text>
    </v-card>
</template>
<script>
import { ref } from "vue"
import { useStore } from "vuex"
import { getToken, getUserInfo } from "@/services/api"

export default {
    emits: ["login"],
    setup(props, ctx) {
        const store = useStore()
        const username = ref("")
        const password = ref("")

        const onLoginClick = async () => {
            try {
                let token = await getToken(username, password)
                store.dispatch("setToken", { token: token })
                let userinfo = await getUserInfo(token)
                store.dispatch("setUserInfo", userinfo)
                ctx.emit("login")
            } catch {
                console.error("Unable to finish log in.")
            }
        }

        return { onLoginClick, username, password }
    },
}
</script>
