<script setup>
import TopNav from "@/components/shared/AppLayout/TopNav.vue";
import BackButton from "@/components/shared/BackButton.vue";
import { ref } from "vue";
import similarity from "similarity";
import GameLayoutComponent2 from "@/components/games/GameLayoutComponent2.vue";
import { createGameStore } from "@/views/game/createGameStore.js";
import { answerGameStore } from "./answerGameStore.js";
import router from "@/router/index.js";

const questionIndex = ref(0);
const currentQuestion = ref(
  answerGameStore.getQuestions()[questionIndex.value]
);

const currentAnswer = ref("");

const checkAnswer = () => {
  for (const answer of currentQuestion.value.answers) {
    const similarityScore = similarity(currentAnswer.value, answer.answer);

    if (
      similarityScore >
      (100 - (100 - currentQuestion.value.correctness)) / 100
    ) {
      alert("Correct!");
      if (questionIndex.value < answerGameStore.getQuestions().length - 1) {
        questionIndex.value++;
        currentQuestion.value =
          answerGameStore.getQuestions()[questionIndex.value];
        currentAnswer.value = "";
      } else {
        alert("You've completed the quiz!");
        router.push("/games/create/answer-game/settings");
      }
      return;
    }
  }

  alert("Incorrect!");
};

const updateAnswer = (value) => {
  currentAnswer.value = value.target.value;
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
      <h1>{{ currentQuestion.question }}</h1>
      <input class="input" v-model="currentAnswer" @input="updateAnswer" />
      <button class="submitButton" @click="checkAnswer">Controleer</button>
    </div>
  </GameLayoutComponent2>
</template>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  gap: 20px;

  background-color: var(--secondary-light);
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

.input {
  padding: 10px;
  font-size: 1.5rem;
  border: transparent;
  width: min(500px, 80%);
}

.submitButton:hover {
  filter: brightness(0.9);
}
</style>
