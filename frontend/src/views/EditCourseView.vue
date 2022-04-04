<template>
    <v-form>
        <v-container>
            <EditCourseDetailsComponent />

            <EditCourseParticipantsComponent />
        </v-container>
    </v-form>
</template>

<script>
import EditCourseDetailsComponent from "@/components/course/EditCourseDetailsComponent.vue"
import EditCourseParticipantsComponent from "@/components/course/EditCourseParticipantsComponent.vue"

export default {
    components: {
        EditCourseDetailsComponent,
        EditCourseParticipantsComponent,
    },
}
</script>
