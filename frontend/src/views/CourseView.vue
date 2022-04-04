<template>
    <v-container>
        <HeaderComponent :title="name" />
        <v-row>
            <v-col cols="12" md="6">
                <v-text-field
                    label="Fagkode"
                    v-model="code"
                    readonly
                ></v-text-field>
            </v-col>

            <v-col cols="12" md="6">
                <v-text-field
                    label="Fagnavn"
                    v-model="name"
                    readonly
                ></v-text-field>
            </v-col>

            <v-col cols="6" md="3">
                <v-text-field
                    label="Semester"
                    v-model="semester"
                    readonly
                ></v-text-field>
            </v-col>

            <v-col cols="6" md="3">
                <v-text-field label="År" v-model="year" readonly></v-text-field>
            </v-col>

            <v-col cols="12" md="6">
                <v-text-field
                    label="Nettsted"
                    v-model="url"
                    readonly
                ></v-text-field>
            </v-col>

            <v-col cols="12" md="6">
                <v-textarea
                    label="Beskrivelse"
                    v-model="description"
                    readonly
                ></v-textarea>
            </v-col>

            <v-col cols="12" md="6">
                <v-btn
                    color="success"
                    prepend-icon="mdi-door"
                    @click="onQueueOpenClick"
                    class="mr-3"
                >
                    Åpne køen
                </v-btn>
            </v-col>
            <v-col cols="12" md="6">
                <v-btn
                    color="primary"
                    prepend-icon="mdi-clipboard-list"
                    @click="onAssignmentsOpenClick"
                    class="mr-3"
                >
                    Vis Oppgaver
                </v-btn>
            </v-col>
        </v-row>
    </v-container>
</template>
<script>
import { ref, watch } from "vue"
import { useRoute, useRouter } from "vue-router"
import { useCookies } from "vue3-cookies"
import { getCourseById } from "@/services/api"
import HeaderComponent from "@/components/HeaderComponent.vue"

export default {
    components: {
        HeaderComponent,
    },
    setup() {
        const router = useRouter()
        const route = useRoute()
        const { cookies } = useCookies()

        const code = ref("")
        const name = ref("")
        const url = ref("")
        const description = ref("")
        const semester = ref("")
        const year = ref(0)

        const getCourseInfo = (id) => {
            if (!id) return

            getCourseById(cookies.get("token"), id).then((course) => {
                code.value = course.code
                name.value = course.name
                url.value = course.url
                description.value = course.description
                semester.value = course.semester
                year.value = course.year
            })
        }

        const onQueueOpenClick = () => {
            router.push({ name: "view-queue", params: { id: route.params.id } })
        }

        const onAssignmentsOpenClick = () => {
            router.push({
                name: "assignments",
                params: { id: route.params.id },
            })
        }

        getCourseInfo(route.params.id)
        watch(
            () => route.params.id,
            async (id) => getCourseInfo(id)
        )

        return {
            code,
            name,
            url,
            description,
            semester,
            year,
            onQueueOpenClick,
            onAssignmentsOpenClick,
        }
    },
}
</script>
