<script setup>
import { createGameStore } from "@/views/game/createGameStore.js";
import timeIcon from "@/assets/time.svg";
</script>

<template>
  <div class="container">
    <div class="iconContainer">
      <img :src="timeIcon" alt="Heart" class="icon" />
    </div>
    <div class="text">
      <h1>Tijdslimiet</h1>
      <p>Hoeveel tijd heeft de leerling?</p>
    </div>
    <input
      name="time"
      class="timeInput"
      type="number"
      v-model="createGameStore.settings.timeLimit"
      min="0"
      max="100"
    />
    <div class="infiniteContainer">
      <input
        type="checkbox"
        id="isInfinite"
        name="isInfinite"
        v-model="createGameStore.settings.isInfiniteTimeLimit"
      />
      <span>∞</span>
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px;
  width: 100%;
  background-color: var(--secondary-light);
  border-radius: 10px;
  margin: 10px 0;
  gap: 10px;
}

.text {
  flex: 1;
  margin-left: 10px;
}
.text h1 {
  margin: 0;
}

.text p {
  margin: 0;
}

.icon {
  width: 24px;
  height: 24px;
  margin-top: 3px;
}

.timeInput {
  padding: 8px 10px;
  border: 1px solid black;
  border-radius: 0;
  border-radius: 4px;
  width: 60px;
  font-weight: bold;
  font-size: 1.5rem;
  margin-right: 30px;
}

.infiniteContainer {
  display: flex;
  align-items: center;
  gap: 5px;
}

.infiniteContainer span {
  font-size: 2rem;
  font-weight: bold;
}

.infiniteContainer input {
  margin: 0;
  padding: 0;
  width: 24px;
  height: 24px;
}

.iconContainer {
  height: 100%;
}
</style>
