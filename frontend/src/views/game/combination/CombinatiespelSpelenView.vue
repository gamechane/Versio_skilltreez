<script setup>
import TopNav from "@/components/shared/AppLayout/TopNav.vue";
import BackButton from "@/components/shared/BackButton.vue";
import GameLayoutComponent from "@/components/games/GameLayoutComponent.vue";
import CombinationGameCard from "@/components/games/CombinationGameCard.vue";
import GameLayoutComponent2 from "@/components/games/GameLayoutComponent2.vue";

import { ref } from "vue";

const cards = ref([
  { id: "1", title: "Fiets", set: "vervoer" },
  { id: "2", title: "Auto", set: "vervoer" },
  { id: "3", title: "Vrachtwagen", set: "vervoer" },
  { id: "4", title: "Hond", set: "dieren" },
  { id: "5", title: "Kat", set: "dieren" },
  { id: "6", title: "Vis", set: "dieren" },
  { id: "7", title: "Broccoli", set: "planten" },
  { id: "8", title: "Wortel", set: "planten" },
  { id: "9", title: "Appel", set: "planten" },
  { id: "10", title: "Zinc", set: "elementen" },
  { id: "11", title: "Ijzer", set: "elementen" },
  { id: "12", title: "Koper", set: "elementen" },
]);

const selectedCards = ref([]);

const checkSet = () => {
  if (selectedCards.value.length == 3) {
    const sets = selectedCards.value.map((card) => card.set);

    if (new Set(sets).size === 1) {
      cards.value = cards.value.filter(
        (card) => !selectedCards.value.map((c) => c.id).includes(card.id)
      );

      selectedCards.value = [];
    } else {
      setTimeout(() => {
        alert("Deze kaarten horen niet bij elkaar");
        selectedCards.value = [];
      }, 200);
    }
  }
};

const handleClickCard = (card) => {
  if (selectedCards.value.includes(card)) {
    selectedCards.value = selectedCards.value.filter((c) => c !== card);
  } else if (selectedCards.value.length < 3) {
    selectedCards.value.push(card);
  } else {
    alert("3 kaarten zijn al geselecteerd");
  }

  checkSet();
};

const shuffleCards = () => {
  cards.value = cards.value.sort(() => Math.random() - 0.5);
};

shuffleCards();
</script>

<template>
  <top-nav> </top-nav>
  <back-button class="button"> </back-button>

  <GameLayoutComponent2
    title="Dieren, Voedsel en voertuigen"
    description="Welke kaarten horen bij elkaar? Elke set bestaat uit 3 kaarten."
  >
    <div class="grid-container">
      <combination-game-card
        v-for="card in cards"
        :click="() => handleClickCard(card)"
        :title="card.title"
        :set="card.set"
        :selected="selectedCards.map((c) => c.id).includes(card.id)"
      />
    </div>
  </GameLayoutComponent2>
</template>

<style scoped>
.grid-container {
  display: grid;
  grid-template-columns: repeat(6, 1fr);
  grid-gap: 20px;
  margin-bottom: 50px;
}

.button {
  position: relative;
  top: 10px;
  left: 10px;
}
</style>
