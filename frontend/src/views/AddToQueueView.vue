<template>
    <v-form>
        <v-container>
            <v-row>
                <v-switch v-model="help" :label="toggletext()"></v-switch>
            </v-row>
            <v-row>
                <v-text-field
                    label="Plassering: bygning, rom, bordnr"
                    :model="position"
                ></v-text-field>
            </v-row>
            <v-row>
                <p>Oppgaver</p>
                <div v-for="asmnt in assignments" :key="asmnt">
                    <v-checkbox
                        :value="asmnt"
                        :key="asmnt"
                        :label="asmnt"
                        :v-model="selected"
                    ></v-checkbox>
                </div>
            </v-row>
            <v-btn type="submit">Still i kø</v-btn>
        </v-container>
    </v-form>
</template>

<script>
import { defineComponent, ref } from "vue"

export default defineComponent({
    setup() {
        const position = ""
        const assignments = ["1", "2", "3", "4"]
        const selected = []
        const help = ref(false)
        var toggletext = () => {
            if (help.value) {
                return "Godkjenning"
            } else {
                return "Hjelp"
            }
        }

        return {
            position,
            selected,
            assignments,
            help,
            toggletext,
        }
    },
})
</script>
