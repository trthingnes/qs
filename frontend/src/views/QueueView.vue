<template>
    <div>
        <div>
            <div></div>
            <div></div>
        </div>
        <div>
            <v-card class="mx-auto mr-4" max-height="300">
                <v-list density="compact">
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
export default {
    name: "QueueView",
    data: () => ({
        queue: [{ name: "Jonathan" }, { name: "Tobias" }, { name: "Tor" }],
    }),
}
</script>

<style scoped></style>
