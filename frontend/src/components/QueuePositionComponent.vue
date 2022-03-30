<template>
    <v-container>
        <p>Din posisjon i køen er:</p>
        <h1>{{ position }}</h1>
    </v-container>
</template>

<script>
import { defineComponent } from "vue"

export default defineComponent({
    setup() {
        const position = 1

        return {
            position,
        }
    },
})
</script>
