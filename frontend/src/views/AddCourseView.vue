<template>
    <v-form @submit.prevent="onFormSubmit">
        <v-container>
            <HeaderComponent title="Legg til fag" />
            <v-row>
                <v-col cols="12" md="6">
                    <v-text-field
                        label="Emnekode"
                        v-model="code"
                        :rules="codeRules"
                        :counter="9"
                        required
                    ></v-text-field>
                </v-col>

                <v-col cols="12" md="6">
                    <v-text-field
                        label="Emnenavn"
                        v-model="name"
                        :rules="nameRules"
                        required
                    ></v-text-field>
                </v-col>

                <v-col cols="6" md="3">
                    <v-text-field
                        label="Semester"
                        v-model="semester"
                        required
                    ></v-text-field>
                </v-col>

                <v-col cols="6" md="3">
                    <v-text-field
                        label="År"
                        v-model="year"
                        :counter="4"
                        required
                    ></v-text-field>
                </v-col>

                <v-col cols="12" md="6">
                    <v-text-field label="URL" v-model="url"></v-text-field>
                </v-col>

                <v-col cols="12" md="6">
                    <v-textarea
                        v-model="description"
                        label="Beskrivelse"
                        required
                    ></v-textarea>
                </v-col>

                <v-col cols="12" md="6">
                    <v-label>Antall øvinger:</v-label>
                    <v-slider
                        v-model="numAssignments"
                        data-testid="slider"
                        :min="0"
                        :max="15"
                        :step="1"
                        thumb-label
                        thumb-color="primary"
                        color="primary"
                        :disabled="false"
                    ></v-slider>
                </v-col>

                <v-col cols="12" md="6">
                    <v-btn prepend-icon="mdi-plus" color="success" type="submit"
                        >Legg til fag
                    </v-btn>
                    <v-alert
                        v-if="updatedSuccessfully"
                        type="success"
                        class="my-5"
                        >Emnet ble opprettet.
                    </v-alert>
                </v-col>
            </v-row>
        </v-container>
    </v-form>
</template>

<script>
import { ref } from "vue"
import { useStore } from "vuex"
import { createCourse } from "@/services/api"
import HeaderComponent from "@/components/HeaderComponent.vue"

export default {
    components: {
        HeaderComponent,
    },
    setup() {
        const store = useStore()

        const code = ref("")
        const name = ref("")
        const semester = ref("")
        const year = ref(0)
        const url = ref("")
        const description = ref("")
        const numAssignments = ref(0)
        const updatedSuccessfully = ref(false)

        const codeRules = [
            (v) => !!v || "Emnekode er påkrevd.",
            (v) => v !== 9 || "Emnekode skal være 9 tegn.",
        ]
        const nameRules = [(v) => !!v || "Navn på emne er påkrevd"]

        const onFormSubmit = () => {
            let infoToAdd = {}
            infoToAdd["code"] = code.value
            infoToAdd["name"] = name.value
            infoToAdd["numAssignments"] = numAssignments.value
            infoToAdd["semester"] = semester.value.toUpperCase()
            infoToAdd["year"] = year.value
            infoToAdd["description"] = description.value
            infoToAdd["url"] = url.value

            updatedSuccessfully.value = createCourse(
                store.getters.token,
                infoToAdd
            )
        }

        return {
            code,
            name,
            semester,
            year,
            url,
            description,
            numAssignments,
            updatedSuccessfully,
            codeRules,
            nameRules,
            onFormSubmit,
        }
    },
}
</script>

<style scoped></style>
