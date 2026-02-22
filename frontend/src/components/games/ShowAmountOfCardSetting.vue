<script setup>
import { createGameStore } from "@/views/game/createGameStore.js";
import combiCard from "@/assets/combi-card.svg";
</script>

<template>
  <div class="container">
    <div class="iconContainer">
      <img :src="combiCard" alt="Combicard" class="icon" />
    </div>
    <div class="text">
      <h1>Aantal kaarten tonen</h1>
      <p>Laat zien hoeveel kaarten in de rangschikking horen.</p>
    </div>
    <div class="amountOfCardsContainer">
      <input
        type="checkbox"
        id="amountOfCards"
        name="amountOfCards"
        v-model="createGameStore.settings.showAmountOfCards"
      />
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  align-items: center;
  padding: 10px;
  width: 100%;
  background-color: var(--secondary-light);
  border-radius: 10px;
  margin: 10px 0;
  gap: 10px;
}

.text {
  margin-left: 10px;
  margin-right: 40px;
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

.amountOfCardsContainer {
  display: flex;
  align-items: center;
  gap: 5px;
}

.amountOfCardsContainer span {
  font-size: 2rem;
  font-weight: bold;
}

.amountOfCardsContainer input {
  margin: 0;
  padding: 0;
  width: 24px;
  height: 24px;
}

.iconContainer {
  height: 100%;
}
</style>
