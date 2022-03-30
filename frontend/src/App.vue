<template>
    <!-- This is the recommended layout according to the docs. -->
    <v-app>
        <v-row>
            <v-col cols="4">
                <NavigationDrawerComponent />
            </v-col>
        </v-row>

        <v-main>
            <v-container fluid>
                <!-- Show whatever router view is currently active. -->
                <router-view />
            </v-container>
        </v-main>
    </v-app>
</template>

<script>
import NavigationDrawerComponent from "@/components/NavigationDrawerComponent.vue"

export default {
    name: "App",
    components: {
        NavigationDrawerComponent,
    },
}
</script>
