<template>
  <v-expansion-panels>
    <v-expansion-panel>
      <v-expansion-panel-title>
        <v-row no-gutters @click="loadSubjects">
          <v-col cols="4" class="d-flex justify-start"> Subjects </v-col>
        </v-row>
      </v-expansion-panel-title>
      <v-expansion-panel-text>
        <v-item-group selected-class="bg-primary">
          <v-container>
            <v-row>
              <v-col
                v-for="subject in subjectList"
                :key="subject"
                cols="12"
                md="4"
              >
                <v-item>
                  <v-card :class="['d-flex']" dark>
                    <div class="text-h6 flex-grow-1">
                      {{ subject }}
                    </div>
                  </v-card>
                </v-item>
              </v-col>
            </v-row>
          </v-container>
        </v-item-group>
      </v-expansion-panel-text>
    </v-expansion-panel>

    <v-expansion-panel>
      <v-expansion-panel-title>
        <v-row no-gutters @click="loadSubjects">
          <v-col cols="4" class="d-flex justify-start">
            Queue Administration
          </v-col>
        </v-row>
      </v-expansion-panel-title>
      <v-expansion-panel-text>
        <v-item-group selected-class="bg-primary">
          <v-container>
            <v-row>
              <v-col
                v-for="subject in subjectList"
                :key="subject"
                cols="12"
                md="4"
              >
                <v-item>
                  <v-card :class="['d-flex']" dark>
                    <div class="text-h6 flex-grow-1">
                      {{ subject }}
                    </div>
                  </v-card>
                </v-item>
              </v-col>
            </v-row>
          </v-container>
        </v-item-group>
      </v-expansion-panel-text>
    </v-expansion-panel>

    <v-expansion-panel>
      <v-expansion-panel-title>
        <v-row no-gutters @click="loadSubjects">
          <v-col cols="4" class="d-flex justify-start">
            List of approved tasks
          </v-col>
        </v-row>
      </v-expansion-panel-title>
      <v-expansion-panel-text>
        <v-item-group selected-class="bg-primary">
          <v-container>
            <v-row>
              <v-col
                v-for="subject in subjectList"
                :key="subject"
                cols="12"
                md="4"
              >
                <v-item>
                  <v-card :class="['d-flex']" dark>
                    <div class="text-h6 flex-grow-1">
                      {{ subject }}
                    </div>
                  </v-card>
                </v-item>
              </v-col>
            </v-row>
          </v-container>
        </v-item-group>
      </v-expansion-panel-text>
    </v-expansion-panel>
  </v-expansion-panels>
</template>

<script>
import { receiveAll } from "@/feature/api";

export default {
  name: "SideBar",
  data: () => ({
    panel: [0, 1],
    subjectList: ["test", "cool", "admin"],
  }),
  methods: {
    loadSubjects() {
      this.subjectList.push(
        receiveAll((err) => {
          if (err) {
            this.error = err.toString();
          }
          console.log("Fetched subjects");
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
