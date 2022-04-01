<template>
    <v-card class="mx-auto mr-4">
        <v-list>
            <v-list-subheader>
                Total number of students:
                {{ students.length }}
            </v-list-subheader>

            <v-list-item
                v-for="(student, i) in students"
                :key="i"
                :value="student"
            >
                <v-list-item-title v-text="student.name"></v-list-item-title>
                <v-spacer></v-spacer>
                <v-list-item-content>
                    <v-row>
                        <v-col
                            v-for="(assignment, i) in assignments"
                            :key="i"
                            :value="assignment"
                        >
                            <!-- TODO: Fix an appropriate solution to displaying approved/not approved assignments -->
                            <v-card>
                                <v-card-text>{{ assignment }}</v-card-text>
                            </v-card>
                        </v-col>
                    </v-row>
                </v-list-item-content>
            </v-list-item>
        </v-list>
    </v-card>
</template>

<script>
import { getStudents, getAssignments } from "../services/api"

export default {
    name: "AssignmentListView",
    data: () => ({
        students: getStudents(),
        assignments: getAssignments(),
    }),
}
</script>

<style scoped></style>
