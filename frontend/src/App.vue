<template>
    <v-app>
        <v-main>
            <v-row>
                <!-- A row has 12 columns, so to fill the whole screen the columns should sum to 12. -->
                <v-col cols="2" class="pr-0">
                    <SideBar />
                    <!-- Show sidebar constantly -->
                </v-col>
                <v-col cols="10" class="pl-0">
                    <router-view />
                    <!-- Show whatever router view is currently active. -->
                </v-col>
            </v-row>
        </v-main>
    </v-app>
</template>

<script>
import SideBar from "@/components/SideBarComponent.vue"

export default {
    name: "App",
    components: {
        SideBar,
    },
}
</script>
