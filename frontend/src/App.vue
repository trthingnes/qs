<template>
    <!-- This is the recommended layout according to the docs. -->
    <v-app>
        <v-row>
            <v-col cols="4">
                <NavigationDrawerComponent />
            </v-col>
            <v-col cols="7">
                <HeaderComponent />
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
import HeaderComponent from "@/components/HeaderComponent"

export default {
    name: "App",
    components: {
        HeaderComponent,
        NavigationDrawerComponent,
    },
}
</script>
