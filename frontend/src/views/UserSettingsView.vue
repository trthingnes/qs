<template>
    <v-form v-model="valid">
        <v-container>
            <v-row>
                <!-- TODO: Add functionality to all buttons -->
                <v-col cols="12" md="4">
                    <v-text-field
                        v-model="firstname"
                        :rules="nameRules"
                        :counter="15"
                        label="First name"
                        required
                    ></v-text-field>
                </v-col>

                <v-col cols="12" md="4">
                    <v-text-field
                        v-model="lastname"
                        :rules="nameRules"
                        :counter="15"
                        label="Last name"
                        required
                    ></v-text-field>
                </v-col>

                <v-col cols="12" md="4">
                    <v-text-field
                        v-model="email"
                        :rules="emailRules"
                        label="Email"
                        required
                    ></v-text-field>
                </v-col>

                <v-col cols="12" md="4">
                    <v-text-field
                        v-model="password"
                        :rules="passwordRules"
                        :type="'password'"
                        label="Password"
                        disabled="true"
                        required
                    ></v-text-field>
                </v-col>

                <v-col cols="12" md="4">
                    <v-text-field
                        v-model="newPass"
                        :rules="passwordRules"
                        :type="'password'"
                        label="New password"
                    ></v-text-field>
                </v-col>

                <v-col cols="12" md="4">
                    <v-text-field
                        v-model="repPass"
                        :rules="passwordRules"
                        :type="'password'"
                        label="Repeat new password"
                    ></v-text-field>
                </v-col>

                <v-col cols="12" md="4">
                    <v-btn :disabled="false" @click="updateInfo">Save</v-btn>
                </v-col>
            </v-row>
        </v-container>
    </v-form>
</template>

<script>
export default {
    name: "UserSettingsView",
    data: () => ({
        valid: true,
        firstname: "",
        lastname: "",
        email: "",
        password: "",
        newPass: "",
        repPass: "",
        user: {
            firstname: "Jonathan",
            lastname: "Løseth",
            email: "jonathhl@stud.ntnu.no",
            password: "admin",
        },
        nameRules: [
            (v) => !!v || "Name is required",
            (v) =>
                (v && v.length <= 15) || "Name must be less than 15 characters",
        ],
        emailRules: [
            (v) => !!v || "Email is required",
            (v) => /.+@.+\..+/.test(v) || "E-mail must be valid",
        ],
        passwordRules: [
            (v) => !!v || "Password can not be empty",
            (v) =>
                /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/.test(
                    v
                ) || "Password must be valid",
        ],
    }),
    methods: {
        updateInfo(firstname, lastname, email, password) {
            if (this.newPass === this.repPass) password = this.newPass

            this.$store.dispatch("saveUserInfo", {
                firstname,
                lastname,
                email,
                password,
            })
            console.log("Updated user info")
        },
    },
}
</script>
