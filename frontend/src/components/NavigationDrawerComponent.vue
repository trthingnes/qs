<template>
    <v-navigation-drawer width="350" app permanent>
        <v-list>
            <v-list-item>
                <h1>Qs</h1>
            </v-list-item>

            <v-divider class="mt-2 mb-2"></v-divider>

            <v-list-subheader color="black">
                <v-icon icon="mdi-school" class="mr-3"></v-icon>
                Dine fag
            </v-list-subheader>
            <v-list-item
                v-for="[code, name] in studentCourses"
                :title="name"
                :value="code"
                :key="code"
                @click.stop="onCourseClick(code)"
            ></v-list-item>

            <div v-if="assistantCourses.length">
                <v-divider class="mt-2 mb-2"></v-divider>

                <v-list-subheader color="black">
                    <v-icon icon="mdi-school" class="mr-3"></v-icon>
                    Læringsassistent
                </v-list-subheader>
                <v-list-item
                    v-for="[code, name] in assistantCourses"
                    :title="name"
                    :value="code"
                    :key="code"
                    @click.stop="onCourseClick(code)"
                ></v-list-item>
            </div>
        </v-list>

        <template v-slot:append>
            <v-divider></v-divider>
            <v-card class="mt-3 pb-2" density="compact">
                <v-card-title> Ola Nordmann </v-card-title>
                <v-card-subtitle> olanord@stud.ntnu.no </v-card-subtitle>
                <v-card-actions>
                    <v-btn
                        variant="contained-text"
                        append-icon="mdi-cog"
                        class="ml-2"
                    >
                        Innstillinger
                    </v-btn>
                    <v-btn
                        variant="contained-text"
                        append-icon="mdi-logout"
                        class="ml-2"
                    >
                        Logg ut
                    </v-btn>
                </v-card-actions>
            </v-card>
        </template>
    </v-navigation-drawer>
</template>
<script>
import { useRouter } from "vue-router"

export default {
    setup() {
        const router = useRouter()

        const studentCourses = [
            ["IDATT2101", "Algoritmer og datastrukturer"],
            ["IDATT2105", "Full-stack applikasjonsutvikling"],
        ]

        const assistantCourses = [
            ["IDATT1001", "Programmering 1"],
            ["IDATT2001", "Programmering 2"],
        ]

        const onCourseClick = (code) => {
            router.push(`/courses/${code}`)
        }

        return { studentCourses, assistantCourses, onCourseClick }
    },
}
</script>
