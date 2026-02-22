<script setup>
import router from "@/router/index.js";
import backImg from "@/assets/arrowContinue.svg";
import { computed } from "vue";

// Define a prop for the route
const props = defineProps({
  route: {
    type: String,
    required: true
  }
});

const handleEvent = () => {
  router.push(props.route);
  console.log("Next button clicked");
};

const canGoBack = computed(() => window.history.length > 1);
</script>

<template>
  <button @click="handleEvent" v-if="canGoBack">
    <img width="20px" height="20px" :src="backImg" alt="Back" />
  </button>
</template>

<style scoped>
button {
  background-color: transparent;
  border: none;
  cursor: pointer;

  position: sticky;
  top: 0px;
  left: 0px;
}
</style>