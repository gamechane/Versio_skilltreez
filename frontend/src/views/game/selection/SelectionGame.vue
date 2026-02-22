<script setup>

import SelectieSpelCard from "@/components/selectieSpel/SelectieSpelCard.vue";
import SelectieSpelVraag from "@/components/selectieSpel/SelectieSpelVraag.vue";
import SelectieSpelCheckButton from "@/components/selectieSpel/SelectieSpelCheckButton.vue";
import GameLayoutComponent from "@/components/games/GameLayoutComponent.vue";
import QuestionButton from "@/components/shared/QuestionButton.vue";

import { ref, onMounted } from 'vue';
import BackButton from "@/components/shared/BackButton.vue";
import BackButtonMiddle from "@/components/selectieSpel/BackButtonMiddle.vue";

let currentQuestion = ref(null);

onMounted(() => {
  const storedQuestions = localStorage.getItem('questions');
  if (storedQuestions) {
    const questions = JSON.parse(storedQuestions);
    currentQuestion.value = questions[0]; // Neem de eerste vraag als de huidige vraag

    // Zorg ervoor dat elke kaart een unieke id heeft
    currentQuestion.value.icons.forEach((icon, index) => {
      if (!icon.id) {
        icon.id = icon.buttonText; // Geef de kaart een unieke id op basis van de buttonText
      }
    });

    // Sla de bijgewerkte vraag op in de local storage
    localStorage.setItem('questions', JSON.stringify(questions));
  }
});
</script>

<template>
  <div class="container">
    <GameLayoutComponent class="topbar" title="Zwemmende dieren" description="Wat zijn de juiste antwoorden?"/>
    <QuestionButton class="vraagbutton"/>
    <SelectieSpelVraag class="vraagComponent" :question="currentQuestion?.questionText" />
    <BackButtonMiddle class="button"/>
    <div class="icons">
      <SelectieSpelCard v-for="icon in currentQuestion?.icons" :key="icon.id" :id="icon.id" :button-text="icon.buttonText" />
    </div>
    <SelectieSpelCheckButton button-text="Controleer" />
  </div>
</template>

<style scoped>
.button {
  position: fixed;
  left: 20px;
}

.vraagComponent {
  margin-top: 150px;
}

.vraagbutton{
  position: fixed;
  right: 0;
  margin-right: 10px;
  margin-top: 100px;
}

.container {
  background-color: white;
  display: flex;
  justify-content: start;
  align-items: center;
  postion: absolute;
  flex-direction: column;
  gap: 30px;
  height: 100%;
  width: 100%;
}

.icons {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 20px;
}

</style>