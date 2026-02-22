<script setup>
import backImg from "@/assets/arrow.svg";

defineProps({
  onClick: {
    type: Boolean,
    required: true,
  },
});
</script>

<template>
  <button @click="onClick">
    <img width="20px" height="20px" :src="backImg" alt="Back" />
  </button>
</template>

<style scoped>
button {
  background-color: transparent;
  border: none;
  cursor: pointer;
  transform: rotate(180deg);
  position: absolute;
  top: 50%;
  right: -60px;
}
</style>