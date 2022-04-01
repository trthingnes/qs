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
                        disabled
                    ></v-text-field>
                </v-col>

                <v-col cols="1" md="5">
                    <v-label>Subject name:</v-label>
                    <v-text-field
                        v-model="subjectName"
                        :counter="30"
                        disabled
                    ></v-text-field>
                </v-col>

                <v-col cols="1" md="1">
                    <!-- -->
                </v-col>

                <v-col cols="1" md="5" class="">
                    <!-- TODO: Find way to read list of all students from csv file. -->
                    <!-- TODO: Also find a more fitting drop down menu, for selecting multiple students at once -->
                    <div class="text-center">
                        <v-menu>
                            <template v-slot:activator="{ props }">
                                <v-btn color="primary" dark v-bind="props">
                                    List students
                                </v-btn>
                            </template>

                            <v-list>
                                <v-list-item
                                    v-for="(student, i) in students"
                                    :key="i"
                                >
                                    <v-list-item-title>{{
                                        student.name
                                    }}</v-list-item-title>
                                </v-list-item>
                            </v-list>
                        </v-menu>
                    </div>
                </v-col>

                <v-col cols="1" md="1">
                    <!-- -->
                </v-col>

                <v-col cols="1" md="1">
                    <!-- -->
                </v-col>

                <v-col cols="1" md="1">
                    <div class="text-center">
                        <v-btn :disabled="false" @click="deleteSubject"
                            >Delete</v-btn
                        >
                    </div>
                </v-col>

                <v-col cols="1" md="5">
                    <div class="text-center">
                        <v-btn :disabled="false" @click="updateInfo"
                            >Save</v-btn
                        >
                    </div>
                </v-col>
            </v-row>
        </v-container>
    </v-form>
</template>

<script>
export default {
    name: "EditSubjectView",
}
</script>

<style scoped></style>
