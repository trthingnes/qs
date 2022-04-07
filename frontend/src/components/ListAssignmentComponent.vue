<template>
    <tr>
        <td>{{ student["firstName"] }} {{ student["lastName"] }}</td>
        <td>
            <div>
                <v-btn-toggle v-for="asmnt in assignments" :key="asmnt">
                    <v-btn
                        plain
                        rounded="pill"
                        @click="selectAssignment(asmnt, student['username'])"
                        >{{ asmnt }}</v-btn
                    >
                </v-btn-toggle>
            </div>
        </td>
        <td>
            <v-btn color="primary" @click="approve()"
                >Godkjenn markert oppgave</v-btn
            >
        </td>
    </tr>
</template>
<script>
import { ref } from "vue"
import { postCompletedAssignment } from "@/services/api"
import { useCookies } from "vue3-cookies"
import { useRoute, useRouter } from "vue-router"

export default {
    props: {
        student: Object,
        assignments: Array,
    },
    setup() {
        const selected = ref()
        const selectedUsername = ref()
        const { cookies } = useCookies()
        const route = useRoute()
        const router = useRouter()

        const selectAssignment = (assignmentNumber, username) => {
            selected.value = assignmentNumber
            selectedUsername.value = username
            console.log(selectedUsername.value)
        }

        const approve = () => {
            postCompletedAssignment(
                cookies.get("token"),
                route.params.id,
                selected.value,
                selectedUsername.value
            ).then(
                router.push({
                    name: "view-queue",
                    params: { id: route.params.id },
                })
            )
        }

        return {
            approve,
            selectAssignment,
        }
    },
}
</script>
