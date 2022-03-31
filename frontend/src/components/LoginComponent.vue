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
    setup() {
        const store = useStore()
        const username = ref("")
        const password = ref("")

        const onLoginClick = async () => {
            console.log(
                `Username: ${username.value}, Password: ${password.value}`
            )
            let token = await getToken(username, password)
            let userinfo = await getUserInfo(token)
            store.commit("setUserInfo", userinfo)

            console.log(token)
        }

        return { onLoginClick, username, password }
    },
}
</script>
