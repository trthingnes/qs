<template>
    <HeaderComponent class="mt-10" title="Legg til og fjern deltagere" />
    <v-row>
        <v-col cols="12" md="6" class="mt-2">
            <v-btn
                class="mr-2 mb-2"
                color="success"
                prepend-icon="mdi-plus"
                @click="isAddStudentOverlayOpen = true"
            >
                Legg til student
            </v-btn>
            <v-btn
                class="mb-2"
                color="success"
                prepend-icon="mdi-plus"
                @click="isAddAssistantOverlayOpen = true"
            >
                Legg til assistent
            </v-btn>
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
                            <td>{{ assistant.username }}</td>
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
                            <td>{{ student.username }}</td>
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
    <v-overlay
        v-model="isAddStudentOverlayOpen"
        class="align-center justify-center"
    >
        <AddCourseParticipantComponent
            @added="isAddStudentOverlayOpen = false"
        />
    </v-overlay>
    <v-overlay
        v-model="isAddAssistantOverlayOpen"
        class="align-center justify-center"
    >
        <AddCourseParticipantComponent
            :assistant="true"
            @added="isAddAssistantOverlayOpen = false"
        />
    </v-overlay>
</template>

<script>
import { ref } from "vue"
import { useStore } from "vuex"
import { useRoute } from "vue-router"
import {
    getCourseStudentsById,
    getCourseAssistantsById,
    removeStudentByCourseId,
    removeAssistantsByCourseId,
} from "@/services/api"
import HeaderComponent from "@/components/HeaderComponent.vue"
import AddCourseParticipantComponent from "@/components/course/AddCourseParticipantComponent.vue"

export default {
    components: {
        HeaderComponent,
        AddCourseParticipantComponent,
    },
    setup() {
        const store = useStore()
        const route = useRoute()

        const courseStudents = ref()
        const courseAssistants = ref()
        const isAddStudentOverlayOpen = ref(false)
        const isAddAssistantOverlayOpen = ref(false)

        const onUserDeleteClick = (username, student) => {
            if (student) {
                removeStudentByCourseId(
                    store.getters.token,
                    route.params.id,
                    username
                )
            } else {
                removeAssistantsByCourseId(
                    store.getters.token,
                    route.params.id,
                    username
                )
            }
        }

        getCourseStudentsById(store.getters.token, route.params.id).then(
            (data) => (courseStudents.value = data)
        )
        getCourseAssistantsById(store.getters.token, route.params.id).then(
            (data) => (courseAssistants.value = data)
        )

        return {
            courseStudents,
            courseAssistants,
            isAddStudentOverlayOpen,
            isAddAssistantOverlayOpen,
            onUserDeleteClick,
        }
    },
}
</script>
