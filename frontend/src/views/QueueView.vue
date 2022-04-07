<template>
    <v-container>
        <HeaderComponent title="Kø" />
        <v-row>
            <v-col cols="12">
                <v-btn color="primary" @click="enterqueue">Still i kø</v-btn>
                <v-table fixed-header>
                    <thead>
                        <tr>
                            <th>Navn</th>
                            <th>Plassering</th>
                            <th>Type</th>
                        </tr>
                    </thead>
                    <tbody>
                        <QueueEntryComponent
                            v-for="entry in entries"
                            :key="entry"
                            :entry="entry"
                        />
                    </tbody>
                </v-table>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
import { ref } from "vue"
import { useRoute, useRouter } from "vue-router"
import { useCookies } from "vue3-cookies"
import HeaderComponent from "@/components/HeaderComponent.vue"
import QueueEntryComponent from "@/components/QueueEntryComponent.vue"
import { getQueueEntriesById } from "@/services/api"

export default {
    components: {
        HeaderComponent,
        QueueEntryComponent,
    },
    setup() {
        const route = useRoute()
        const router = useRouter()
        const { cookies } = useCookies()

        const entries = ref()

        const getEntries = () => {
            getQueueEntriesById(cookies.get("token"), route.params.id).then(
                (data) => {
                    entries.value = data
                }
            )
        }

        const enterqueue = () => {
            router.push({
                name: "enter-queue",
                params: { id: route.params.id },
            })
        }

        getEntries()

        return { entries, enterqueue }
    },
}
</script>
