<template>
    <v-component>
        <h1>{{ entry.username }} får hjelp</h1>
        <v-btn color="primary" @click="complete()">Marker som fullført</v-btn>
    </v-component>
</template>

<script>
import { deleteQueueEntry, getQueueEntryById } from "@/services/api"
import { useRoute, useRouter } from "vue-router"
import { ref } from "vue"
import { useStore } from "vuex"

export default {
    setup() {
        const store = useStore()
        const route = useRoute()
        const router = useRouter()
        const entry = ref()

        const getEntry = () => {
            getQueueEntryById(
                store.getters.token,
                route.params.id,
                route.params.entry
            ).then((data) => {
                console.log(data)
                entry.value = data
            })
        }

        const complete = () => {
            deleteQueueEntry(
                store.getters.token,
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
