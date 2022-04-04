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
import { useCookies } from "vue3-cookies"
import { useRoute } from "vue-router"
import { onMounted } from "@vue/runtime-core"

export default {
    name: "AssignmentListView",

    components: { ListAssignmentComponent },

    setup() {
        const { cookies } = useCookies()
        const route = useRoute()

        const students = ref([])
        const assignments = ref([])

        const updateStudents = () => {
            getCourseStudentsById(cookies.get("token"), route.params.id).then(
                (data) => {
                    console.log(data)
                    students.value = data
                }
            )
        }

        const updateAssignments = () => {
            getAssignments(cookies.get("token"), route.params.id).then(
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

<style scoped></style>
