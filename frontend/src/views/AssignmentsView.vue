<template>
    <v-component>
        <h2>Fullførte og gjenstående oppgaver i faget 'fagkode'</h2>
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
import { useRoute } from "vue-router"
import { getAssignments, getCompletedAssignments } from "../services/api"
import { useCookies } from "vue3-cookies"

export default defineComponent({
    setup() {
        const { cookies } = useCookies()
        const route = useRoute()

        const assignments = ref([])
        const completed = ref([])

        const updateAssignments = () =>
            getAssignments(cookies.get("token"), route.params.id).then(
                (data) => {
                    console.log(data)
                    assignments.value = data
                }
            )

        const updateCompletedAssignments = () =>
            getCompletedAssignments(cookies.get("token"), route.params.id).then(
                (data) => {
                    console.log(data)
                    completed.value = data
                }
            )

        onMounted(() => {
            updateAssignments()
            updateCompletedAssignments()
        })

        return {
            assignments,
            completed,
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
