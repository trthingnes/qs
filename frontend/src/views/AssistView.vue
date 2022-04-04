<template>
    <v-component>
        <h1>{{ entry.username }} får hjelp</h1>
        <v-btn color="primary" @click="complete()">Marker som fullført</v-btn>
    </v-component>
</template>

<script>
import { deleteQueueEntry, getQueueEntryById } from "@/services/api"
import { useCookies } from "vue3-cookies"
import { useRoute, useRouter } from "vue-router"
import { ref } from "vue"

export default {
    setup() {
        const route = useRoute()
        const router = useRouter()
        const { cookies } = useCookies()
        const entry = ref()

        const getEntry = () => {
            getQueueEntryById(
                cookies.get("token"),
                route.params.id,
                route.params.entry
            ).then((data) => {
                console.log(data)
                entry.value = data
            })
        }

        const complete = () => {
            deleteQueueEntry(
                cookies.get("token"),
                route.params.id,
                route.params.entry
            ).then(() => {
                if (entry.value.help) {
                    router.push({
                        name: "view-queue",
                        params: { id: route.params.id },
                    })
                } else
                    router.push({
                        name: "list-assignments",
                        params: { id: route.params.id },
                    })
            })
        }

        getEntry()

        return { complete, entry }
    },
}
</script>
