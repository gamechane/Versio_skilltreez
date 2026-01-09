<script setup>
import TopNav from "@/components/shared/AppLayout/TopNav.vue";
import BackButton from "@/components/shared/BackButton.vue";
import GameLayoutComponent2 from "@/components/games/GameLayoutComponent2.vue";
import { createGameStore } from "@/views/game/createGameStore.js";
import { rankingStore } from "./rankingStore.js";
import sorteerPijlIcon from "@/assets/sorteer-pijl.svg";
import { ref } from "vue";
import router from "@/router/index.js";

// const unSortedCards = ref(rankingStore.getAllCards());

const unSortedCards = ref(rankingStore.getAllCards());

const sortedCards = ref([]);

const sortCard = (card) => {
  sortedCards.value.push(card);
  unSortedCards.value = unSortedCards.value.filter((c) => c.id !== card.id);
};

const unSortCard = (card) => {
  unSortedCards.value.push(card);
  sortedCards.value = sortedCards.value.filter((c) => c.id !== card.id);
};

function checkSorted(arr) {
  for (let i = 0; i < arr.length - 1; i++) {
    if (arr[i] > arr[i + 1]) {
      return false;
    }
  }
  return true;
}

const checkCards = () => {
  if (sortedCards.value.length < rankingStore.cards.length) {
    alert("not all cards sorted");
  } else if (sortedCards.value.length > rankingStore.cards.length) {
    alert("too many cards sorted");
  } else {
    if (sortedCards.value.every((card) => !card.extra)) {
      const sortedCardIndexes = sortedCards.value.map((card) => card.index);

      if (checkSorted(sortedCardIndexes)) {
        alert("Volgorde is correct");
        router.push("/games/create/ranking-game/settings");
        return;
      }
    }
    alert("Volgorde is niet correct");
  }
};
</script>

<template>
  <TopNav />
  <BackButton />
  <GameLayoutComponent2
    :title="createGameStore.title"
    :description="createGameStore.description"
  >
    <div class="container">
      <div
        class="amount-of-cards"
        v-if="createGameStore.settings.showAmountOfCards"
      >
        <span>{{ rankingStore.cards.length }}</span> kaarten
      </div>
      <div class="sorted-container">
        <div
          class="card"
          v-for="card in sortedCards"
          :key="card.id"
          @click="() => unSortCard(card)"
        >
          <div class="card-center">
            {{ card.text }}
          </div>
          <div class="card-footer">
            <button class="sort-button reverse">
              <img
                :src="sorteerPijlIcon"
                class="sorteerPijlIcon"
                alt="sorteerPijlIcon"
              />
            </button>
          </div>
        </div>
        <div
          class="placeholder-card"
          v-if="
            createGameStore.settings.showAmountOfCards &&
            (rankingStore.cards.length ?? 0) - (sortedCards.length ?? 0) > 0
          "
          v-for="n in Math.min(
            Math.max(
              (rankingStore.cards.length ?? 0) - (sortedCards.length ?? 0),
              0
            ),
            Infinity
          )"
          :key="n"
        />
      </div>
      <div class="unsorted-container">
        <div
          class="card"
          v-for="card in unSortedCards"
          :key="card.id"
          @click="() => sortCard(card)"
        >
          <div class="card-center">
            {{ card.text }}
          </div>
          <div class="card-footer">
            <button class="sort-button">
              <img
                :src="sorteerPijlIcon"
                class="sorteerPijlIcon"
                alt="sorteerPijlIcon"
              />
            </button>
          </div>
        </div>
      </div>
      <div class="submitButtonContainer">
        <button class="submitButton" @click="checkCards">Controleer</button>
      </div>
    </div>
  </GameLayoutComponent2>
</template>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 80vh;
  width: 760px;
  gap: 20px;
  position: relative;
  padding: 20px;

  background-color: var(--secondary-light);
}

.amount-of-cards {
  font-size: 1.5rem;
  font-weight: bold;
  display: flex;
  align-items: center;
  gap: 4px;
  position: absolute;
  top: 30px;
  left: 30px;
}

.amount-of-cards span {
  font-size: 2rem;
}

.submitButtonContainer {
  display: flex;
  justify-content: center;
  width: 100%;
}

.submitButton {
  background-color: var(--secondary);
  color: black;
  padding: 15px 40px;
  border: none;
  cursor: pointer;

  font-weight: bold;
  font-size: 1.5rem;
}

.submitButton:hover {
  filter: brightness(0.9);
}

.sorted-container {
  margin-top: 70px;
  display: flex;
  gap: 40px;
  flex-wrap: wrap;
  flex: 1;
}

.unsorted-container {
  display: flex;
  gap: 20px;
  width: 100%;
  overflow-x: scroll;
  margin-top: 80px;
}

.placeholder-card {
  width: 148px;
  height: 206px;
  border-radius: 8px;
  background-color: white;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  background-color: transparent;
  outline: 4px solid var(--primary);
}

.card {
  width: 148px;
  height: 206px;
  min-width: 148px;
  min-height: 206px;
  border-radius: 8px;
  background-color: var(--secondary);
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.card-center {
  padding: 8px;
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}

.card-footer {
  background-color: var(--secondary);
  display: flex;
  justify-content: center;
  padding: 8px;
  height: 30px;
}

.sort-button {
  padding: 8px;
  border-radius: 5px;
  background-color: white;
  justify-content: center;
  align-items: center;
  display: flex;
  transform: rotate(90deg);
}

.sort-button:disabled {
  opacity: 0;
  cursor: default;
}

.sort-button:hover {
  filter: brightness(0.8);
}

.sort-button.reverse {
  padding: 8px;
  border-radius: 5px;
  background-color: white;
  justify-content: center;
  align-items: center;
  display: flex;
  transform: rotate(-90deg);
}
</style>
