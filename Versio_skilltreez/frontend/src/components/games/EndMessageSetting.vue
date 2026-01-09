<script setup>
import { createGameStore } from "@/views/game/createGameStore.js";
import TrophyIcon from "@/assets/trophy.svg";
</script>

<template>
  <div class="container">
    <div class="title">
      <h1>Eindbericht success</h1>
      <img :src="TrophyIcon" alt="Trophy" />
    </div>
    <textarea
      name="endMessage"
      class="endMessageInput"
      v-model="createGameStore.settings.endMessage"
      placeholder="Dit bericht verschijnt wanneer de leerling het spel heeft gehaald."
    />
  </div>
</template>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  padding: 10px;
  width: 100%;
  background-color: var(--secondary-light);
  border-radius: 10px;
  margin: 10px 0;
}

.title {
  display: flex;
  align-items: center;
  gap: 10px;
}

.endMessageInput {
  height: 100px;
  padding: 10px;
  resize: none;
}
</style>
