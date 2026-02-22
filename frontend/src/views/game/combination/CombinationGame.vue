<script setup>
import GameLayoutComponent from "@/components/games/GameLayoutComponent.vue";
import QuestionButton from "@/components/shared/QuestionButton.vue";
import BackButtonMiddle from "@/components/selectieSpel/BackButtonMiddle.vue";
import SelectieSpelCard from "@/components/selectieSpel/SelectieSpelCard.vue";
import {onMounted, ref} from "vue";

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
    <GameLayoutComponent class="topbar" title="Dieren, Voedsel en voertuigen" description="selecteer de juiste kaarten die bij elkaar horen"/>
    <QuestionButton class="vraagbutton"/>
    <BackButtonMiddle class="button"/>
    <div class="icons">
      <SelectieSpelCard v-for="icon in currentQuestion?.icons" :key="icon.id" :id="icon.id" :button-text="icon.buttonText" />
    </div>
  </div>
</template>

<style scoped>
.button {
  position: fixed;
  left: 20px;
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
  margin-top: 200px;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 20px;
}

</style>
