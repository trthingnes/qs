<template>
    <v-app>
        <v-toolbar>
            <v-toolbar-items>
                <h1>{{ view.name }}</h1>
                <v-spacer></v-spacer>
                <h1 v-if="subject.name !== null">{{ subject.name }}</h1>
            </v-toolbar-items>
        </v-toolbar>
    </v-app>
</template>

<script>
export default {
    name: "HeaderComponent",
    data: () => ({
        view: { name: "Queue" },
        subject: {
            name: "Full-Stack Applikasjonsutvikling",
        },
    }),
}
</script>

<style scoped></style>
