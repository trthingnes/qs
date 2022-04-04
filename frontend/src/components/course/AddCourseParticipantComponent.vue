<template>
    <v-card class="px-5 py-5">
        <v-card-title>
            Legg til en {{ assistant ? "assistent" : "student" }}
        </v-card-title>
        <v-card-subtitle>
            Fyll inn informasjon om brukeren under.
        </v-card-subtitle>
        <v-card-text>
            <form @submit.prevent="onAddUserClick">
                <v-container>
                    <v-row>
                        <v-col cols="12" md="4">
                            <v-text-field
                                label="Fornavn"
                                v-model="firstname"
                                :counter="50"
                                required
                            ></v-text-field>
                        </v-col>

                        <v-col cols="12" md="4">
                            <v-text-field
                                label="Etternavn"
                                v-model="lastname"
                                :counter="50"
                                required
                            ></v-text-field>
                        </v-col>

                        <v-col cols="12" md="4">
                            <v-text-field
                                label="Epost-adresse"
                                v-model="email"
                                required
                            ></v-text-field>
                        </v-col>

                        <v-col cols="12" md="6">
                            <v-text-field
                                v-model="username"
                                label="Brukernavn"
                            ></v-text-field>
                        </v-col>

                        <v-col cols="12" md="6">
                            <v-text-field
                                v-model="password"
                                type="password"
                                label="Passord"
                            ></v-text-field>
                        </v-col>

                        <v-col cols="12" md="12">
                            <v-btn
                                prepend-icon="mdi-plus"
                                color="success"
                                type="submit"
                            >
                                Legg til
                                {{ assistant ? "assistent" : "student" }}
                            </v-btn>
                        </v-col>
                    </v-row>
                </v-container>
            </form>
        </v-card-text>
    </v-card>
</template>
<script>
import { ref } from "vue"
import { useStore } from "vuex"
import { useRoute } from "vue-router"
import {
    addStudentToCourseById,
    addAssistantToCourseById,
} from "@/services/api"

export default {
    props: ["assistant"],
    setup(props, ctx) {
        const store = useStore()
        const route = useRoute()

        const firstname = ref("")
        const lastname = ref("")
        const email = ref("")
        const username = ref("")
        const password = ref("")

        const onAddUserClick = () => {
            let data = {
                firstName: firstname.value,
                lastName: lastname.value,
                email: email.value,
                username: username.value,
                password: password.value,
            }

            if (props.assistant) {
                addAssistantToCourseById(
                    store.getters.token,
                    route.params.id,
                    data
                )
            } else {
                addStudentToCourseById(
                    store.getters.token,
                    route.params.id,
                    data
                )
            }

            ctx.emit("added")
        }

        return {
            firstname,
            lastname,
            email,
            username,
            password,
            onAddUserClick,
        }
    },
}
</script>
