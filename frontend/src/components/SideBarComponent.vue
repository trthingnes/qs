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
                  <!-- TODO: Add buttons -->
                </v-expansion-panel-content>
              </v-expansion-panel>
            </v-expansion-panels>
          </div>
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {};
</script>
