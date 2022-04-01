<template>
    <v-app>
        <v-toolbar>
            <v-toolbar-items>
                <h1>{{ view.name }}</h1>
                <v-spacer></v-spacer>
                <h1>
                    {{ subject.name }}
                </h1>
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
            code: "IDATT2105",
        },
    }),
}
</script>

<style scoped></style>
