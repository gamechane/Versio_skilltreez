<script setup>
import AppLayout from "@/components/shared/AppLayout/AppLayout.vue";
import InputField from "@/components/games/InputField.vue";
import GameSelectCard from "@/components/games/GameSelectCard.vue";
import NextButton from "@/components/shared/NextButton.vue";

import orderGameImage from "@/assets/games/SelectCards/OrderGame.png";
import orderGameGif from "@/assets/games/SelectCards/OrderGameGif.gif";

import classifyGameImage from "@/assets/games/SelectCards/ClassifyGame.png";
import classifyGameGif from "@/assets/games/SelectCards/ClassifyGameGif.gif";

import combinationGameImage from "@/assets/games/SelectCards/CombinationGame.png";
import combinationGameGif from "@/assets/games/SelectCards/CombinationGameGif.gif";

import selectionGameImage from "@/assets/games/SelectCards/SelectionGame.png";
import selectionGameGif from "@/assets/games/SelectCards/SelectionGameGif.gif";

import answerGameImage from "@/assets/games/SelectCards/AnswerGame.png";
import answerGameGif from "@/assets/games/SelectCards/AnswerGameGif.gif";

import informationCardImage from "@/assets/games/SelectCards/InformationCard.png";
import informationCardGif from "@/assets/games/SelectCards/InformationCardGif.gif";
import { useRouter } from 'vue-router';
import {createGameStore} from "@/views/game/createGameStore.js";

const router = useRouter();
const games = [
  {
    title: "Rubriceerspel",
    imageUrl: classifyGameImage,
    gifUrl: classifyGameGif,
    path: "/games/create/classify-game",
    // path: "/games/create/rubriekspel",
  },
  {
    title: "Combinatiespel",
    imageUrl: combinationGameImage,
    gifUrl: combinationGameGif,
    path: "/games/create/combination-game",
  },
  {
    title: "Selectiespel",
    imageUrl: selectionGameImage,
    gifUrl: selectionGameGif,
    path: "/games/create/selection-game",
  },
  {
    title: "Antwoordspel",
    imageUrl: answerGameImage,
    gifUrl: answerGameGif,
    path: "/games/create/answer-game",
  },
  {
    title: "Rangschikspel",
    imageUrl: orderGameImage,
    gifUrl: orderGameGif,
    path: "/games/create/ranking-game"
  },
  {
    title: "Informatie",
    imageUrl: informationCardImage,
    gifUrl: informationCardGif,
    path: "/games/create/information-card",
  },
];

const submit = () => {
  const game = games.find(
    (game) => game.title === createGameStore.selectedGame
  );
  console.log("Game selected: ", game);
  router.push(game.path);
};
</script>

<template>
  <AppLayout>
    <div class="container">
      <InputField
        type="text"
        v-model="createGameStore.title"
        :value="createGameStore.title"
        :onChange="(v) => (createGameStore.title = v)"
      />
      <div class="input-container">
        <div class="games-container">
          <GameSelectCard
            v-for="game in games"
            :key="game.title"
            :title="game.title"
            :imageUrl="game.imageUrl"
            :gifUrl="game.gifUrl"
            :value="game.title === createGameStore.selectedGame"
            :click="() => (createGameStore.selectedGame = game.title)"
          />
        </div>
        <NextButton
          :onClick="submit"
          v-if="!!createGameStore.title && !!createGameStore.selectedGame"
        />
      </div>
    </div>
  </AppLayout>
</template>

<style scoped>
.container {
  background-color: var(--secondary-light);
  width: 100%;
  height: 100%;
  position: relative;

  display: flex;
  align-items: center;
  flex-direction: column;
  padding-top: 40px;

  gap: 50px;
}

.input-container {
  position: relative;
}

.games-container {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
  padding: 20px;
  margin: 20px;
}
</style>
