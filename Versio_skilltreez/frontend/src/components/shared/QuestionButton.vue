<script setup>
  import questionButton from "@/assets/questionButton.png";
</script>

<template>
    <button>
      <img width="30px" height="30px" :src="questionButton" alt="question" />
    </button>
</template>

<style>
button {
  background-color: transparent;
  border: none;
  cursor: pointer;

  position: sticky;
  top: 0px;
  right: 0px;
}
</style>