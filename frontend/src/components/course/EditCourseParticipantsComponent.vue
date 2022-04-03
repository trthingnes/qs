<template>
    <HeaderComponent class="mt-10" title="Legg til og fjern deltagere" />
    <v-row>
        <v-col cols="12" md="6" class="mt-2">
            <v-btn class="mr-2 mb-2" color="success" prepend-icon="mdi-plus"
                >Legg til student</v-btn
            >
            <v-btn class="mb-2" color="success" prepend-icon="mdi-plus"
                >Legg til assistent</v-btn
            >
        </v-col>
        <v-col cols="12" md="6">
            <v-file-input
                chips
                label="Legg til studenter fra CSV-fil"
            ></v-file-input>
        </v-col>

        <v-col cols="12">
            <v-table>
                <template v-slot:default>
                    <thead>
                        <tr>
                            <th>Navn</th>
                            <th>Rolle</th>
                            <th>Slett</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr
                            v-for="assistant in courseAssistants"
                            :key="assistant.username"
                        >
                            <td>{{ assistant.name }}</td>
                            <td>Assistent</td>
                            <td>
                                <v-btn
                                    variant="text"
                                    color="error"
                                    icon="mdi-delete"
                                    @click="
                                        onUserDeleteClick(
                                            assistant.username,
                                            false
                                        )
                                    "
                                ></v-btn>
                            </td>
                        </tr>
                        <tr
                            v-for="student in courseStudents"
                            :key="student.username"
                        >
                            <td>{{ student.name }}</td>
                            <td>Student</td>
                            <td>
                                <v-btn
                                    variant="text"
                                    color="error"
                                    icon="mdi-delete"
                                    @click="
                                        onUserDeleteClick(
                                            student.username,
                                            true
                                        )
                                    "
                                ></v-btn>
                            </td>
                        </tr>
                    </tbody>
                </template>
            </v-table>
        </v-col>
    </v-row>
</template>

<script>
import { ref } from "vue"
import { useRoute } from "vue-router"
import { useCookies } from "vue3-cookies"
import HeaderComponent from "@/components/HeaderComponent.vue"
import { getCourseStudentsById, getCourseAssistantsById } from "@/services/api"

export default {
    components: {
        HeaderComponent,
    },
    setup() {
        const route = useRoute()
        const { cookies } = useCookies()

        const courseStudents = ref()
        const courseAssistants = ref()

        const onUserDeleteClick = (username, student) => {
            console.log(
                `Deleting ${
                    student ? "student" : "assistant"
                } with username ${username}`
            )
        }

        getCourseStudentsById(cookies.get("token"), route.params.id).then(
            (data) => (courseStudents.value = data)
        )
        getCourseAssistantsById(cookies.get("token"), route.params.id).then(
            (data) => (courseAssistants.value = data)
        )

        return {
            courseStudents,
            courseAssistants,
            onUserDeleteClick,
        }
    },
}
</script>
