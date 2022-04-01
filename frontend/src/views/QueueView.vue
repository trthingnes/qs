<template>
    <v-card class="mx-auto mr-4">
        <QueuePositionComponent />
        <v-list>
            <v-list-subheader
                >Students in queue: {{ queue.length }}</v-list-subheader
            >
            <v-list-item
                v-for="(student, i) in queue"
                :key="i"
                :value="student"
                class="text-center"
                @click="regHelp"
            >
                <v-list-item-title v-text="student.name"></v-list-item-title>
                <v-spacer></v-spacer>
                <v-list-item-content>
                    {{ student.type }}
                    <v-spacer></v-spacer>
                    Assignment:
                    {{ student.assignment }}</v-list-item-content
                >
            </v-list-item>
        </v-list>
    </v-card>
</template>

<script>
import QueuePositionComponent from "../components/QueuePositionComponent.vue"
import { getQueue } from "../services/api"

export default {
    name: "QueueView",
    data: () => ({
        queue: getQueue(),
    }),
    methods: {
        // TODO: Finne mer forklarende metodenavn, finn også rett syntax for å endre fargen.
        regHelp() {
            document.documentElement.style.color = "#add8e6"
        },
    },

    components: {
        QueuePositionComponent,
    },
}
</script>

<style scoped></style>
