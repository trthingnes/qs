<template>
    <div>
        <div>
            <v-card class="mx-auto mr-4">
                <v-list>
                    <v-list-subheader
                        >Students in queue: {{ queue.length }}</v-list-subheader
                    >
                    <v-list-item
                        v-for="(student, i) in queue"
                        :key="i"
                        :value="student"
                        class="text-center"
                    >
                        <v-list-item-title
                            v-text="student.name"
                        ></v-list-item-title>
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
        </div>
    </div>
</template>

<script>
export default {
    name: "QueueView",
    data: () => ({
        queue: [
            { name: "Jonathan", type: "Help", assignment: "1" },
            { name: "Tobias", type: "Help", assignment: "69" },
            { name: "Tor", type: "Help", assignment: "38" },
        ],
    }),
}
</script>

<style scoped></style>
