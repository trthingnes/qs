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
import { useStore } from "vuex"
import { useCookies } from "vue3-cookies"
import NavigationDrawerComponent from "@/components/navigation/NavigationDrawerComponent.vue"

export default {
    name: "App",
    components: {
        NavigationDrawerComponent,
    },
    setup() {
        const store = useStore()
        const { cookies } = useCookies()

        if (cookies.isKey("token")) {
            store.dispatch("setToken", cookies.get("token"))
        }
    },
}
</script>
