<template>
  <v-app>
    <v-main>
      <router-view />
      <SideBarComponent />
    </v-main>
  </v-app>
</template>

<script>
import SideBarComponent from "@/components/SideBarComponent";
export default {
  name: "App",
  components: {
    SideBarComponent,
  },

  data: () => ({
    //
  }),
};
</script>
