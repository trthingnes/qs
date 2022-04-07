<template>
    <v-row>
        <h4 class="text-h4" data-testid="subheader">{{ title }}</h4>
        <v-divider class="my-5"></v-divider>
    </v-row>
</template>

<script>
export default {
    props: ["title"],
}
</script>
