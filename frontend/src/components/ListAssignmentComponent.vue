<template>
    <tr>
        <td>{{ student.firstname }}</td>
        <td>
            <div v-for="asmnt in assignments" :key="asmnt">
                <v-checkbox
                    :v-model="selected"
                    :value="success"
                    :model-value="completed.includes(asmnt) ? true : false"
                    :key="asmnt"
                    :label="asmnt"
                    :disabled="completed.includes(asmnt) ? true : false"
                ></v-checkbox>
            </div>
        </td>
    </tr>
</template>
<script>
import { ref } from "vue"

export default {
    props: {
        student: Array,
        assignmments: Array,
    },
    setup() {
        const selected = ref([])

        const assignmentClicked = (ordinal) => {
            selected.value = ordinal
        }

        return {
            selected,
            assignmentClicked,
        }
    },
}
</script>
