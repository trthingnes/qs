<template>
    <v-form @submit.prevent="submit()">
        <v-container>
            <v-row>
                <v-switch v-model="help" :label="toggletext()"></v-switch>
            </v-row>
            <v-row>
                <v-text-field
                    label="Plassering: bygning, rom, bordnr"
                    v-model="position"
                ></v-text-field>
            </v-row>
            <v-row>
                <p>Oppgaver</p>
                <div v-for="asmnt in assignments" :key="asmnt">
                    <v-checkbox
                        :value="asmnt"
                        :model-value="completed.includes(asmnt) ? true : false"
                        readonly
                        :label="asmnt"
                    ></v-checkbox>
                </div>
            </v-row>
            <v-btn type="submit">Still i kø</v-btn>
        </v-container>
    </v-form>
</template>

<script>
import { defineComponent, onMounted, ref } from "vue"
import { useRoute } from "vue-router"
import { useCookies } from "vue3-cookies"
import {
    getAssignments,
    getCompletedAssignments,
    postQueueEntry,
} from "../services/api"

export default defineComponent({
    setup() {
        const route = useRoute()

        const assignments = ref([])
        const completed = ref([])
        const position = ref("")
        const selected = ref([])
        const help = ref(false)
        const { cookies } = useCookies()

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

        var toggletext = () => {
            if (help.value) {
                return "Godkjenning"
            } else {
                return "Hjelp"
            }
        }

        const submit = () => {
            postQueueEntry(
                cookies.get("token"),
                route.params.id,
                help.value,
                position.value
            )
        }

        onMounted(() => {
            updateAssignments()
            updateCompletedAssignments()
        })

        return {
            position,
            selected,
            assignments,
            completed,
            help,
            toggletext,
            submit,
        }
    },
})
</script>
