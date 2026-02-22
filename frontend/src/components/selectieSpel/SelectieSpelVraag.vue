<script setup>
defineProps({
  question: String,
});
</script>

<template>
  <h1>
    {{ question }}
  </h1>
</template>

<style scoped>
  h1 {
    margin-top: 100px;
  }
</style>
