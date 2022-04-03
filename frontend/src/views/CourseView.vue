<template>
    <v-container>
        <HeaderComponent :title="name" />
    </v-container>
</template>
<script>
import { ref, watch } from "vue"
import { useRoute } from "vue-router"
import HeaderComponent from "@/components/HeaderComponent.vue"

export default {
    components: {
        HeaderComponent,
    },
    setup() {
        const route = useRoute()
        const name = ref("IDATT1001 Programmering 1")

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

            getCourseInfo(route.params.id)
            watch(
                () => route.params.id,
                async (id) => getCourseInfo(id)
            )
        }

        return { name }
    },
}
</script>
