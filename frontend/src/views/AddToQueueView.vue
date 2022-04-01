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
                        v-model="selected"
                        :value="asmnt"
                        :model-value="completed.includes(asmnt) ? true : false"
                        :disabled="completed.includes(asmnt) ? true : false"
                        :label="asmnt"
                    ></v-checkbox>
                </div>
            </v-row>
            <v-btn type="submit">Still i kø</v-btn>
        </v-container>
    </v-form>
</template>

<script>
import { defineComponent, ref } from "vue"
import {
    getAssignments,
    getCompletedAssignments,
    pushToQueue,
} from "../services/api"

export default defineComponent({
    setup() {
        const position = ref("")

        const assignments = getAssignments()

        const completed = getCompletedAssignments()

        const selected = ref([])

        const help = ref(false)

        var toggletext = () => {
            if (help.value) {
                return "Godkjenning"
            } else {
                return "Hjelp"
            }
        }

        const submit = () => {
            pushToQueue("Tor", position.value, toggletext(), selected.value)
            console.log(selected.value)
        }

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
