<template>
    <div>
        <div class="mx-auto bg-light-blue">
            <div class="ml-3 text-white">
                <h1>"Name of subject"</h1>
            </div>
            <div class="ml-3 text-white">
                <p>Students waiting: null</p>
            </div>
        </div>

        <div>
            <v-card class="mx-auto mr-4">
                <QueuePositionComponent />
                <v-list>
                    <v-list-subheader>QUEUE</v-list-subheader>
                    <v-list-item
                        v-for="(student, i) in queue"
                        :key="i"
                        :value="student"
                    >
                        <v-list-item-title
                            v-text="student.name"
                        ></v-list-item-title>
                    </v-list-item>
                </v-list>
            </v-card>
        </div>
    </div>
</template>

<script>
import QueuePositionComponent from "../components/QueuePositionComponent.vue"

export default {
    name: "QueueView",
    data: () => ({
        queue: [{ name: "Jonathan" }, { name: "Tobias" }, { name: "Tor" }],
    }),

    components: {
        QueuePositionComponent,
    },
}
</script>

<style scoped></style>
