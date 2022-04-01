<template>
    <v-navigation-drawer width="350" app permanent>
        <v-list>
            <v-list-item>
                <h1>Qs</h1>
            </v-list-item>

            <!-- Student courses section -->
            <div v-if="studentCourses.length">
                <v-divider class="mt-2 mb-2"></v-divider>
                <v-list-subheader color="black">
                    <v-icon icon="mdi-school" class="mr-3"></v-icon>
                    Dine fag
                </v-list-subheader>

                <v-list-item
                    v-for="course in studentCourses"
                    :title="course.name"
                    :value="`/student/${course.id}`"
                    :key="course.code"
                    @click.stop="onCourseClick(`/student/${course.id}`)"
                ></v-list-item>
            </div>

            <!-- Assistant courses section -->
            <div v-if="assistantCourses.length">
                <v-divider class="mt-2 mb-2"></v-divider>
                <v-list-subheader color="black">
                    <v-icon icon="mdi-school" class="mr-3"></v-icon>
                    Læringsassistent
                </v-list-subheader>
                <v-list-item
                    v-for="course in assistantCourses"
                    :title="course.name"
                    :value="`/assistant/${course.id}`"
                    :key="course.code"
                    @click.stop="onCourseClick(`/assistant/${course.id}`)"
                ></v-list-item>
            </div>

            <!-- Teacher admin section -->
            <div v-if="isTeacher">
                <v-divider class="mt-2 mb-2"></v-divider>
                <v-list-subheader color="black">
                    <v-icon icon="mdi-bookshelf" class="mr-3"></v-icon>
                    Faglærer
                </v-list-subheader>
                <v-list-item
                    v-for="course in teacherCourses"
                    :title="course.name"
                    :value="course.id"
                    :key="course.id"
                    @click.stop="onCourseClick(`/teacher/${course.id}`)"
                ></v-list-item>
            </div>
        </v-list>

        <!-- Profile card section -->
        <template v-slot:append>
            <v-divider></v-divider>
            <div v-if="isAuthenticated">
                <v-card class="mt-3 pb-2" density="compact">
                    <v-card-title>
                        {{ firstname }} {{ lastname }}
                    </v-card-title>
                    <v-card-subtitle>
                        {{ email }} ({{ role }})
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
                            @click.stop="onUserLogout()"
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
                            append-icon="mdi-login"
                            class="ml-2"
                            @click.stop="isLoginOverlayOpen = true"
                        >
                            Logg inn
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </div>
        </template>
    </v-navigation-drawer>
    <v-overlay v-model="isLoginOverlayOpen" class="align-center justify-center">
        <LoginComponent @login="onUserLogin()" />
    </v-overlay>
</template>
<script>
import LoginComponent from "@/components/navigation/LoginComponent.vue"

import { ref } from "vue"
import { useStore } from "vuex"
import { useCookies } from "vue3-cookies"
import {
    getUserInfo,
    getUserStudentCourses,
    getUserAssistantCourses,
    getUserTeacherCourses,
} from "@/services/api"

export default {
    components: {
        LoginComponent,
    },
    setup() {
        const store = useStore()
        const { cookies } = useCookies()

        const isAuthenticated = ref(Boolean(cookies.get("token")))
        const isTeacher = ref(false)
        const isLoginOverlayOpen = ref(false)

        const username = ref("")
        const firstname = ref("")
        const lastname = ref("")
        const email = ref("")
        const role = ref("")
        const studentCourses = ref([])
        const assistantCourses = ref([])
        const teacherCourses = ref([])

        const updateUserInfoFromStore = () => {
            let userinfo = store.getters.userInfo
            username.value = userinfo.username
            firstname.value = userinfo.firstname
            lastname.value = userinfo.lastname
            email.value = userinfo.email
            role.value = userinfo.role
            isTeacher.value = userinfo.role === "TEACHER"
        }

        const updateCourses = () => {
            getUserStudentCourses(cookies.get("token")).then((courses) => {
                studentCourses.value = courses
            })

            getUserAssistantCourses(cookies.get("token")).then((courses) => {
                assistantCourses.value = courses
            })

            getUserTeacherCourses(cookies.get("token")).then((courses) => {
                teacherCourses.value = courses
            })
        }

        const onCourseClick = (code) => {
            console.log("Course clicked", code)
        }

        const onUserLogin = () => {
            updateUserInfoFromStore()
            updateCourses()
            isAuthenticated.value = true
            isLoginOverlayOpen.value = false
        }

        const onUserLogout = () => {
            store.dispatch("logout")
            cookies.remove("token")
            updateUserInfoFromStore()
            studentCourses.value = []
            assistantCourses.value = []
            isAuthenticated.value = false
        }

        if (cookies.isKey("token")) {
            getUserInfo(cookies.get("token")).then((userinfo) => {
                store.dispatch("setUserInfo", userinfo)
                updateUserInfoFromStore()
                updateCourses()
            })
        }

        return {
            username,
            firstname,
            lastname,
            email,
            role,
            studentCourses,
            assistantCourses,
            teacherCourses,
            isAuthenticated,
            isTeacher,
            isLoginOverlayOpen,
            onCourseClick,
            onUserLogin,
            onUserLogout,
        }
    },
}
</script>
