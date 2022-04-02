<template>
    <v-form>
        <v-container>
            <v-row>
                <!-- TODO: Add functionality to all buttons -->
                <v-col cols="1" md="5">
                    <v-label>Subject code:</v-label>
                    <v-text-field :counter="9" required></v-text-field>
                </v-col>

                <v-col cols="1" md="5">
                    <v-label>Subject name:</v-label>
                    <v-text-field required></v-text-field>
                </v-col>

                <v-col cols="1" md="1">
                    <!-- -->
                </v-col>

                <v-col cols="1" md="5" class="">
                    <v-label>Number of assignments:</v-label>
                    <v-slider
                        v-model="numAssignments"
                        data-testid="slider"
                        :min="0"
                        :max="15"
                        :step="1"
                        thumb-label
                        :disabled="false"
                    ></v-slider>
                </v-col>

                <v-col cols="1" md="5" class="mt-2">
                    <!-- -->
                </v-col>

                <v-col cols="1" md="1">
                    <!-- -->
                </v-col>

                <v-col cols="1" md="5">
                    <v-btn :disabled="false">Save</v-btn>
                </v-col>
            </v-row>
        </v-container>
    </v-form>
</template>

<script>
import useStore from "vuex"
import { ref } from "vue"
import { useCookies } from "vue3-cookies"

export default {
    setup() {
        const store = useStore()
        const { cookies } = useCookies()

        const code = ref("")
        const name = ref("")
        const numAssignments = ref("")

        const codeRules = [
            (v) => !!v || "Emnekode er påkrevd.",
            (v) => v != 9 || "Emnekode skal være 9 tegn.",
        ]

        const nameRules = [(v) => !!v || "Navn på emne er påkrevd"]

        const createCourse = () => {
            let courseinfo = store.getters.courseInfo
            code.value = courseinfo.code
            name.value = courseinfo.name
            numAssignments.value = courseinfo.numAssignments
        }

        const onFormSubmit = () => {
            let courseinfo = store.getters.courseInfo

            let infoToAdd = {}

            updatedSuccessfully.value = updateCourseInfo(
                cookies.get("token"),
                infoToAdd
            ).then(async () => {
                let courseInfo = await getCourseInfo(cookies.get("token"))
                store.dispatch("setCourseInfo", courseInfo)
            })
        }

        updateCourseInfoFromStore()

        return {
            code,
            name,
            numAssignments,
        }
    },
}
</script>

<style scoped></style>
