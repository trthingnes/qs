<template>
    <v-navigation-drawer width="350" app permanent>
        <v-list>
            <v-list-item>
                <h1>Qs</h1>
            </v-list-item>

            <v-divider class="mt-2 mb-2"></v-divider>

            <div v-if="token">
                <v-list-subheader color="black">
                    <v-icon icon="mdi-school" class="mr-3"></v-icon>
                    Dine fag
                </v-list-subheader>

                <v-list-item
                    v-for="[code, name] in studentCourses"
                    :title="name"
                    :value="code"
                    :key="code"
                    @click.stop="onCourseClick(code)"
                ></v-list-item>
            </div>

            <div v-if="token && assistantCourses.length">
                <v-divider class="mt-2 mb-2"></v-divider>

                <v-list-subheader color="black">
                    <v-icon icon="mdi-school" class="mr-3"></v-icon>
                    Læringsassistent
                </v-list-subheader>
                <v-list-item
                    v-for="[code, name] in assistantCourses"
                    :title="name"
                    :value="code"
                    :key="code"
                    @click.stop="onCourseClick(code)"
                ></v-list-item>
            </div>
        </v-list>

        <template v-slot:append>
            <v-divider></v-divider>
            <div v-if="token">
                <v-card class="mt-3 pb-2" density="compact">
                    <v-card-title>
                        {{ firstname }} {{ lastname }}
                    </v-card-title>
                    <v-card-subtitle>
                        {{ email }} ({{ username }})
                    </v-card-subtitle>
                    <v-card-actions>
                        <v-btn
                            variant="contained-text"
                            append-icon="mdi-cog"
                            class="ml-2"
                        >
                            Innstillinger
                        </v-btn>
                        <v-btn
                            variant="contained-text"
                            append-icon="mdi-logout"
                            class="ml-2"
                        >
                            Logg ut
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </div>
            <div v-else>
                <v-card class="mt-3 pb-2" density="compact">
                    <v-card-title>Velkommen til Qs</v-card-title>
                    <v-card-subtitle>
                        Vennligst logg inn for å komme i gang.
                    </v-card-subtitle>
                    <v-card-actions>
                        <v-btn
                            variant="contained-text"
                            append-icon="mdi-logout"
                            class="ml-2"
                            @click.stop="loginOverlayOpen = true"
                        >
                            Logg inn
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </div>
        </template>
    </v-navigation-drawer>
    <v-overlay v-model="loginOverlayOpen" class="align-center justify-center">
        <LoginComponent @login="onUserLogin()" />
    </v-overlay>
</template>
<script>
import LoginComponent from "@/components/LoginComponent.vue"

import { ref } from "vue"
import { useRouter } from "vue-router"
import { useStore } from "vuex"

export default {
    components: {
        LoginComponent,
    },
    setup() {
        const router = useRouter()
        const store = useStore()

        const loginOverlayOpen = ref(false)
        const token = ref("")
        const username = ref("")
        const firstname = ref("")
        const lastname = ref("")
        const email = ref("")

        const studentCourses = [
            ["IDATT2101", "Algoritmer og datastrukturer"],
            ["IDATT2105", "Full-stack applikasjonsutvikling"],
        ]

        const assistantCourses = [
            ["IDATT1001", "Programmering 1"],
            ["IDATT2001", "Programmering 2"],
        ]

        const onCourseClick = (code) => {
            router.push(`/courses/${code}`)
        }

        const onUserLogin = () => {
            loginOverlayOpen.value = false
            token.value = store.getters.token

            let userinfo = store.getters.userInfo
            username.value = userinfo.username
            firstname.value = userinfo.firstname
            lastname.value = userinfo.lastname
            email.value = userinfo.email
        }

        return {
            token,
            username,
            firstname,
            lastname,
            email,
            studentCourses,
            assistantCourses,
            loginOverlayOpen,
            onCourseClick,
            onUserLogin,
        }
    },
}
</script>
