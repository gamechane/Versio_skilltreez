<template>
  <div class="buttonWrapper">
    <button @submit="$emit('SubmitEvent')" :type="type">{{ inner }}</button>
  </div>
</template>

<script setup>
  defineProps({
    inner: String,
    type: String,
  });
</script>

<style>
  .buttonWrapper {
  }

  .buttonWrapper > button {
    background-color: var(--primary);
    border: none;
    color: white;
    font: var(--offwhite);
    padding: 0.7rem 3rem;
    border-radius: 2px;
    cursor: pointer;
    transition: background-color 0.3s ease; /* voeg een overgang toe voor een soepel effect */
  }

  .buttonWrapper > button:hover {
    background-color: darkgreen; /* verander de kleur naar donkergroen wanneer de muis over de knop gaat */
  }
</style>
