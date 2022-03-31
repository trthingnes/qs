<template>
    <v-container>
        <p>Din posisjon i køen er:</p>
        <h1>{{ position() }}</h1>
    </v-container>
</template>

<script>
import { defineComponent } from "vue"
import { getQueue } from "../services/api"

export default defineComponent({
    setup() {
        const queue = getQueue()

        const position = () => {
            return queue.findIndex((queue) => queue.name === "Jonathan") + 1
        }

        return {
            position,
        }
    },
})
</script>
