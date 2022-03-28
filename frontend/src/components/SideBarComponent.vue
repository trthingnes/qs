<template>
  <v-container>
    <v-row>
      <v-col col="12">
        <div>
          <div class="d-flex">
            <v-expansion-panels v-model="panel" :disabled="disabled" multiple>
              <v-expansion-panel>
                <v-expansion-panel-header>Subjects</v-expansion-panel-header>
                <v-expansion-panel-content>
                  <v-btn v-for="(subjectName, i) in { subjectList }" :key="i">
                    { subjectName }
                  </v-btn>
                </v-expansion-panel-content>
              </v-expansion-panel>

              <v-expansion-panel>
                <v-expansion-panel-header>
                  Queue administration
                </v-expansion-panel-header>
                <v-expansion-panel-content>
                  <v-btn v-for="(subjectName, i) in { subjectList }" :key="i">
                    { subjectName }
                  </v-btn>
                </v-expansion-panel-content>
              </v-expansion-panel>

              <v-expansion-panel>
                <v-expansion-panel-header>Task list</v-expansion-panel-header>
                <v-expansion-panel-content>
                  <v-btn
                    v-for="(subjectName, i) in { subjectList }"
                    :key="i"
                    @click="loadSubjects"
                  >
                    { subjectName }
                  </v-btn>
                </v-expansion-panel-content>
              </v-expansion-panel>
              <v-btn>User settings</v-btn>
              <v-btn>Log out</v-btn>
            </v-expansion-panels>
          </div>
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { receiveAll } from "@/feature/api";

export default {
  name: "SideBar",
  data() {
    return {
      subjectList: [],
      get: null,
      error: null,
    };
  },
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
