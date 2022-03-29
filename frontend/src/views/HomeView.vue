<template>
  <div>
    <SideBar />
  </div>
  <div>
    <!-- -->
  </div>
  <div>
    <!-- -->
  </div>
</template>

<script>
import { defineComponent } from "vue";

// Components
import SideBar from "../components/SideBarComponent.vue";

export default defineComponent({
  name: "QueueView",
  components: {
    SideBar,
  },
});
</script>
