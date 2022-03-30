<template>
    <v-component>
        <div v-for="asmnt in assignments" :key="asmnt">
            <v-checkbox
                :value="asmnt"
                :key="asmnt"
                :label="asmnt"
                :v-model="selected"
                :class="{ disabled: completed.includes(asmnt) }"
            ></v-checkbox>
        </div>
    </v-component>
</template>

<script>
import { defineComponent } from "vue"

export default defineComponent({
    setup() {
        const assignments = ["1", "2", "3", "4"]

        const completed = ["1", "2"]

        return {
            assignments,
            completed,
        }
    },
})
</script>
