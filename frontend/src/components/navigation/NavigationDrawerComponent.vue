<template>
    <v-navigation-drawer width="350" app permanent>
        <v-list>
            <v-list-item>
                <router-link
                    class="text-decoration-none"
                    style="color: black"
                    :to="{ name: 'home' }"
                >
                    <h1>Qs</h1>
                </router-link>
            </v-list-item>

            <!-- Student courses section -->
            <div v-if="studentCourses.length">
                <CourseSectionComponent
                    title="Dine fag"
                    slug="student"
                    icon="mdi-school"
                    :courses="studentCourses"
                    @click="onCourseClick"
                />
            </div>

            <!-- Assistant courses section -->
            <div v-if="assistantCourses.length">
                <CourseSectionComponent
                    title="Læringsassistent"
                    slug="courses/"
                    icon="mdi-book"
                    :courses="assistantCourses"
                    @click="onCourseClick"
                />
            </div>

            <!-- Teacher admin section -->
            <div v-if="isTeacher">
                <CourseSectionComponent
                    title="Faglærer"
                    slug="courses/edit"
                    icon="mdi-bookshelf"
                    :courses="teacherCourses"
                    @click="onCourseClick"
                />
                <v-list-item>
                    <v-btn
                        color="primary"
                        prepend-icon="mdi-plus"
                        @click.stop="onAddCourseClick()"
                    >
                        Legg til fag
                    </v-btn>
                </v-list-item>
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
                            @click="onSettingsClick()"
                        >
                            Innstillinger
                        </v-btn>
                        <v-btn
                            variant="contained-text"
                            append-icon="mdi-logout"
                            color="error"
                            class="ml-2"
                            @click="onLogoutClick()"
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
                            color="success"
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
import CourseSectionComponent from "@/components/navigation/CourseSectionComponent.vue"

import { ref } from "vue"
import { useStore } from "vuex"
import { useRouter } from "vue-router"
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
        CourseSectionComponent,
    },
    setup() {
        const router = useRouter()
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

        const onCourseClick = (path) => {
            router.push({ path: path })
        }

        const onAddCourseClick = () => {
            router.push({ name: "add-course" })
        }

        const onSettingsClick = () => {
            router.push({ name: "settings" })
        }

        const onLogoutClick = () => {
            store.dispatch("logout")
            cookies.remove("token")
            router.push({ name: "home" })
            updateUserInfoFromStore()
            studentCourses.value = []
            assistantCourses.value = []
            isAuthenticated.value = false
        }

        const onUserLogin = () => {
            updateUserInfoFromStore()
            updateCourses()
            isAuthenticated.value = true
            isLoginOverlayOpen.value = false
        }

        if (cookies.isKey("token")) {
            getUserInfo(cookies.get("token")).then((userinfo) => {
                store.dispatch("setUserInfo", userinfo)
                updateUserInfoFromStore()
                updateCourses()
            })
        } else if (router.currentRoute.value.name != "home") {
            router.push({ name: "home" })
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
            onAddCourseClick,
            onLogoutClick,
            onSettingsClick,
            onUserLogin,
        }
    },
}
</script>
