<template>
    <v-form @submit.prevent="onFormSubmit">
        <v-container>
            <HeaderComponent title="Innstillinger" />
            <v-row>
                <v-col cols="12" md="4">
                    <v-text-field
                        label="Fornavn"
                        v-model="firstname"
                        :rules="nameRules"
                        :counter="50"
                        data-testid="firstname"
                        required
                    ></v-text-field>
                </v-col>

                <v-col cols="12" md="4">
                    <v-text-field
                        label="Etternavn"
                        v-model="lastname"
                        :rules="nameRules"
                        :counter="50"
                        data-testid="lastname"
                        required
                    ></v-text-field>
                </v-col>

                <v-col cols="12" md="4">
                    <v-text-field
                        label="Epost-adresse"
                        v-model="email"
                        :rules="emailRules"
                        data-testid="email"
                        required
                    ></v-text-field>
                </v-col>

                <v-col cols="12" md="6">
                    <v-text-field
                        v-model="newPass"
                        type="password"
                        label="Nytt passord"
                    ></v-text-field>
                </v-col>

                <v-col cols="12" md="6">
                    <v-text-field
                        v-model="repPass"
                        type="password"
                        label="Gjenta nytt passord"
                    ></v-text-field>
                </v-col>

                <v-col cols="12" md="12">
                    <v-btn
                        prepend-icon="mdi-check"
                        color="success"
                        type="submit"
                    >
                        Oppdater profil
                    </v-btn>
                    <v-alert
                        v-if="updatedSuccessfully"
                        type="success"
                        class="my-5"
                        >Profilen ble oppdatert.</v-alert
                    >
                </v-col>
            </v-row>
        </v-container>
    </v-form>
</template>

<script>
import { ref } from "vue"
import { useStore } from "vuex"
import { useCookies } from "vue3-cookies"
import { getUserInfo, updateUserInfo } from "@/services/api"
import HeaderComponent from "@/components/HeaderComponent.vue"

export default {
    components: {
        HeaderComponent,
    },
    setup() {
        const store = useStore()
        const { cookies } = useCookies()

        const firstname = ref("")
        const lastname = ref("")
        const email = ref("")
        const newPass = ref("")
        const repPass = ref("")
        const updatedSuccessfully = ref(false)

        const nameRules = [
            (v) => !!v || "Navn er påkrevd.",
            (v) => (v && v.length <= 50) || "Navn må være under 50 tegn.",
        ]

        const emailRules = [
            (v) => !!v || "Epost-adresse er påkrevd.",
            (v) => /.+@.+\..+/.test(v) || "Epost-adressen må være gyldig.",
        ]

        const updateUserInfoFromStore = () => {
            let userinfo = store.getters.userInfo
            firstname.value = userinfo.firstname
            lastname.value = userinfo.lastname
            email.value = userinfo.email
        }

        const onFormSubmit = () => {
            let userinfo = store.getters.userInfo

            let infoToUpdate = {}
            if (firstname.value !== userinfo.firstname) {
                infoToUpdate["firstName"] = firstname.value
            }
            if (lastname.value !== userinfo.lastname) {
                infoToUpdate["lastName"] = lastname.value
            }
            if (email.value !== userinfo.email) {
                infoToUpdate["email"] = email.value
            }
            if (newPass.value && newPass.value === repPass.value) {
                infoToUpdate["password"] = newPass.value
            }

            updatedSuccessfully.value = updateUserInfo(
                cookies.get("token"),
                infoToUpdate
            ).then(async () => {
                let userinfo = await getUserInfo(cookies.get("token"))
                store.dispatch("setUserInfo", userinfo)
            })
        }

        updateUserInfoFromStore()

        return {
            firstname,
            lastname,
            email,
            newPass,
            repPass,
            nameRules,
            emailRules,
            updatedSuccessfully,
            onFormSubmit,
        }
    },
}
</script>
