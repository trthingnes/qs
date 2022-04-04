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
import { useStore } from "vuex"
import { useRoute, useRouter } from "vue-router"
import {
    getAssignments,
    getCompletedAssignments,
    postQueueEntry,
} from "../services/api"

export default defineComponent({
    setup() {
        const store = useStore()
        const route = useRoute()
        const router = useRouter()

        const assignments = ref([])
        const completed = ref([])
        const position = ref("")
        const selected = ref([])
        const help = ref(false)

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

        var toggletext = () => {
            if (help.value) {
                return "Godkjenning"
            } else {
                return "Hjelp"
            }
        }

        const submit = () => {
            postQueueEntry(
                store.getters.token,
                route.params.id,
                !help.value,
                position.value
            ).then(
                router.push({
                    name: "view-queue",
                    params: { id: route.params.id },
                })
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
