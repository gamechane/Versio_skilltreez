<template>
  <RouterLink class="textLink" :to="linkTo">{{ inner }}</RouterLink>
</template>

<script setup>
  defineProps({
    inner: {
      type: String,
      required: true
    },
    linkTo: {
      type: String,
      required: true
    }
  });
</script>

<style>
  .textLink {
    color: var(--dark)
  }
</style>