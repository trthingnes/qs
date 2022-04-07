<template>
    <v-list-subheader color="black" data-testid="subheader">
        <v-icon :icon="icon" class="mr-3"></v-icon>
        {{ title }}
    </v-list-subheader>
    <v-list-item
        data-testid="item"
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
