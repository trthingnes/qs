<template>
    <tr :class="{ 'bg-green-lighten-3': entry.hasAssistant }" @click="assist()">
        <td>{{ entry.username }}</td>
        <td>{{ entry.location }}</td>
        <td>{{ entry.help ? "Hjelp" : "Godkjenning" }}</td>
    </tr>
</template>
<script>
import { useRoute, useRouter } from "vue-router"
import { useStore } from "vuex"
import { putQueueEntryHasAssistant } from "@/services/api"

export default {
    props: ["entry"],
    setup(props) {
        const store = useStore()
        const route = useRoute()
        const router = useRouter()

        const assist = () => {
            console.log(route.params.id)
            console.log(props.entry.id)
            putQueueEntryHasAssistant(
                store.getters.token,
                route.params.id,
                props.entry.id
            ).then(() => {
                router.push({
                    name: "assist",
                    params: { id: route.params.id, entry: props.entry.id },
                })
            })
        }

        return {
            assist,
        }
    },
}
</script>
