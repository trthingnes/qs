<template>
    <v-card class="mx-auto mr-4">
        <v-table fixed-header>
            <thead>
                <tr>
                    <th>Total number of students: {{ students.length }}</th>
                </tr>
            </thead>
            <tbody>
                <ListAssignmentComponent
                    v-for="student in students"
                    :key="student"
                    :student="student"
                    :assignments="assignments"
                />
            </tbody>
        </v-table>
    </v-card>
</template>

<script>
import ListAssignmentComponent from "@/components/ListAssignmentComponent.vue"
import { getAssignments, getCourseStudentsById } from "@/services/api"
import { ref } from "vue"
import { useRoute } from "vue-router"
import { onMounted } from "@vue/runtime-core"
import { useStore } from "vuex"

export default {
    name: "AssignmentListView",

    components: { ListAssignmentComponent },

    setup() {
        const store = useStore()
        const route = useRoute()

        const students = ref([])
        const assignments = ref([])

        const updateStudents = () => {
            getCourseStudentsById(store.getters.token, route.params.id).then(
                (data) => {
                    console.log(data)
                    students.value = data
                }
            )
        }

        const updateAssignments = () => {
            getAssignments(store.getters.token, route.params.id).then(
                (data) => {
                    assignments.value = data
                }
            )
        }

        onMounted(() => {
            updateStudents()
            updateAssignments()
        })

        return {
            students,
            assignments,
        }
    },
}
</script>
