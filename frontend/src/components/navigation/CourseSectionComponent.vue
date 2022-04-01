<template>
    <v-divider class="mt-2 mb-2"></v-divider>
    <v-list-subheader color="black">
        <v-icon :icon="icon" class="mr-3"></v-icon>
        {{ title }}
    </v-list-subheader>
    <v-list-item
        v-for="course in courses"
        :title="course.name"
        :value="course.id"
        :key="course.id"
        @click.stop="onCourseClick(course.id)"
    ></v-list-item>
</template>
<script>
export default {
    props: ["title", "icon", "courses", "slug"],
    emits: ["click"],
    setup(props, ctx) {
        const onCourseClick = (id) => {
            ctx.emit("click", `/${props.slug}/${id}`)
        }

        return { onCourseClick }
    },
}
</script>
