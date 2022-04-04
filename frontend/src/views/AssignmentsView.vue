<template>
    <v-component>
        <h2>Fullførte og gjenstående oppgaver i faget {{ course.name }}</h2>
    </v-component>
    <v-component>
        <div v-for="asmnt in assignments" :key="asmnt">
            <v-checkbox
                :value="success"
                :model-value="completed.includes(asmnt) ? true : false"
                :key="asmnt"
                :label="asmnt"
                :disabled="completed.includes(asmnt) ? true : false"
            ></v-checkbox>
        </div>
    </v-component>
</template>

<script>
import { defineComponent, ref, onMounted } from "vue"
import { useStore } from "vuex"
import { useRoute } from "vue-router"
import {
    getAssignments,
    getCompletedAssignments,
    getCourseById,
} from "../services/api"

export default defineComponent({
    setup() {
        const store = useStore()
        const route = useRoute()

        const assignments = ref([])
        const completed = ref([])
        const course = ref({})

        const getCourse = () => {
            getCourseById(store.getters.token, route.params.id).then((data) => {
                course.value = data
            })
        }

        const updateAssignments = () =>
            getAssignments(store.getters.token, route.params.id).then(
                (data) => {
                    console.log(data)
                    assignments.value = data
                }
            )

        const updateCompletedAssignments = () =>
            getCompletedAssignments(store.getters.token, route.params.id).then(
                (data) => {
                    console.log(data)
                    completed.value = data
                }
            )

        onMounted(() => {
            updateAssignments()
            updateCompletedAssignments()
            getCourse()
        })

        return {
            assignments,
            completed,
            course,
        }
    },
})
</script>

<style scoped>
.asmnt-completed {
    font-size: 24px;
    user-select: all;
}
</style>
