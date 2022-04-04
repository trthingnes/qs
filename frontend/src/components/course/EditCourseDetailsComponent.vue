<template>
    <HeaderComponent title="Rediger fag" />
    <v-row>
        <v-col cols="12" md="6">
            <v-text-field label="Fagkode" v-model="code"></v-text-field>
        </v-col>

        <v-col cols="12" md="6">
            <v-text-field label="Fagnavn" v-model="name"></v-text-field>
        </v-col>

        <v-col cols="6" md="3">
            <v-text-field label="Semester" v-model="semester"></v-text-field>
        </v-col>

        <v-col cols="6" md="3">
            <v-text-field label="År" v-model="year"></v-text-field>
        </v-col>

        <v-col cols="12" md="6">
            <v-text-field label="Nettsted" v-model="url"></v-text-field>
        </v-col>

        <v-col cols="12" md="6">
            <v-textarea label="Beskrivelse" v-model="description"></v-textarea>
        </v-col>

        <v-col cols="12" md="6">
            <v-btn
                color="success"
                prepend-icon="mdi-check"
                @click="onCourseSaveClick"
                class="mr-3"
            >
                Lagre fag
            </v-btn>
            <v-btn
                color="error"
                prepend-icon="mdi-delete"
                @click="onCourseDeleteClick"
            >
                Slett fag
            </v-btn>
            <v-alert v-if="updatedSuccessfully" type="success" class="my-5"
                >Faget ble oppdatert.</v-alert
            >
        </v-col>
    </v-row>
</template>

<script>
import { ref, watch } from "vue"
import { useStore } from "vuex"
import { useRoute } from "vue-router"
import { getCourseById, updateCourseInfoById } from "@/services/api"
import HeaderComponent from "@/components/HeaderComponent.vue"

export default {
    components: {
        HeaderComponent,
    },
    setup() {
        const store = useStore()
        const route = useRoute()

        const code = ref("")
        const name = ref("")
        const url = ref("")
        const description = ref("")
        const semester = ref("")
        const year = ref(0)
        const updatedSuccessfully = ref(false)

        const getCourseInfo = (id) => {
            if (!id) return

            getCourseById(store.getters.token, id).then((course) => {
                code.value = course.code
                name.value = course.name
                url.value = course.url
                description.value = course.description
                semester.value = course.semester
                year.value = course.year
            })
        }

        const onCourseDeleteClick = () => {}

        const onCourseSaveClick = () => {
            let infoToUpdate = {
                code: code.value,
                name: name.value,
                url: url.value,
                description: description.value,
                semester: semester.value,
                year: year.value,
            }

            updatedSuccessfully.value = updateCourseInfoById(
                store.getters.token,
                route.params.id,
                infoToUpdate
            )
        }

        getCourseInfo(route.params.id)
        watch(
            () => route.params.id,
            async (id) => getCourseInfo(id)
        )
        watch(
            () => updatedSuccessfully,
            async () => getCourseInfo(route.params.id)
        )

        return {
            code,
            name,
            url,
            description,
            semester,
            year,
            updatedSuccessfully,
            onCourseDeleteClick,
            onCourseSaveClick,
        }
    },
}
</script>
