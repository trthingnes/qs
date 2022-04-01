<template>
    <v-form v-model="valid">
        <v-container>
            <v-row>
                <!-- TODO: Add functionality to all buttons -->
                <v-col cols="1" md="5">
                    <v-label>Subject code:</v-label>
                    <v-text-field
                        v-model="subjectcode"
                        :counter="9"
                        required
                    ></v-text-field>
                </v-col>

                <v-col cols="1" md="5">
                    <v-label>Subject name:</v-label>
                    <v-text-field
                        v-model="subjectName"
                        :counter="30"
                        required
                    ></v-text-field>
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
                    <v-btn :disabled="false" @click="updateInfo">Save</v-btn>
                </v-col>
            </v-row>
        </v-container>
    </v-form>
</template>

<script>
export default {
    name: "EditSubjectView",
    data: () => ({
        numAssignments: 0,
        students: [
            { name: "Jonathan", type: "Help", assignment: "1" },
            { name: "Tobias", type: "Help", assignment: "69" },
            { name: "Tor", type: "Help", assignment: "38" },
        ],
    }),
}
</script>

<style scoped></style>
