<template>
    <v-app>
        <v-main>
            <v-row>
                <v-col cols="3" class="pr-0">
                    <HomeView />
                </v-col>
                <v-col cols="6" class="pl-0">
                    <QueueView />
                </v-col>
            </v-row>
        </v-main>
    </v-app>
</template>

<script>
import HomeView from "@/views/HomeView"
import QueueView from "@/views/QueueView"
export default {
    name: "App",
    components: {
        QueueView,
        HomeView,
    },

    data: () => ({
        //
    }),
}
</script>
