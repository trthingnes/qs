<template>
    <div>
        <div class="mx-auto bg-light-blue">
            <div class="ml-3 text-white">
                <h1>User settings</h1>
            </div>
            <div class="ml-3 text-white">
                <p>"Username"</p>
            </div>
        </div>

        <v-form ref="form" v-model="valid" lazy-validation>
            <v-text-field v-model="firstName" label="First Name"></v-text-field>
        </v-form>

        <v-form ref="form" v-model="valid" lazy-validation>
            <v-text-field v-model="lastName" label="Last Name"></v-text-field>
        </v-form>

        <v-form ref="form" v-model="valid" lazy-validation>
            <v-text-field v-model="email" label="Email"></v-text-field>
        </v-form>
    </div>
</template>

<script>
export default {
    name: "UserSettingsView",
}
</script>

<style scoped></style>
