<template>
  <v-container>
    <v-row>
      <v-col col="12">
        <v-expansion-panels v-model="panel" multiple>
          <v-expansion-panel>
            <v-expansion-panel-header>Subjects</v-expansion-panel-header>
            <v-expansion-panel-content
              v-for="(subject, i) in { subjectList }"
              :key="i"
            >
              <div class="subject"></div>
            </v-expansion-panel-content>
          </v-expansion-panel>

          <v-expansion-panel>
            <v-expansion-panel-header>
              Queue administration
            </v-expansion-panel-header>
            <v-expansion-panel-content
              v-for="(subject, i) in { subjectList }"
              :key="i"
            >
              <div class="subject"></div>
            </v-expansion-panel-content>
          </v-expansion-panel>

          <v-expansion-panel>
            <v-expansion-panel-header>Task list</v-expansion-panel-header>
            <v-expansion-panel-content
              v-for="(subject, i) in { subjectList }"
              :key="i"
            >
              <div class="subject"></div>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-col>
    </v-row>
    <div id="settings">
      <v-btn>User settings</v-btn>
    </div>
    <div id="logout">
      <v-btn>Log out</v-btn>
    </div>
  </v-container>
</template>

<script>
import { receiveAll } from "@/feature/api";

export default {
  name: "SideBar",
  data: () => ({
    panel: [0, 1],
    subjectList: [],
  }),
  methods: {
    loadSubjects() {
      this.subjectList.push(
        receiveAll((err) => {
          if (err) {
            this.error = err.toString();
          }
        })
      );
    },
  },
};
</script>

<style scoped>
body {
  display: grid;
  grid-template-columns: repeat(1, 1fr);
  text-align: center;
}
</style>
