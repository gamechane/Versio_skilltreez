<script setup>
import router from "@/router/index.js";
import backImg from "@/assets/arrow.svg";
import { computed } from "vue";

const handleEvent = () => {
  router.go(-1);
  console.log("Back button clicked");
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
  top: 0;
  left: 0;
}
</style>
